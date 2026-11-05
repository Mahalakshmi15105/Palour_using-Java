/**
 * SmartGoNext - Beauty Parlour & Salon SaaS Management System
 * Front-end Scripts & Interactive Behaviors
 */

document.addEventListener('DOMContentLoaded', function () {
    // -------------------------------------------------------------
    // 1. Sidebar Toggle (Mobile & Desktop)
    // -------------------------------------------------------------
    const sidebarToggleBtn = document.getElementById('sidebarToggleBtn');
    const appSidebar = document.getElementById('appSidebar');
    const sidebarOverlay = document.getElementById('sidebarOverlay');

    if (sidebarToggleBtn && appSidebar) {
        sidebarToggleBtn.addEventListener('click', function () {
            appSidebar.classList.toggle('show');
            if (sidebarOverlay) {
                sidebarOverlay.classList.toggle('show');
            }
        });
    }

    if (sidebarOverlay && appSidebar) {
        sidebarOverlay.addEventListener('click', function () {
            appSidebar.classList.remove('show');
            sidebarOverlay.classList.remove('show');
        });
    }

    // -------------------------------------------------------------
    // 2. Branch Selector Switcher
    // -------------------------------------------------------------
    const branchOptions = document.querySelectorAll('.branch-option');
    const currentBranchName = document.getElementById('currentBranchName');

    if (branchOptions && currentBranchName) {
        branchOptions.forEach(option => {
            option.addEventListener('click', function (e) {
                e.preventDefault();
                const selectedBranch = this.getAttribute('data-branch');
                currentBranchName.textContent = selectedBranch;
                showToast(`Switched salon branch to "${selectedBranch}"`, 'success');
            });
        });
    }

    // -------------------------------------------------------------
    // 3. Quick Toast Notification Helper
    // -------------------------------------------------------------
    window.showToast = function (message, type = 'info') {
        let toastContainer = document.getElementById('toastContainer');
        if (!toastContainer) {
            toastContainer = document.createElement('div');
            toastContainer.id = 'toastContainer';
            toastContainer.className = 'toast-container position-fixed bottom-0 end-0 p-3';
            toastContainer.style.zIndex = '9999';
            document.body.appendChild(toastContainer);
        }

        const toastId = 'toast_' + Date.now();
        const iconClass = type === 'success' ? 'bi-check-circle-fill text-success' :
                          type === 'error' ? 'bi-x-circle-fill text-danger' : 'bi-info-circle-fill text-primary';

        const toastHtml = `
            <div id="${toastId}" class="toast align-items-center bg-white border shadow-lg" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="d-flex">
                    <div class="toast-body d-flex align-items-center gap-2">
                        <i class="bi ${iconClass} fs-5"></i>
                        <span class="fw-semibold text-dark">${message}</span>
                    </div>
                    <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        `;

        toastContainer.insertAdjacentHTML('beforeend', toastHtml);
        const toastEl = document.getElementById(toastId);
        if (typeof bootstrap !== 'undefined' && bootstrap.Toast) {
            const toast = new bootstrap.Toast(toastEl, { delay: 3500 });
            toast.show();
            toastEl.addEventListener('hidden.bs.toast', () => toastEl.remove());
        }
    };

    // -------------------------------------------------------------
    // 4. POS (Billing) Cart Engine (Interactive Demo)
    // -------------------------------------------------------------
    const posItems = document.querySelectorAll('.pos-item-clickable');
    const posCartList = document.getElementById('posCartList');
    const posSubtotal = document.getElementById('posSubtotal');
    const posTax = document.getElementById('posTax');
    const posTotal = document.getElementById('posTotal');
    const posCheckoutBtn = document.getElementById('posCheckoutBtn');
    const posClearBtn = document.getElementById('posClearBtn');

    let cart = [];

    function updateCartUI() {
        if (!posCartList) return;

        if (cart.length === 0) {
            posCartList.innerHTML = `
                <div class="text-center py-5 text-muted">
                    <i class="bi bi-cart-x fs-1 opacity-25"></i>
                    <p class="small mt-2 mb-0">No services or products selected.<br>Click items from the catalog to add.</p>
                </div>
            `;
            if (posSubtotal) posSubtotal.textContent = '$0.00';
            if (posTax) posTax.textContent = '$0.00';
            if (posTotal) posTotal.textContent = '$0.00';
            if (posCheckoutBtn) posCheckoutBtn.disabled = true;
            return;
        }

        let subtotal = 0;
        posCartList.innerHTML = '';

        cart.forEach((item, index) => {
            const itemTotal = item.price * item.quantity;
            subtotal += itemTotal;

            const itemRow = document.createElement('div');
            itemRow.className = 'd-flex align-items-center justify-content-between p-2 mb-2 bg-light rounded-3';
            itemRow.innerHTML = `
                <div class="pe-2">
                    <div class="fw-bold small text-dark">${item.name}</div>
                    <div class="text-muted" style="font-size: 0.75rem;">$${item.price.toFixed(2)} x ${item.quantity}</div>
                </div>
                <div class="d-flex align-items-center gap-2">
                    <span class="fw-bold text-dark small">$${itemTotal.toFixed(2)}</span>
                    <button class="btn btn-sm btn-outline-danger p-0 px-1 border-0" onclick="window.removeCartItem(${index})">
                        <i class="bi bi-trash"></i>
                    </button>
                </div>
            `;
            posCartList.appendChild(itemRow);
        });

        const tax = subtotal * 0.08;
        const total = subtotal + tax;

        if (posSubtotal) posSubtotal.textContent = '$' + subtotal.toFixed(2);
        if (posTax) posTax.textContent = '$' + tax.toFixed(2);
        if (posTotal) posTotal.textContent = '$' + total.toFixed(2);
        if (posCheckoutBtn) posCheckoutBtn.disabled = false;
    }

    window.removeCartItem = function (index) {
        cart.splice(index, 1);
        updateCartUI();
    };

    if (posItems) {
        posItems.forEach(item => {
            item.addEventListener('click', function () {
                const name = this.getAttribute('data-name');
                const price = parseFloat(this.getAttribute('data-price'));

                const existing = cart.find(c => c.name === name);
                if (existing) {
                    existing.quantity += 1;
                } else {
                    cart.push({ name, price, quantity: 1 });
                }
                updateCartUI();
                showToast(`Added "${name}" to bill`, 'info');
            });
        });
    }

    if (posClearBtn) {
        posClearBtn.addEventListener('click', function () {
            cart = [];
            updateCartUI();
            showToast('Cart cleared', 'info');
        });
    }

    if (posCheckoutBtn) {
        posCheckoutBtn.addEventListener('click', function () {
            if (cart.length > 0) {
                const total = posTotal ? posTotal.textContent : '$0.00';
                showToast(`Invoice generated successfully! Total paid: ${total}`, 'success');
                cart = [];
                updateCartUI();
            }
        });
    }

    // -------------------------------------------------------------
    // 5. Dashboard Chart.js Visualizations
    // -------------------------------------------------------------
    const revenueChartCanvas = document.getElementById('revenueTrendsChart');
    if (revenueChartCanvas && typeof Chart !== 'undefined') {
        const ctx = revenueChartCanvas.getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                datasets: [
                    {
                        label: 'Hair & Styling ($)',
                        data: [1200, 1450, 1800, 2100, 2900, 3850, 3450],
                        borderColor: '#d946ef',
                        backgroundColor: 'rgba(217, 70, 239, 0.1)',
                        tension: 0.4,
                        fill: true,
                        borderWidth: 3
                    },
                    {
                        label: 'Spa & Skin Care ($)',
                        data: [800, 950, 1100, 1400, 1950, 2600, 2200],
                        borderColor: '#8b5cf6',
                        backgroundColor: 'rgba(139, 92, 246, 0.05)',
                        tension: 0.4,
                        fill: true,
                        borderWidth: 2
                    }
                ]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'top',
                        labels: { font: { family: 'Plus Jakarta Sans', weight: '600' } }
                    }
                },
                scales: {
                    y: {
                        grid: { color: '#f1f5f9' },
                        ticks: { callback: value => '$' + value }
                    },
                    x: {
                        grid: { display: false }
                    }
                }
            }
        });
    }

    const appointmentDoughnutCanvas = document.getElementById('appointmentStatusChart');
    if (appointmentDoughnutCanvas && typeof Chart !== 'undefined') {
        const ctx = appointmentDoughnutCanvas.getContext('2d');
        new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['Completed (18)', 'In-Chair (6)', 'Confirmed (6)', 'Waiting (4)'],
                datasets: [{
                    data: [18, 6, 6, 4],
                    backgroundColor: ['#10b981', '#f59e0b', '#3b82f6', '#94a3b8'],
                    borderWidth: 0,
                    hoverOffset: 4
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                        labels: { font: { family: 'Plus Jakarta Sans', size: 11 } }
                    }
                },
                cutout: '70%'
            }
        });
    }

    // -------------------------------------------------------------
    // 6. Generic Table Search Filter
    // -------------------------------------------------------------
    const tableSearchInput = document.getElementById('tableSearchInput');
    if (tableSearchInput) {
        tableSearchInput.addEventListener('keyup', function () {
            const query = this.value.toLowerCase();
            const rows = document.querySelectorAll('.salon-table tbody tr');
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(query) ? '' : 'none';
            });
        });
    }
});

