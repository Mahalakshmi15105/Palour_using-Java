-- ========================================================
-- SmartGoNext Salon SaaS: Tenant Schema (parlour_java)
-- All 24+ Operational Salon Tables with FKs & Indexes
-- ========================================================

USE parlour_java;

-- 1. Branches (Multi-Branch, Coordinates, Wi-Fi Geofence)
CREATE TABLE IF NOT EXISTS branches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    address TEXT,
    phone VARCHAR(25),
    email VARCHAR(120),
    is_main_branch BOOLEAN NOT NULL DEFAULT FALSE,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    geofence_radius_meters INT,
    wifi_ssid VARCHAR(100),
    wifi_public_ip VARCHAR(50),
    initial_opening_balance DECIMAL(10, 2) DEFAULT 0.00,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Tenant Users (Salon Staff Logins)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    branch_id BIGINT,
    email VARCHAR(120) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_users_branch FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Tenant Settings (Branding, Taxes, GST, Printer)
CREATE TABLE IF NOT EXISTS tenant_settings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    logo_url VARCHAR(300),
    gst_number VARCHAR(50),
    tax_rate DECIMAL(5, 2) DEFAULT 8.00,
    invoice_prefix VARCHAR(20) DEFAULT 'INV-',
    receipt_template VARCHAR(30) DEFAULT 'Classic',
    currency_symbol VARCHAR(10) DEFAULT '$',
    primary_color VARCHAR(20) DEFAULT '#d946ef',
    booking_enabled BOOLEAN DEFAULT TRUE,
    working_days VARCHAR(100) DEFAULT 'Mon,Tue,Wed,Thu,Fri,Sat,Sun',
    opening_time TIME DEFAULT '09:00:00',
    closing_time TIME DEFAULT '21:00:00',
    google_drive_access_token TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 4. Service Categories
CREATE TABLE IF NOT EXISTS service_categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tenant_id BIGINT NOT NULL,
    branch_id BIGINT,
    name VARCHAR(100) NOT NULL,
    description TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. Services
CREATE TABLE IF NOT EXISTS services (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    duration_minutes INT NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    image_url VARCHAR(300),
    description TEXT,
    CONSTRAINT fk_services_category FOREIGN KEY (category_id) REFERENCES service_categories(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 6. Products (Retail & Backbar Inventory)
CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    sku VARCHAR(50) UNIQUE,
    barcode VARCHAR(50),
    cost_price DECIMAL(10, 2),
    selling_price DECIMAL(10, 2) NOT NULL,
    mrp DECIMAL(10, 2),
    stock_quantity INT NOT NULL DEFAULT 0,
    low_stock_threshold INT DEFAULT 5,
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 7. Suppliers
CREATE TABLE IF NOT EXISTS suppliers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    contact_name VARCHAR(100),
    phone VARCHAR(25),
    email VARCHAR(120),
    address TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 8. Stock Reorder Logs
CREATE TABLE IF NOT EXISTS stock_reorder_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    supplier_id BIGINT,
    quantity INT NOT NULL,
    cost_price DECIMAL(10, 2),
    status VARCHAR(30) NOT NULL DEFAULT 'Pending',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_stock_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    CONSTRAINT fk_stock_supplier FOREIGN KEY (supplier_id) REFERENCES suppliers(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 9. Customers (CRM)
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80),
    phone VARCHAR(25) NOT NULL UNIQUE,
    email VARCHAR(120),
    gender VARCHAR(20),
    date_of_birth DATE,
    address TEXT,
    notes TEXT,
    spot VARCHAR(50),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 10. Employees (Stylists, Beauticians, Therapists)
CREATE TABLE IF NOT EXISTS employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_id BIGINT NOT NULL,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80),
    phone VARCHAR(25) NOT NULL,
    role VARCHAR(80) NOT NULL,
    salary DECIMAL(10, 2),
    target DECIMAL(10, 2),
    commission_percentage DECIMAL(5, 2) DEFAULT 0.00,
    shift_start_time TIME,
    shift_end_time TIME,
    monthly_offs INT DEFAULT 4,
    status VARCHAR(30) NOT NULL DEFAULT 'Active',
    CONSTRAINT fk_employees_branch FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 11. Appointments
CREATE TABLE IF NOT EXISTS appointments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_number VARCHAR(50) NOT NULL UNIQUE,
    customer_id BIGINT NOT NULL,
    customer_name VARCHAR(150),
    customer_phone VARCHAR(25),
    appointment_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME,
    token_number INT,
    total_amount DECIMAL(10, 2),
    booking_source VARCHAR(50) DEFAULT 'Walk-in',
    status VARCHAR(30) NOT NULL DEFAULT 'Booked',
    notes TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_appointments_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 12. Appointment Items
CREATE TABLE IF NOT EXISTS appointment_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    employee_id BIGINT,
    price DECIMAL(10, 2) NOT NULL,
    duration_minutes INT NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'Booked',
    CONSTRAINT fk_apt_items_appointment FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE CASCADE,
    CONSTRAINT fk_apt_items_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
    CONSTRAINT fk_apt_items_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 13. Invoices (POS Financial Master)
CREATE TABLE IF NOT EXISTS invoices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_number VARCHAR(50) NOT NULL UNIQUE,
    customer_id BIGINT,
    branch_id BIGINT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    discount DECIMAL(10, 2) DEFAULT 0.00,
    tax DECIMAL(10, 2) DEFAULT 0.00,
    total DECIMAL(10, 2) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'Paid',
    membership_name VARCHAR(100),
    membership_discount DECIMAL(10, 2) DEFAULT 0.00,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_invoices_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE SET NULL,
    CONSTRAINT fk_invoices_branch FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 14. Invoice Line Items (Services & Products billed)
CREATE TABLE IF NOT EXISTS invoice_line_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    service_id BIGINT,
    product_id BIGINT,
    employee_id BIGINT,
    quantity INT NOT NULL DEFAULT 1,
    unit_price DECIMAL(10, 2) NOT NULL,
    mrp DECIMAL(10, 2),
    discount_amount DECIMAL(10, 2) DEFAULT 0.00,
    tax_amount DECIMAL(10, 2) DEFAULT 0.00,
    line_total DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_inv_items_invoice FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE,
    CONSTRAINT fk_inv_items_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE SET NULL,
    CONSTRAINT fk_inv_items_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE SET NULL,
    CONSTRAINT fk_inv_items_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 15. Invoice Payments (Split payment: cash, card, online UPI)
CREATE TABLE IF NOT EXISTS invoice_payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    method VARCHAR(30) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_reference VARCHAR(100),
    payment_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_inv_payments_invoice FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 16. Reminders
CREATE TABLE IF NOT EXISTS reminders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    invoice_id BIGINT,
    reminder_type VARCHAR(80) NOT NULL,
    reminder_date DATE NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'Pending',
    message TEXT,
    CONSTRAINT fk_reminders_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    CONSTRAINT fk_reminders_invoice FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 17. Customer Feedback
CREATE TABLE IF NOT EXISTS customer_feedback (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    invoice_id BIGINT,
    rating INT NOT NULL,
    comments TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_feedback_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    CONSTRAINT fk_feedback_invoice FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 18. Membership Plans (Type A)
CREATE TABLE IF NOT EXISTS membership_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    validity_months INT NOT NULL,
    service_discount_percent DECIMAL(5, 2) DEFAULT 0.00,
    product_discount_percent DECIMAL(5, 2) DEFAULT 0.00,
    description TEXT,
    status VARCHAR(30) NOT NULL DEFAULT 'Active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 19. Membership Plan Services (Eligible Services)
CREATE TABLE IF NOT EXISTS membership_plan_services (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    membership_plan_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    discount_percent DECIMAL(5, 2),
    CONSTRAINT fk_plan_services_plan FOREIGN KEY (membership_plan_id) REFERENCES membership_plans(id) ON DELETE CASCADE,
    CONSTRAINT fk_plan_services_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 20. Customer Memberships (Purchased Plans)
CREATE TABLE IF NOT EXISTS customer_memberships (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    membership_plan_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    expires_at DATE NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'Active',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cust_mem_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    CONSTRAINT fk_cust_mem_plan FOREIGN KEY (membership_plan_id) REFERENCES membership_plans(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 21. Membership Benefits (Complimentary service balances)
CREATE TABLE IF NOT EXISTS membership_benefits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_membership_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    total_quantity INT NOT NULL,
    remaining_quantity INT NOT NULL,
    CONSTRAINT fk_mem_benefits_mem FOREIGN KEY (customer_membership_id) REFERENCES customer_memberships(id) ON DELETE CASCADE,
    CONSTRAINT fk_mem_benefits_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 22. Visit Membership Settings (Type B)
CREATE TABLE IF NOT EXISTS visit_membership_settings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    visits_required INT NOT NULL,
    reward_service_id BIGINT NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'Active',
    CONSTRAINT fk_visit_settings_service FOREIGN KEY (reward_service_id) REFERENCES services(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 23. Customer Visit Counters
CREATE TABLE IF NOT EXISTS customer_visit_counters (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL UNIQUE,
    current_visit_count INT NOT NULL DEFAULT 0,
    total_free_services_claimed INT NOT NULL DEFAULT 0,
    last_visit_date DATE,
    CONSTRAINT fk_visit_counters_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 24. Customer Points Counters (Type C)
CREATE TABLE IF NOT EXISTS customer_points_counters (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL UNIQUE,
    current_points_balance INT NOT NULL DEFAULT 0,
    total_points_earned INT NOT NULL DEFAULT 0,
    total_points_redeemed INT NOT NULL DEFAULT 0,
    last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_points_counters_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 25. Attendances (Check-in, geofence, distance, shift status)
CREATE TABLE IF NOT EXISTS attendances (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    branch_id BIGINT NOT NULL,
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    checkin_method VARCHAR(30) DEFAULT 'Manual',
    location_flagged BOOLEAN DEFAULT FALSE,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    distance_meters DOUBLE,
    check_out_time TIME,
    status VARCHAR(10) NOT NULL DEFAULT 'P',
    CONSTRAINT fk_attendances_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE,
    CONSTRAINT fk_attendances_branch FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 26. Payroll Adjustments (Advances & Deductions)
CREATE TABLE IF NOT EXISTS payroll_adjustments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    type VARCHAR(30) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    note TEXT,
    date DATE NOT NULL,
    CONSTRAINT fk_payroll_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 27. Expenses (Petty cash daily)
CREATE TABLE IF NOT EXISTS expenses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    note TEXT,
    category VARCHAR(80),
    date DATE NOT NULL,
    created_by VARCHAR(100),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_expenses_branch FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 28. Cash Denominations (End-of-day register tally)
CREATE TABLE IF NOT EXISTS cash_denominations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_id BIGINT NOT NULL,
    date DATE NOT NULL,
    count_500 INT DEFAULT 0,
    count_200 INT DEFAULT 0,
    count_100 INT DEFAULT 0,
    count_50 INT DEFAULT 0,
    count_20 INT DEFAULT 0,
    count_10 INT DEFAULT 0,
    count_5 INT DEFAULT 0,
    count_2 INT DEFAULT 0,
    count_1 INT DEFAULT 0,
    total DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    opening_balance DECIMAL(10, 2) DEFAULT 0.00,
    CONSTRAINT fk_cash_denominations_branch FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 29. WhatsApp Settings (Meta Cloud API credentials)
CREATE TABLE IF NOT EXISTS whatsapp_settings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meta_waba_id VARCHAR(100),
    meta_phone_number_id VARCHAR(100),
    encrypted_access_token TEXT,
    status VARCHAR(30) DEFAULT 'Inactive'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 30. WhatsApp Campaigns
CREATE TABLE IF NOT EXISTS whatsapp_campaigns (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    target_audience VARCHAR(100),
    message_template TEXT NOT NULL,
    scheduled_at DATETIME,
    status VARCHAR(30) NOT NULL DEFAULT 'Draft',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 31. WhatsApp Campaign Recipients
CREATE TABLE IF NOT EXISTS whatsapp_campaign_recipients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    campaign_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    customer_phone VARCHAR(25),
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    sent_at DATETIME,
    CONSTRAINT fk_campaign_recipients_campaign FOREIGN KEY (campaign_id) REFERENCES whatsapp_campaigns(id) ON DELETE CASCADE,
    CONSTRAINT fk_campaign_recipients_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 32. Notifications
CREATE TABLE IF NOT EXISTS notifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    message TEXT NOT NULL,
    category VARCHAR(50) DEFAULT 'System',
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    priority VARCHAR(30) DEFAULT 'Normal',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 33. Audit Logs
CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    action VARCHAR(100) NOT NULL,
    resource_name VARCHAR(100),
    details TEXT,
    timestamp DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
