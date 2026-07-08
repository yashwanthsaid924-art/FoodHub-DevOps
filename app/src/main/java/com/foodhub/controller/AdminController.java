package com.foodhub.controller;

import com.foodhub.dto.FoodItemDTO;
import com.foodhub.dto.RestaurantDTO;
import com.foodhub.entity.FoodItem;
import com.foodhub.entity.Restaurant;
import com.foodhub.service.FoodItemService;
import com.foodhub.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RestaurantService restaurantService;
    private final FoodItemService foodItemService;

    @Autowired
    public AdminController(RestaurantService restaurantService, FoodItemService foodItemService) {
        this.restaurantService = restaurantService;
        this.foodItemService = foodItemService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        model.addAttribute("foodItems", foodItemService.getAllFoodItems());
        return "admin/dashboard";
    }

    // --- RESTAURANT CRUD ---

    @GetMapping("/restaurant/new")
    public String newRestaurantForm(Model model) {
        model.addAttribute("restaurantDTO", new RestaurantDTO());
        return "admin/restaurant-form";
    }

    @GetMapping("/restaurant/edit/{id}")
    public String editRestaurantForm(@PathVariable("id") Long id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        RestaurantDTO dto = new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getRating(),
                restaurant.getDeliveryTime(),
                restaurant.getLocation(),
                restaurant.getImageUrl()
        );
        model.addAttribute("restaurantDTO", dto);
        return "admin/restaurant-form";
    }

    @PostMapping("/restaurant/save")
    public String saveRestaurant(
            @Valid @ModelAttribute("restaurantDTO") RestaurantDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "admin/restaurant-form";
        }

        restaurantService.saveRestaurant(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Restaurant saved successfully!");
        return "redirect:/admin";
    }

    @GetMapping("/restaurant/delete/{id}")
    public String deleteRestaurant(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            restaurantService.deleteRestaurant(id);
            redirectAttributes.addFlashAttribute("successMessage", "Restaurant deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting restaurant: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    // --- FOOD ITEM CRUD ---

    @GetMapping("/food/new")
    public String newFoodItemForm(Model model) {
        model.addAttribute("foodItemDTO", new FoodItemDTO());
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "admin/food-form";
    }

    @GetMapping("/food/edit/{id}")
    public String editFoodItemForm(@PathVariable("id") Long id, Model model) {
        FoodItem foodItem = foodItemService.getFoodItemById(id);
        FoodItemDTO dto = new FoodItemDTO(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getDescription(),
                foodItem.getPrice(),
                foodItem.getImageUrl(),
                foodItem.getIsVeg(),
                foodItem.getCategory(),
                foodItem.getRestaurant().getId()
        );
        model.addAttribute("foodItemDTO", dto);
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "admin/food-form";
    }

    @PostMapping("/food/save")
    public String saveFoodItem(
            @Valid @ModelAttribute("foodItemDTO") FoodItemDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("restaurants", restaurantService.getAllRestaurants());
            return "admin/food-form";
        }

        foodItemService.saveFoodItem(dto);
        redirectAttributes.addFlashAttribute("successMessage", "Food item saved successfully!");
        return "redirect:/admin";
    }

    @GetMapping("/food/delete/{id}")
    public String deleteFoodItem(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            foodItemService.deleteFoodItem(id);
            redirectAttributes.addFlashAttribute("successMessage", "Food item deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting food item: " + e.getMessage());
        }
        return "redirect:/admin";
    }
}
