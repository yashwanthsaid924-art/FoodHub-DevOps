package com.foodhub.controller;

import com.foodhub.entity.FoodItem;
import com.foodhub.entity.Restaurant;
import com.foodhub.service.FoodItemService;
import com.foodhub.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final RestaurantService restaurantService;
    private final FoodItemService foodItemService;

    @Autowired
    public HomeController(RestaurantService restaurantService, FoodItemService foodItemService) {
        this.restaurantService = restaurantService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Fetch top rated restaurants as featured
        List<Restaurant> featured = restaurantService.getAllRestaurants().stream()
                .sorted((r1, r2) -> Double.compare(r2.getRating(), r1.getRating()))
                .limit(3)
                .collect(Collectors.toList());

        // Fetch a few popular dishes
        List<FoodItem> popularDishes = foodItemService.getAllFoodItems().stream()
                .limit(6)
                .collect(Collectors.toList());

        model.addAttribute("featuredRestaurants", featured);
        model.addAttribute("popularDishes", popularDishes);
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("successMessage",
                "Thank you, " + name + "! Your inquiry has been received. Our team will get back to you shortly.");
        return "redirect:/contact";
    }
}
