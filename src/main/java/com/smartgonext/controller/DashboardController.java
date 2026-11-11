package com.smartgonext.controller;

import com.smartgonext.service.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final SampleDataService dataService;

    @Autowired
    public DashboardController(SampleDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/dashboard")
    public String dashboard(@org.springframework.web.bind.annotation.RequestParam(value = "accessDenied", required = false) String accessDenied,
                            Model model) {
        if (accessDenied != null) {
            model.addAttribute("accessDeniedError", "Access Restricted: Your role does not have authorization to view the requested module.");
        }
        model.addAttribute("activeModule", "dashboard");
        model.addAttribute("pageTitle", "Salon Performance & Command Center");
        model.addAttribute("metrics", dataService.getDashboardMetrics());
        model.addAttribute("appointments", dataService.getRecentAppointments());
        model.addAttribute("employees", dataService.getEmployees());
        model.addAttribute("recentInvoices", dataService.getRecentInvoices());
        model.addAttribute("notifications", dataService.getNotifications());
        return "dashboard";
    }
}

