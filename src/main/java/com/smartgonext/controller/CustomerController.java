package com.smartgonext.controller;

import com.smartgonext.service.CustomerService;
import com.smartgonext.tenant.dto.CustomerDetailDto;
import com.smartgonext.tenant.dto.CustomerFormDto;
import com.smartgonext.tenant.dto.CustomerListItemDto;
import com.smartgonext.tenant.dto.CustomerStatsDto;
import com.smartgonext.tenant.entity.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "membership", required = false) String membership,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "DESC") String sortDir,
            Model model) {

        Page<CustomerListItemDto> customersPage = customerService.getCustomers(
                search, gender, status, membership, page, size, sortBy, sortDir);

        CustomerStatsDto stats = customerService.getCustomerStats();

        model.addAttribute("activeModule", "customers");
        model.addAttribute("pageTitle", "Client Relationship Management (CRM)");
        model.addAttribute("customersPage", customersPage);
        model.addAttribute("customers", customersPage.getContent());
        model.addAttribute("stats", stats);

        // Active filters & pagination state
        model.addAttribute("search", search != null ? search : "");
        model.addAttribute("gender", gender != null ? gender : "ALL");
        model.addAttribute("status", status != null ? status : "ALL");
        model.addAttribute("membership", membership != null ? membership : "ALL");
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("totalPages", customersPage.getTotalPages());
        model.addAttribute("totalElements", customersPage.getTotalElements());

        // Modal backing object
        if (!model.containsAttribute("customerForm")) {
            model.addAttribute("customerForm", new CustomerFormDto());
        }

        return "customers";
    }

    @GetMapping("/{id}")
    public String viewCustomer(@PathVariable("id") Long id, Model model) {
        CustomerDetailDto detail = customerService.getCustomerDetail(id);
        model.addAttribute("activeModule", "customers");
        model.addAttribute("pageTitle", "Client Profile - " + detail.getCustomer().getFullName());
        model.addAttribute("detail", detail);
        return "customer-detail";
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<CustomerFormDto> getCustomerApi(@PathVariable("id") Long id) {
        try {
            CustomerFormDto dto = customerService.getCustomerFormById(id);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public String saveCustomer(
            @ModelAttribute("customerForm") CustomerFormDto formDto,
            RedirectAttributes redirectAttributes) {

        if (formDto.getFullName() == null || formDto.getFullName().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Customer full name is required.");
            redirectAttributes.addFlashAttribute("customerForm", formDto);
            return "redirect:/customers";
        }

        if (formDto.getPhone() == null || formDto.getPhone().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mobile number is required.");
            redirectAttributes.addFlashAttribute("customerForm", formDto);
            return "redirect:/customers";
        }

        try {
            CustomerEntity saved = customerService.saveCustomer(formDto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Customer '" + saved.getFullName() + "' has been successfully saved.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("customerForm", formDto);
        } catch (Exception e) {
            log.error("Error saving customer", e);
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while saving customer: " + e.getMessage());
        }

        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'PARLOUR_ADMIN', 'BRANCH_ADMIN')")
    public String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            CustomerService.DeleteResult result = customerService.deleteCustomer(id);
            if (result.isSuccess()) {
                redirectAttributes.addFlashAttribute("successMessage", result.getMessage());
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", result.getMessage());
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            log.error("Error deleting customer ID {}", id, e);
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete customer: " + e.getMessage());
        }
        return "redirect:/customers";
    }
}
