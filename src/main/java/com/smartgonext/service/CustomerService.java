package com.smartgonext.service;

import com.smartgonext.tenant.dto.CustomerDetailDto;
import com.smartgonext.tenant.dto.CustomerFormDto;
import com.smartgonext.tenant.dto.CustomerListItemDto;
import com.smartgonext.tenant.dto.CustomerStatsDto;
import com.smartgonext.tenant.entity.*;
import com.smartgonext.tenant.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerEntityRepository customerRepository;
    private final AppointmentEntityRepository appointmentRepository;
    private final InvoiceEntityRepository invoiceRepository;
    private final CustomerMembershipEntityRepository membershipRepository;
    private final CustomerVisitCounterRepository visitCounterRepository;
    private final CustomerPointsCounterRepository pointsCounterRepository;

    public CustomerService(CustomerEntityRepository customerRepository,
                           AppointmentEntityRepository appointmentRepository,
                           InvoiceEntityRepository invoiceRepository,
                           CustomerMembershipEntityRepository membershipRepository,
                           CustomerVisitCounterRepository visitCounterRepository,
                           CustomerPointsCounterRepository pointsCounterRepository) {
        this.customerRepository = customerRepository;
        this.appointmentRepository = appointmentRepository;
        this.invoiceRepository = invoiceRepository;
        this.membershipRepository = membershipRepository;
        this.visitCounterRepository = visitCounterRepository;
        this.pointsCounterRepository = pointsCounterRepository;
    }

    @Transactional(readOnly = true)
    public Page<CustomerListItemDto> getCustomers(String search, String gender, String status,
                                                  String membershipFilter, int page, int size,
                                                  String sortBy, String sortDir) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir != null ? sortDir : "DESC"),
                (sortBy != null && !sortBy.trim().isEmpty()) ? sortBy : "id");
        Pageable pageable = PageRequest.of(Math.max(0, page), Math.max(1, size), sort);

        Page<CustomerEntity> entitiesPage = customerRepository.searchCustomers(
                (search != null && !search.trim().isEmpty()) ? search.trim() : null,
                (gender != null && !gender.trim().isEmpty() && !"ALL".equalsIgnoreCase(gender)) ? gender.trim() : null,
                (status != null && !status.trim().isEmpty() && !"ALL".equalsIgnoreCase(status)) ? status.trim() : null,
                pageable
        );

        List<CustomerListItemDto> items = new ArrayList<>();
        for (CustomerEntity entity : entitiesPage.getContent()) {
            CustomerListItemDto item = mapToListItem(entity);

            // Filter by membership if specified
            if (membershipFilter != null && !membershipFilter.trim().isEmpty() && !"ALL".equalsIgnoreCase(membershipFilter)) {
                if ("Active".equalsIgnoreCase(membershipFilter) && "Standard".equalsIgnoreCase(item.getMembershipStatus())) {
                    continue;
                }
                if ("Standard".equalsIgnoreCase(membershipFilter) && !"Standard".equalsIgnoreCase(item.getMembershipStatus())) {
                    continue;
                }
            }
            items.add(item);
        }

        return new PageImpl<>(items, pageable, entitiesPage.getTotalElements());
    }

    @Transactional(readOnly = true)
    public CustomerStatsDto getCustomerStats() {
        long total = customerRepository.countByDeletedFalse();
        LocalDateTime startOfMonth = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        long newThisMonth = customerRepository.countByDeletedFalseAndCreatedAtGreaterThanEqual(startOfMonth);
        long active = customerRepository.countByDeletedFalseAndStatus("Active");
        long returning = visitCounterRepository.countReturningCustomers();

        return new CustomerStatsDto(total, newThisMonth, active, returning);
    }

    @Transactional(readOnly = true)
    public CustomerEntity getCustomerById(Long id) {
        return customerRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public CustomerFormDto getCustomerFormById(Long id) {
        CustomerEntity entity = getCustomerById(id);
        return new CustomerFormDto(
                entity.getId(),
                entity.getFullName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getGender(),
                entity.getDateOfBirth(),
                entity.getAnniversaryDate(),
                entity.getAddress(),
                entity.getCity(),
                entity.getNotes(),
                entity.getStatus()
        );
    }

    @Transactional(readOnly = true)
    public CustomerDetailDto getCustomerDetail(Long id) {
        CustomerEntity entity = getCustomerById(id);
        CustomerDetailDto detail = new CustomerDetailDto();
        detail.setCustomer(entity);

        // Visits and last visit date
        Optional<CustomerVisitCounter> visitOpt = visitCounterRepository.findByCustomerId(id);
        if (visitOpt.isPresent()) {
            detail.setTotalVisits(visitOpt.get().getCurrentVisitCount());
            detail.setLastVisitDate(visitOpt.get().getLastVisitDate());
        } else {
            long apptCount = appointmentRepository.countByCustomerIdAndStatus(id, "Completed");
            detail.setTotalVisits((int) apptCount);
        }

        // Total spending
        BigDecimal spending = invoiceRepository.calculateTotalSpending(id);
        detail.setTotalSpending(spending != null ? spending : BigDecimal.ZERO);

        // Loyalty points
        pointsCounterRepository.findByCustomerId(id).ifPresent(pts ->
                detail.setLoyaltyPoints(pts.getCurrentPointsBalance())
        );

        // Active Membership
        Optional<CustomerMembershipEntity> activeMembership = membershipRepository
                .findFirstByCustomerIdAndStatusOrderByStartDateDesc(id, "Active");
        if (activeMembership.isPresent()) {
            CustomerMembershipEntity mem = activeMembership.get();
            detail.setHasMembership(true);
            detail.setMembershipStatus("Active");
            detail.setMembershipStartDate(mem.getStartDate());
            detail.setMembershipExpiresAt(mem.getExpiresAt());
            if (mem.getMembershipPlan() != null) {
                detail.setMembershipTier(mem.getMembershipPlan().getName());
            } else {
                detail.setMembershipTier("Active Member");
            }
        } else {
            detail.setHasMembership(false);
            detail.setMembershipStatus("None");
            detail.setMembershipTier("Standard Walk-in");
        }

        // Appointments history
        detail.setAppointments(appointmentRepository.findByCustomerIdOrderByAppointmentDateDescStartTimeDesc(id));

        // Invoices history
        detail.setInvoices(invoiceRepository.findByCustomerIdOrderByCreatedAtDesc(id));

        // All memberships history
        detail.setMemberships(membershipRepository.findByCustomerIdOrderByStartDateDesc(id));

        return detail;
    }

    public CustomerEntity saveCustomer(CustomerFormDto dto) {
        String cleanPhone = dto.getPhone() != null ? dto.getPhone().trim() : "";
        if (cleanPhone.isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be empty.");
        }

        if (dto.getId() == null) {
            // New Customer - Check mobile uniqueness within tenant
            if (customerRepository.existsByPhoneAndDeletedFalse(cleanPhone)) {
                throw new IllegalArgumentException("A customer with mobile number '" + cleanPhone + "' already exists in this salon.");
            }

            CustomerEntity customer = new CustomerEntity();
            customer.setFullName(dto.getFullName());
            customer.setPhone(cleanPhone);
            customer.setEmail(dto.getEmail());
            customer.setGender(dto.getGender());
            customer.setDateOfBirth(dto.getDateOfBirth());
            customer.setAnniversaryDate(dto.getAnniversaryDate());
            customer.setAddress(dto.getAddress());
            customer.setCity(dto.getCity());
            customer.setNotes(dto.getNotes());
            customer.setStatus(dto.getStatus() != null ? dto.getStatus() : "Active");
            customer.setDeleted(false);

            CustomerEntity saved = customerRepository.save(customer);

            // Initialize visit counter and loyalty points
            CustomerVisitCounter visitCounter = new CustomerVisitCounter();
            visitCounter.setCustomer(saved);
            visitCounter.setCurrentVisitCount(0);
            visitCounter.setTotalFreeServicesClaimed(0);
            visitCounterRepository.save(visitCounter);

            CustomerPointsCounter pointsCounter = new CustomerPointsCounter();
            pointsCounter.setCustomer(saved);
            pointsCounter.setCurrentPointsBalance(0);
            pointsCounter.setTotalPointsEarned(0);
            pointsCounter.setTotalPointsRedeemed(0);
            pointsCounterRepository.save(pointsCounter);

            log.info("Registered new customer #{} - {} ({})", saved.getId(), saved.getFullName(), cleanPhone);
            return saved;
        } else {
            // Existing Customer - Check mobile uniqueness excluding self
            if (customerRepository.existsByPhoneAndIdNotAndDeletedFalse(cleanPhone, dto.getId())) {
                throw new IllegalArgumentException("Another customer with mobile number '" + cleanPhone + "' already exists in this salon.");
            }

            CustomerEntity customer = getCustomerById(dto.getId());
            customer.setFullName(dto.getFullName());
            customer.setPhone(cleanPhone);
            customer.setEmail(dto.getEmail());
            customer.setGender(dto.getGender());
            customer.setDateOfBirth(dto.getDateOfBirth());
            customer.setAnniversaryDate(dto.getAnniversaryDate());
            customer.setAddress(dto.getAddress());
            customer.setCity(dto.getCity());
            customer.setNotes(dto.getNotes());
            if (dto.getStatus() != null) {
                customer.setStatus(dto.getStatus());
            }

            CustomerEntity updated = customerRepository.save(customer);
            log.info("Updated customer #{} - {} ({})", updated.getId(), updated.getFullName(), cleanPhone);
            return updated;
        }
    }

    public DeleteResult deleteCustomer(Long id) {
        CustomerEntity customer = getCustomerById(id);

        long apptCount = appointmentRepository.countByCustomerId(id);
        long invoiceCount = invoiceRepository.countByCustomerId(id);

        if (apptCount > 0 || invoiceCount > 0) {
            // Soft delete to protect financial audit and appointment trails
            customer.setDeleted(true);
            customer.setStatus("Inactive");
            customerRepository.save(customer);
            log.info("Soft-deleted customer #{} due to {} appointments and {} invoices", id, apptCount, invoiceCount);
            return new DeleteResult(true, true,
                    "Customer '" + customer.getFullName() + "' was safely archived as Inactive to protect " +
                            apptCount + " appointment(s) and " + invoiceCount + " invoice(s) on record.");
        } else {
            // Safe removal
            customer.setDeleted(true);
            customer.setStatus("Inactive");
            customerRepository.save(customer);
            log.info("Safely deleted customer #{} (no linked invoices/appointments)", id);
            return new DeleteResult(true, false,
                    "Customer '" + customer.getFullName() + "' has been successfully removed.");
        }
    }

    private CustomerListItemDto mapToListItem(CustomerEntity entity) {
        Long id = entity.getId();

        // Total visits and last visit date
        int visits = 0;
        LocalDate lastVisit = null;
        Optional<CustomerVisitCounter> visitOpt = visitCounterRepository.findByCustomerId(id);
        if (visitOpt.isPresent()) {
            visits = visitOpt.get().getCurrentVisitCount();
            lastVisit = visitOpt.get().getLastVisitDate();
        }

        // Total spending
        BigDecimal spending = invoiceRepository.calculateTotalSpending(id);

        // Membership
        String membershipStatus = "Standard";
        String planName = null;
        Optional<CustomerMembershipEntity> memOpt = membershipRepository
                .findFirstByCustomerIdAndStatusOrderByStartDateDesc(id, "Active");
        if (memOpt.isPresent()) {
            membershipStatus = "Active";
            if (memOpt.get().getMembershipPlan() != null) {
                planName = memOpt.get().getMembershipPlan().getName();
            } else {
                planName = "VIP Member";
            }
        }

        // Points
        int points = 0;
        Optional<CustomerPointsCounter> ptsOpt = pointsCounterRepository.findByCustomerId(id);
        if (ptsOpt.isPresent()) {
            points = ptsOpt.get().getCurrentPointsBalance();
        }

        return new CustomerListItemDto(
                id,
                entity.getFullName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getGender(),
                visits,
                lastVisit,
                spending != null ? spending : BigDecimal.ZERO,
                membershipStatus,
                planName,
                points,
                entity.getStatus()
        );
    }

    public static class DeleteResult {
        private final boolean success;
        private final boolean softDeleted;
        private final String message;

        public DeleteResult(boolean success, boolean softDeleted, String message) {
            this.success = success;
            this.softDeleted = softDeleted;
            this.message = message;
        }

        public boolean isSuccess() { return success; }
        public boolean isSoftDeleted() { return softDeleted; }
        public String getMessage() { return message; }
    }
}
