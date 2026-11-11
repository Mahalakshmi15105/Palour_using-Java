-- ========================================================
-- SmartGoNext Salon SaaS: Seed Initial Data for parlour_java
-- ========================================================

USE parlour_java;

-- 1. Branch
INSERT IGNORE INTO branches (id, tenant_id, name, address, phone, email, is_main_branch, latitude, longitude, geofence_radius_meters, wifi_ssid, initial_opening_balance) VALUES
(1, 1, 'Downtown Flagship Lounge', '742 Evergreen Promenade, Suite 100, Downtown', '+1 (555) 789-2233', 'downtown@smartgonext.com', TRUE, 40.7128, -74.0060, 100, 'SmartGoNext_Guest_WiFi', 500.00),
(2, 1, 'Beverly Hills Luxury Spa', '90210 Wilshire Blvd, Beverly Hills', '+1 (555) 789-4455', 'beverly@smartgonext.com', FALSE, 34.0736, -118.4004, 150, 'SmartGoNext_BH_WiFi', 300.00),
(3, 1, 'Uptown Express Studio', '120 Lexington Ave, Uptown', '+1 (555) 789-6677', 'uptown@smartgonext.com', FALSE, 40.7831, -73.9712, 80, 'SmartGoNext_Uptown_WiFi', 200.00);

-- 2. Tenant Settings
INSERT IGNORE INTO tenant_settings (id, tenant_id, logo_url, gst_number, tax_rate, invoice_prefix, receipt_template, currency_symbol, primary_color, booking_enabled, working_days, opening_time, closing_time) VALUES
(1, 1, '/images/logo.svg', 'GSTIN-33AAECS1234F1Z5', 8.00, 'INV-', 'Classic', '$', '#d946ef', TRUE, 'Mon,Tue,Wed,Thu,Fri,Sat,Sun', '09:00:00', '21:00:00');

-- 3. Staff Users
INSERT IGNORE INTO users (id, tenant_id, branch_id, email, password_hash, full_name, role, status) VALUES
(1, 1, 1, 'admin@smartgonext.com', 'admin123', 'Mahalakshmi', 'ParlourAdmin', 'ACTIVE'),
(2, 1, 1, 'priya.sharma@smartgonext.com', 'staff123', 'Priya Sharma', 'BranchAdmin', 'ACTIVE'),
(3, 1, 1, 'reception@smartgonext.com', 'staff123', 'Front Desk Reception', 'Receptionist', 'ACTIVE');

-- 4. Service Categories
INSERT IGNORE INTO service_categories (id, tenant_id, branch_id, name, description) VALUES
(1, 1, 1, 'Hair Services', 'Cut, style, coloring, keratin and scalp treatments'),
(2, 1, 1, 'Skin & Facial', 'Deep cleansing, anti-aging, acne, and botanical facials'),
(3, 1, 1, 'Nails & Bridal', 'Russian manicure, nail art, bridal makeover and styling'),
(4, 1, 1, 'Spa & Wellness', 'Aromatherapy, Swedish deep tissue massage, and scrubs');

-- 5. Services
INSERT IGNORE INTO services (id, category_id, name, price, duration_minutes, status, description) VALUES
(1, 1, 'Keratin Smoothing Therapy', 185.00, 120, 'ACTIVE', 'Intense anti-frizz formalde-free smoothing treatment'),
(2, 1, 'Balayage Highlights & Blowout', 220.00, 150, 'ACTIVE', 'Hand-painted dimensional color with customized gloss'),
(3, 2, 'Hydra-Glow Facial & Mask', 145.00, 60, 'ACTIVE', 'Deep pore extraction with botanical hyaluronic infusion'),
(4, 3, 'Luxury Russian Manicure', 95.00, 75, 'ACTIVE', 'Dry e-file precision cuticle care with gel overlay'),
(5, 4, 'Swedish Deep Tissue Massage', 130.00, 90, 'ACTIVE', 'Full body tension relief massage with therapeutic oils'),
(6, 3, 'Bridal Royale Makeup & Styling', 260.00, 180, 'ACTIVE', 'Airbrush HD makeup, jewelry draping, and hairstyle'),
(7, 1, 'Volumizing Haircut & Gloss', 85.00, 45, 'ACTIVE', 'Customized precision cut with shine blowout');

-- 6. Products
INSERT IGNORE INTO products (id, name, sku, barcode, cost_price, selling_price, mrp, stock_quantity, low_stock_threshold, status) VALUES
(1, 'Olaplex No. 3 Hair Perfector (100ml)', 'OLP-003', '8964521092', 18.00, 30.00, 32.00, 8, 5, 'ACTIVE'),
(2, 'Moroccanoil Treatment Original (100ml)', 'MOR-100', '8964521093', 25.00, 48.00, 50.00, 14, 5, 'ACTIVE'),
(3, 'Kerastase Elixir Ultime Hair Oil', 'KER-ULT', '8964521094', 32.00, 58.00, 60.00, 6, 3, 'ACTIVE'),
(4, 'Dermalogica Daily Microfoliant', 'DER-MIC', '8964521095', 35.00, 65.00, 68.00, 12, 4, 'ACTIVE');

-- 7. Employees
INSERT IGNORE INTO employees (id, branch_id, first_name, last_name, phone, role, salary, target, commission_percentage, shift_start_time, shift_end_time, monthly_offs, status) VALUES
(1, 1, 'Priya', 'Sharma', '+1 (555) 123-4567', 'Lead Esthetician & Skin Director', 3500.00, 12000.00, 15.00, '09:00:00', '18:00:00', 4, 'Active'),
(2, 1, 'Marcus', 'Chen', '+1 (555) 234-5678', 'Creative Hair Director', 4000.00, 15000.00, 18.00, '09:00:00', '18:00:00', 4, 'Active'),
(3, 1, 'Elena', 'Rostova', '+1 (555) 345-6789', 'Senior Nail & Lash Artist', 2800.00, 8000.00, 12.00, '10:00:00', '19:00:00', 4, 'Active'),
(4, 1, 'Chloe', 'Bennett', '+1 (555) 456-7890', 'Ayurvedic Spa Therapist', 3000.00, 9500.00, 14.00, '10:00:00', '19:00:00', 4, 'Active');

-- 8. Customers
INSERT IGNORE INTO customers (id, first_name, last_name, phone, email, gender, date_of_birth, address) VALUES
(1, 'Sophia', 'Turner', '+1 (555) 234-8901', 'sophia.t@example.com', 'Female', '1996-02-21', '88 Fifth Avenue, Apt 4B'),
(2, 'Emma', 'Watson', '+1 (555) 782-9912', 'emma.w@example.com', 'Female', '1990-04-15', '142 Mercer Street, Suite 9'),
(3, 'Olivia', 'Rodrigo', '+1 (555) 431-7721', 'olivia.r@example.com', 'Female', '2003-02-20', '520 Broadway'),
(4, 'Isabella', 'Rossi', '+1 (555) 882-1093', 'isabella.r@example.com', 'Female', '1994-08-11', '19 Grand Street');

-- 9. Membership Plans
INSERT IGNORE INTO membership_plans (id, name, price, validity_months, service_discount_percent, product_discount_percent, description, status) VALUES
(1, 'Silver Elite', 149.00, 6, 10.00, 5.00, '10% off services and priority booking', 'Active'),
(2, 'Gold Club', 299.00, 12, 15.00, 10.00, '15% off all services, 2 free facials', 'Active'),
(3, 'Platinum VIP', 499.00, 12, 20.00, 15.00, '20% off all services, private VIP suite', 'Active');

