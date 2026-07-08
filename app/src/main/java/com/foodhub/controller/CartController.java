package com.foodhub.controller;

import com.foodhub.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartDetails());
        model.addAttribute("totalPrice", cartService.getCartTotal());

        double deliveryFee = cartService.getCartSize() > 0 ? 40.0 : 0.0;
        double tax = cartService.getCartTotal() * 0.05; // 5% GST
        double grandTotal = cartService.getCartTotal() + deliveryFee + tax;

        model.addAttribute("deliveryFee", deliveryFee);
        model.addAttribute("tax", tax);
        model.addAttribute("grandTotal", grandTotal);

        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam("foodItemId") Long foodItemId,
            @RequestHeader(value = "referer", required = false) String referer,
            RedirectAttributes redirectAttributes) {

        cartService.addItem(foodItemId);
        redirectAttributes.addFlashAttribute("successMessage", "Item added to cart successfully!");

        if (referer != null) {
            return "redirect:" + referer;
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(
            @RequestParam("foodItemId") Long foodItemId,
            @RequestHeader(value = "referer", required = false) String referer) {

        cartService.removeItem(foodItemId);
        if (referer != null) {
            return "redirect:" + referer;
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete")
    public String deleteFromCart(@RequestParam("foodItemId") Long foodItemId) {
        cartService.deleteItem(foodItemId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String updateQuantity(
            @RequestParam("foodItemId") Long foodItemId,
            @RequestParam("quantity") int quantity) {

        cartService.updateQuantity(foodItemId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(RedirectAttributes redirectAttributes) {
        if (cartService.getCartSize() == 0) {
            redirectAttributes.addFlashAttribute("errorMessage", "Your cart is empty!");
            return "redirect:/cart";
        }
        cartService.clearCart();
        redirectAttributes.addFlashAttribute("orderSuccessMessage", "Congratulations! Your order has been placed successfully. A rider will deliver it soon.");
        return "redirect:/cart";
    }
}
