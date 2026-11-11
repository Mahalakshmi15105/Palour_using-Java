package com.smartgonext.tenant.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CustomerFormDto {

    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String gender;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate anniversaryDate;

    private String address;
    private String city;
    private String notes;
    private String status = "Active";

    public CustomerFormDto() {}

    public CustomerFormDto(Long id, String fullName, String phone, String email, String gender,
                           LocalDate dateOfBirth, LocalDate anniversaryDate, String address,
                           String city, String notes, String status) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.anniversaryDate = anniversaryDate;
        this.address = address;
        this.city = city;
        this.notes = notes;
        this.status = status != null ? status : "Active";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDate getAnniversaryDate() { return anniversaryDate; }
    public void setAnniversaryDate(LocalDate anniversaryDate) { this.anniversaryDate = anniversaryDate; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
