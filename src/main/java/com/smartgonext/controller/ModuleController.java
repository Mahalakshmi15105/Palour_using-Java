package com.smartgonext.controller;

import com.smartgonext.service.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModuleController {

    private final SampleDataService dataService;

    @Autowired
    public ModuleController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/billing")
    public String billing(Model model) {
        model.addAttribute("activeModule", "billing");
        model.addAttribute("pageTitle", "Billing & Point of Sale (POS)");
        model.addAttribute("services", dataService.getServices());
        model.addAttribute("customers", dataService.getCustomers());
        model.addAttribute("recentInvoices", dataService.getRecentInvoices());
        return "billing";
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("activeModule", "customers");
        model.addAttribute("pageTitle", "Client Relationship Management (CRM)");
        model.addAttribute("customers", dataService.getCustomers());
        return "customers";
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("activeModule", "employees");
        model.addAttribute("pageTitle", "Staff & Stylists Directory");
        model.addAttribute("employees", dataService.getEmployees());
        return "employees";
    }

    @GetMapping("/attendance")
    public String attendance(Model model) {
        model.addAttribute("activeModule", "attendance");
        model.addAttribute("pageTitle", "Staff Attendance & Duty Shifts");
        model.addAttribute("records", dataService.getAttendanceRecords());
        return "attendance";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("activeModule", "services");
        model.addAttribute("pageTitle", "Services & Retail Products Catalog");
        model.addAttribute("services", dataService.getServices());
        return "services";
    }

    @GetMapping("/membership")
    public String membership(Model model) {
        model.addAttribute("activeModule", "membership");
        model.addAttribute("pageTitle", "Loyalty & Membership Club");
        model.addAttribute("tiers", dataService.getMembershipTiers());
        model.addAttribute("vipCustomers", dataService.getCustomers());
        return "membership";
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("activeModule", "appointments");
        model.addAttribute("pageTitle", "Appointment Calendar & Booking Manager");
        model.addAttribute("appointments", dataService.getRecentAppointments());
        model.addAttribute("employees", dataService.getEmployees());
        model.addAttribute("services", dataService.getServices());
        return "appointments";
    }

    @GetMapping("/notifications")
    public String notifications(Model model) {
        model.addAttribute("activeModule", "notifications");
        model.addAttribute("pageTitle", "Notifications & System Alerts");
        model.addAttribute("notifications", dataService.getNotifications());
        return "notifications";
    }

    @GetMapping("/whatsapp")
    public String whatsapp(Model model) {
        model.addAttribute("activeModule", "whatsapp");
        model.addAttribute("pageTitle", "WhatsApp Marketing & Automated Campaigns");
        model.addAttribute("campaigns", dataService.getCampaigns());
        return "whatsapp";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("activeModule", "reports");
        model.addAttribute("pageTitle", "Analytics & Business Intelligence Reports");
        model.addAttribute("metrics", dataService.getDashboardMetrics());
        return "reports";
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        model.addAttribute("activeModule", "settings");
        model.addAttribute("pageTitle", "Salon Configuration & System Settings");
        return "settings";
    }
}

