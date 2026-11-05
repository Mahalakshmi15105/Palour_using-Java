package com.smartgonext.service;

import com.smartgonext.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SampleDataService {

    public DashboardMetrics getDashboardMetrics() {
        return new DashboardMetrics(
                "$3,450.00",
                "+14.8% vs yesterday",
                48,
                "+8 new clients today",
                34,
                "6 in-chair, 4 upcoming",
                12,
                14
        );
    }

    public List<Appointment> getRecentAppointments() {
        List<Appointment> list = new ArrayList<>();
        list.add(new Appointment("APT-1092", "Sophia Turner", "+1 (555) 234-8901", "Hydra-Glow Facial & Mask", "Priya Sharma", "10:30 AM", "In-Chair", 145.00));
        list.add(new Appointment("APT-1093", "Emma Watson", "+1 (555) 782-9912", "Balayage Highlights & Blowout", "Marcus Chen", "11:15 AM", "Waiting", 220.00));
        list.add(new Appointment("APT-1094", "Olivia Rodrigo", "+1 (555) 431-7721", "Keratin Hair Smoothing", "Chloe Bennett", "11:45 AM", "Confirmed", 185.00));
        list.add(new Appointment("APT-1095", "Isabella Rossi", "+1 (555) 882-1093", "Signature Spa Manicure & Pedicure", "Elena Rostova", "12:00 PM", "Confirmed", 95.00));
        list.add(new Appointment("APT-1096", "Mia Thompson", "+1 (555) 901-4432", "Swedish Deep Tissue Massage", "Chloe Bennett", "01:15 PM", "Completed", 130.00));
        list.add(new Appointment("APT-1097", "Ava Mitchell", "+1 (555) 674-2122", "Bridal Trial Makeup & Styling", "Priya Sharma", "02:00 PM", "Confirmed", 260.00));
        list.add(new Appointment("APT-1098", "Charlotte Davies", "+1 (555) 321-9988", "Volumizing Haircut & Gloss", "Marcus Chen", "02:30 PM", "Waiting", 85.00));
        return list;
    }

    public List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("EMP-101", "Priya Sharma", "Lead Esthetician & Skin Director", "+1 (555) 123-4567", "In Service", 4.95, 118, "$14,850"));
        list.add(new Employee("EMP-102", "Marcus Chen", "Creative Hair Director", "+1 (555) 234-5678", "In Service", 4.92, 134, "$18,220"));
        list.add(new Employee("EMP-103", "Elena Rostova", "Senior Nail & Lash Artist", "+1 (555) 345-6789", "Active", 4.88, 96, "$9,400"));
        list.add(new Employee("EMP-104", "Chloe Bennett", "Ayurvedic Spa & Wellness Therapist", "+1 (555) 456-7890", "Active", 4.91, 84, "$11,200"));
        list.add(new Employee("EMP-105", "David Vance", "Men's Grooming Specialist", "+1 (555) 567-8901", "On Break", 4.85, 78, "$8,150"));
        list.add(new Employee("EMP-106", "Amara Okafor", "Colorist & Texture Specialist", "+1 (555) 678-9012", "Leave", 4.90, 88, "$12,300"));
        list.add(new Employee("EMP-107", "Hannah Abbott", "Junior Stylist & Blowout Bar", "+1 (555) 789-0123", "Active", 4.79, 62, "$5,900"));
        return list;
    }

    public List<Customer> getCustomers() {
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("CUST-501", "Sophia Turner", "+1 (555) 234-8901", "sophia.t@example.com", "Platinum VIP", 1450, 24, 3850.00, "Today"));
        list.add(new Customer("CUST-502", "Emma Watson", "+1 (555) 782-9912", "emma.w@example.com", "Gold Club", 920, 16, 2180.00, "Yesterday"));
        list.add(new Customer("CUST-503", "Olivia Rodrigo", "+1 (555) 431-7721", "olivia.r@example.com", "Platinum VIP", 1890, 31, 4920.00, "3 days ago"));
        list.add(new Customer("CUST-504", "Isabella Rossi", "+1 (555) 882-1093", "isabella.r@example.com", "Silver Elite", 480, 8, 940.00, "Today"));
        list.add(new Customer("CUST-505", "Mia Thompson", "+1 (555) 901-4432", "mia.t@example.com", "Gold Club", 760, 12, 1650.00, "Today"));
        list.add(new Customer("CUST-506", "Ava Mitchell", "+1 (555) 674-2122", "ava.m@example.com", "Bridal Privilege", 2400, 19, 5200.00, "1 week ago"));
        list.add(new Customer("CUST-507", "Charlotte Davies", "+1 (555) 321-9988", "charlotte.d@example.com", "Walk-in", 120, 2, 190.00, "Today"));
        return list;
    }

    public List<ServiceItem> getServices() {
        List<ServiceItem> list = new ArrayList<>();
        list.add(new ServiceItem("SRV-01", "Keratin Smoothing Therapy", "Hair", 120, 185.00, "Intense anti-frizz formalde-free smoothing treatment.", true));
        list.add(new ServiceItem("SRV-02", "Balayage & Gloss Finish", "Hair", 150, 220.00, "Hand-painted dimensional highlights with customized gloss tone.", true));
        list.add(new ServiceItem("SRV-03", "Hydra-Dew Facial Deluxe", "Skin & Facial", 60, 145.00, "Deep pore ultrasonic extraction with botanical hyaluronic infusion.", true));
        list.add(new ServiceItem("SRV-04", "Luxury Russian Manicure", "Nails & Bridal", 75, 95.00, "Precision dry e-file cuticle care and builder gel manicure.", true));
        list.add(new ServiceItem("SRV-05", "Swedish Aromatherapy Massage", "Spa & Massage", 90, 130.00, "Full body relaxing pressure massage with essential oils.", true));
        list.add(new ServiceItem("SRV-06", "Bridal Royale Makeup & Hair", "Nails & Bridal", 180, 260.00, "Complete HD airbrush bridal makeup, jewelry setting, & hairstyle.", true));
        list.add(new ServiceItem("SRV-07", "Moroccan Oil Scalp Ritual", "Hair", 45, 75.00, "Warm argan oil scalp detox and therapeutic head massage.", true));
        list.add(new ServiceItem("SRV-08", "Collagen Eye Lift & Peel", "Skin & Facial", 40, 85.00, "Targeted fine-line reduction and gentle botanical peel.", true));
        return list;
    }

    public List<MembershipTier> getMembershipTiers() {
        List<MembershipTier> list = new ArrayList<>();
        list.add(new MembershipTier("MEM-01", "Silver Elite", 149.00, 6, 10,
                Arrays.asList("10% off all hair services", "1 complimentary blowout/quarter", "Priority weekend booking"), 142, "#94a3b8"));
        list.add(new MembershipTier("MEM-02", "Gold Club", 299.00, 12, 15,
                Arrays.asList("15% off all services & retail", "2 complimentary facial treatments", "Free birthday grooming package", "Priority VIP stylist reservation"), 285, "#f59e0b"));
        list.add(new MembershipTier("MEM-03", "Platinum VIP", 499.00, 12, 20,
                Arrays.asList("20% off all services", "Unlimited complimentary blowouts", "Complimentary champagne & valet", "Dedicated VIP private suite access"), 98, "#8b5cf6"));
        list.add(new MembershipTier("MEM-04", "Bridal Privilege", 399.00, 6, 15,
                Arrays.asList("Special bridal party discounts", "Free pre-wedding skin consultation", "Free makeup trial & nail art bundle"), 64, "#ec4899"));
        return list;
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        List<AttendanceRecord> list = new ArrayList<>();
        list.add(new AttendanceRecord("ATT-1", "Priya Sharma", "Lead Esthetician", "09:02 AM", "In Progress", "Present", 6.8));
        list.add(new AttendanceRecord("ATT-2", "Marcus Chen", "Creative Hair Director", "08:55 AM", "In Progress", "Present", 7.0));
        list.add(new AttendanceRecord("ATT-3", "Elena Rostova", "Senior Nail Artist", "09:12 AM", "In Progress", "Late", 6.6));
        list.add(new AttendanceRecord("ATT-4", "Chloe Bennett", "Spa Therapist", "08:48 AM", "In Progress", "Present", 7.2));
        list.add(new AttendanceRecord("ATT-5", "David Vance", "Men's Grooming", "09:00 AM", "In Progress", "Present", 6.9));
        list.add(new AttendanceRecord("ATT-6", "Hannah Abbott", "Junior Stylist", "09:05 AM", "In Progress", "Present", 6.8));
        list.add(new AttendanceRecord("ATT-7", "Amara Okafor", "Colorist", "--", "--", "On Leave", 0.0));
        return list;
    }

    public List<InvoiceItem> getRecentInvoices() {
        List<InvoiceItem> list = new ArrayList<>();
        list.add(new InvoiceItem("INV-2026-081", "Sophia Turner", "05 Nov, 11:45 AM", "Hydra Facial, Nail Art", 240.00, "Credit Card", "Paid"));
        list.add(new InvoiceItem("INV-2026-080", "Emma Watson", "05 Nov, 11:10 AM", "Balayage, Olaplex Treatment", 270.00, "UPI / Digital", "Paid"));
        list.add(new InvoiceItem("INV-2026-079", "Mia Thompson", "05 Nov, 10:35 AM", "Swedish Massage (90m)", 130.00, "Cash", "Paid"));
        list.add(new InvoiceItem("INV-2026-078", "David Miller", "05 Nov, 10:05 AM", "Men's Executive Haircut & Beard", 65.00, "Credit Card", "Paid"));
        list.add(new InvoiceItem("INV-2026-077", "Ava Mitchell", "05 Nov, 09:40 AM", "Bridal Consultation & Facial", 195.00, "UPI / Digital", "Paid"));
        return list;
    }

    public List<NotificationItem> getNotifications() {
        List<NotificationItem> list = new ArrayList<>();
        list.add(new NotificationItem("NOTIF-1", "Inventory Alert: Olaplex No. 3", "Only 3 bottles remaining in Downtown branch backbar.", "Inventory", "12 min ago", false, "High"));
        list.add(new NotificationItem("NOTIF-2", "VIP Client Arrival", "Platinum VIP member Olivia Rodrigo arrived for 11:45 AM appointment.", "Appointment", "28 min ago", false, "Normal"));
        list.add(new NotificationItem("NOTIF-3", "High Revenue Milestone", "Downtown branch exceeded $3,000 revenue target by 2:00 PM.", "Billing", "1 hr ago", true, "Info"));
        list.add(new NotificationItem("NOTIF-4", "WhatsApp Broadcast Delivered", "Weekend Glamour 20% discount campaign delivered to 480 VIPs.", "System", "2 hrs ago", true, "Normal"));
        return list;
    }

    public List<CampaignItem> getCampaigns() {
        List<CampaignItem> list = new ArrayList<>();
        list.add(new CampaignItem("CAM-101", "Festive Glow – 20% Off Facials", "Inactive Clients (>45 days)", 480, "98.2%", "Sent", "Nov 04, 2026", "Hey {{name}}! We miss pampering you. Book any facial this week and enjoy flat 20% off with code GLOW20. Reply YES to book!"));
        list.add(new CampaignItem("CAM-102", "Birthday Royale Treat", "November Birthday Clients", 125, "99.1%", "Scheduled", "Nov 07, 2026", "Happy Birthday Month {{name}}! 🎂 Enjoy a complimentary Moroccan scalp ritual on your next salon visit. Show this message at reception."));
        list.add(new CampaignItem("CAM-103", "Bridal Season 2026 Preview", "Registered Brides & Enquiries", 210, "96.5%", "Sent", "Nov 01, 2026", "Congratulations on your upcoming big day {{name}}! Our Master Stylists are offering complimentary trial slots this Sunday. Reserve your spot now."));
        list.add(new CampaignItem("CAM-104", "Weekend Hair Transformation Blast", "All Loyal Members", 850, "--", "Draft", "Nov 09, 2026", "Ready for a weekend transformation? Get a free Olaplex bond repair treatment with any Balayage or Global hair color!"));
        return list;
    }
}

