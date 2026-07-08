document.addEventListener("DOMContentLoaded", () => {
    console.log("FoodHub JS Initialized.");

    // Auto-dismiss Bootstrap alert banners after 4 seconds
    const alerts = document.querySelectorAll(".alert-dismissible");
    alerts.forEach((alert) => {
        setTimeout(() => {
            const bsAlert = bootstrap.Alert.getOrCreateInstance(alert);
            if (bsAlert) {
                bsAlert.close();
            }
        }, 4000);
    });

    // Client-side confirmation dialogs for destructive actions
    const deleteButtons = document.querySelectorAll(".confirm-delete");
    deleteButtons.forEach((btn) => {
        btn.addEventListener("click", (e) => {
            const targetName = btn.getAttribute("data-name") || "this item";
            const confirmed = confirm(`Are you sure you want to delete ${targetName}? This action cannot be undone.`);
            if (!confirmed) {
                e.preventDefault();
            }
        });
    });

    // Pulse effect animation on the cart badge if the count changes
    const cartBadge = document.querySelector(".cart-badge");
    if (cartBadge) {
        cartBadge.classList.add("pulse-animation");
        setTimeout(() => {
            cartBadge.classList.remove("pulse-animation");
        }, 2000);
    }
});
