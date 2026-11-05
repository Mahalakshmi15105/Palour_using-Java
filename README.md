# SmartGoNext – Beauty Parlour & Salon SaaS Management System
## Phase 1: Interactive Prototype & Salon UI/UX Interface

**SmartGoNext** is a modern, enterprise-grade Beauty Parlour and Salon SaaS platform built for single and multi-branch salons, spas, bridal makeup studios, and wellness centers.

---

## 💎 Phase 1 Highlights

- **Professional Salon Login Interface**: Modern glassmorphic login screen with ambient glow, brand identity, and demo access helper.
- **Unified Master Layout**: Responsive navigation shell with collapsible sidebar, active route indicators, badge alerts, and breadcrumbs.
- **Multi-Branch Selector**: Interactive branch switcher in top navigation (Downtown Flagship, Beverly Hills Spa, Uptown Studio).
- **Executive Salon Command Dashboard**:
  - 4 Real-time KPI Cards: Today's Revenue ($3,450.00 / +14.8%), Active Customers (48), Appointments Today (34), and Staff On-Duty (12/14).
  - Interactive Revenue & Category Trend Chart (Chart.js line chart).
  - Real-Time Live Appointments Queue table with status indicators (`In-Chair`, `Waiting`, `Confirmed`, `Completed`).
  - Top Performing Stylists leaderboard with rating badges and monthly contributions.
  - Recent POS Transaction ledger.
- **Dedicated Interactive Pages for All 12 Modules**:
  1. **Dashboard** (`/dashboard`): Salon performance command center.
  2. **Billing (POS)** (`/billing`): Interactive touch POS terminal with category filtering, live cart recalculation (subtotal, tax 8%, discount, total), and instant invoice generation.
  3. **Customers (CRM)** (`/customers`): Client database with VIP tier badges, loyalty points balance, lifetime spend, and "+ Add Client" modal.
  4. **Employees** (`/employees`): Staff directory with role designations, commissions, customer satisfaction ratings, and "+ Onboard Stylist" modal.
  5. **Attendance** (`/attendance`): Daily biometric attendance board, check-in/out stamps, break status, and shift hours.
  6. **Services & Products** (`/services`): Treatment menu catalog with duration minutes, pricing, description, availability switches, and "+ Add Service" modal.
  7. **Membership** (`/membership`): Loyalty packages (Silver, Gold, Platinum VIP, Bridal Privilege) with member perks, tier validity, and pricing.
  8. **Appointments** (`/appointments`): Booking schedule timeline with stylist filters, time-slot management, and "+ Book Appointment" modal.
  9. **Notifications** (`/notifications`): Multi-channel operational alerts for low stock, VIP arrivals, and duty alerts.
  10. **WhatsApp Campaigns** (`/whatsapp`): WhatsApp marketing hub with live smartphone bubble preview, template variables, and delivery rate metrics.
  11. **Reports** (`/reports`): Financial intelligence, daily register balancing, stylist commission ledgers, and profit margin analysis.
  12. **Settings** (`/settings`): Salon profile branding, business hours, tax/GST registration, invoice header/footer templates, and role permissions.

---

## 🛠 Technology Stack

- **Backend**: Java 21 (also compatible with Java 17+), Spring Boot 3.2.x, Spring MVC
- **Frontend / Templating**: Thymeleaf, HTML5, CSS3, JavaScript (ES6+)
- **UI Framework**: Bootstrap 5.3.3, Bootstrap Icons 1.11.3
- **Data Visualizations**: Chart.js 4.4.1
- **Build Tool**: Maven

---

## 🚀 How to Run in IntelliJ IDEA (Step-by-Step)

### Step 1: Open the Project in IntelliJ IDEA
1. Launch **IntelliJ IDEA**.
2. Select **File** &rarr; **Open...** (or click **Open** on the Welcome screen).
3. Navigate to and select this folder:
   ```text
   parlour using java
   ```
4. Click **OK**. Choose **Trust Project** if prompted.

### Step 2: Configure the Java SDK
1. Open **File** &rarr; **Project Structure...** (or press `Ctrl + Alt + Shift + S`).
2. Under **Project Settings**, select **Project**.
3. In the **SDK** dropdown, select your installed **Java 21** (or Java 17+).
   * *If no JDK is listed, click "Add SDK" &rarr; "Download JDK..." &rarr; choose version 21 (Eclipse Temurin or Oracle).*
4. In **Project language level**, select **21 - Sealed types, pattern matching...** (or matching your installed JDK).
5. Click **Apply** and **OK**.

### Step 3: Load Maven Dependencies
1. Open the **Maven** tool window on the right-hand panel (or go to **View** &rarr; **Tool Windows** &rarr; **Maven**).
2. Click the **Reload All Maven Projects** icon (the circular double-arrow 🔄 in the top left of the Maven window).
3. IntelliJ will automatically resolve and download all Spring Boot dependencies specified in `pom.xml`.

### Step 4: Run the Application
1. In the Project file tree, navigate to:
   ```text
   src/main/java/com/smartgonext/SmartGoNextApplication.java
   ```
2. **Right-click** on `SmartGoNextApplication.java` and click **Run 'SmartGoNextApplication'** (or click the green ▶️ play arrow in the gutter next to `public static void main`).
3. The console will display the Spring Boot banner:
   ```text
   Tomcat started on port 8080 (http) with context path ''
   Started SmartGoNextApplication in X.XXX seconds
   ```

### Step 5: Access the Application in Your Browser
1. Open your web browser (Chrome, Edge, Firefox, etc.).
2. Go to:
   ```text
   http://localhost:8080
   ```
3. You will be greeted by the **SmartGoNext Login Page**.
4. **Demo Credentials**:
   - **Work Email**: `admin@smartgonext.com`
   - **Password**: `admin123`
   - *(Or click the "Auto-Login" button or "Access Salon Dashboard")*
5. You will be redirected to the **Salon Dashboard**, where you can explore and click through all 12 modules using the responsive sidebar!

---

## 📁 Project Directory Structure

```text
parlour using java/
├── pom.xml                                      # Maven build file with Spring Boot & Thymeleaf
├── README.md                                    # Project documentation & run guide
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── smartgonext/
│   │   │           ├── SmartGoNextApplication.java    # Main Spring Boot launcher
│   │   │           ├── controller/
│   │   │           │   ├── AuthController.java        # Login, logout, root redirection
│   │   │           │   ├── DashboardController.java   # Main Salon Dashboard
│   │   │           │   └── ModuleController.java      # Controllers for all 11 other modules
│   │   │           ├── model/                         # Domain models & DTOs
│   │   │           │   ├── Appointment.java
│   │   │           │   ├── AttendanceRecord.java
│   │   │           │   ├── CampaignItem.java
│   │   │           │   ├── Customer.java
│   │   │           │   ├── DashboardMetrics.java
│   │   │           │   ├── Employee.java
│   │   │           │   ├── InvoiceItem.java
│   │   │           │   ├── MembershipTier.java
│   │   │           │   ├── NotificationItem.java
│   │   │           │   └── ServiceItem.java
│   │   │           └── service/
│   │   │               └── SampleDataService.java     # Rich mock datasets for all salon modules
│   │   └── resources/
│   │       ├── application.properties                 # Spring Boot server & thymeleaf config
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── styles.css                     # Bespoke luxury salon stylesheet
│   │       │   └── js/
│   │       │       └── main.js                        # Sidebar toggle, POS calculator, Chart.js, toasts
│   │       └── templates/
│   │           ├── login.html                         # Professional salon login screen
│   │           ├── dashboard.html                     # 1. Main Dashboard & KPIs
│   │           ├── billing.html                       # 2. Billing & Point of Sale (POS)
│   │           ├── customers.html                     # 3. Client CRM Directory
│   │           ├── employees.html                     # 4. Staff & Stylists Management
│   │           ├── attendance.html                    # 5. Biometric Attendance & Shifts
│   │           ├── services.html                      # 6. Treatment & Retail Catalog
│   │           ├── membership.html                    # 7. Loyalty Club & VIP Tiers
│   │           ├── appointments.html                  # 8. Booking Calendar & Queue
│   │           ├── notifications.html                 # 9. Operational Alerts
│   │           ├── whatsapp.html                      # 10. WhatsApp Automated Campaigns
│   │           ├── reports.html                       # 11. Financial & Business Analytics
│   │           ├── settings.html                      # 12. Salon & Multi-Branch Settings
│   │           └── fragments/
│   │               └── layout.html                    # Reusable Master App Shell
```

---

## 🔮 Next Phase Roadmap (Phase 2 Preview)

- **Database Connectivity**: Integration with PostgreSQL / MySQL using Spring Data JPA & Hibernate.
- **Spring Security & RBAC**: Role-Based Access Control (Super Admin, Branch Manager, Front Desk Cashier, Stylist).
- **JWT / Session Authentication**: Secure token-based session handling.
- **Live WhatsApp Business Cloud API Integration**: Webhook triggers for real-time appointment confirmations.
- **Payment Gateway Integration**: Razorpay / Stripe for online salon bookings and card swipers.

