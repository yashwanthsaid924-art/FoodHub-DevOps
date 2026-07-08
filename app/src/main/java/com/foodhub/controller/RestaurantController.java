package com.foodhub.controller;

import com.foodhub.entity.FoodItem;
import com.foodhub.entity.Restaurant;
import com.foodhub.service.FoodItemService;
import com.foodhub.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final FoodItemService foodItemService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, FoodItemService foodItemService) {
        this.restaurantService = restaurantService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/restaurants")
    public String listRestaurants(
            @RequestParam(value = "search", required = false) String search,
            Model model) {

        List<Restaurant> restaurants;
        if (search != null && !search.trim().isEmpty()) {
            restaurants = restaurantService.searchRestaurants(search);
            model.addAttribute("searchQuery", search);
        } else {
            restaurants = restaurantService.getAllRestaurants();
        }

        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @GetMapping("/restaurants/{id}")
    public String viewRestaurantMenu(
            @PathVariable("id") Long id,
            @RequestParam(value = "search", required = false) String search,
            Model model) {

        Restaurant restaurant = restaurantService.getRestaurantById(id);
        List<FoodItem> foodItems;

        if (search != null && !search.trim().isEmpty()) {
            foodItems = foodItemService.searchFoodItemsInRestaurant(id, search);
            model.addAttribute("searchQuery", search);
        } else {
            foodItems = foodItemService.getFoodItemsByRestaurant(id);
        }

        model.addAttribute("restaurant", restaurant);
        model.addAttribute("foodItems", foodItems);
        return "menu";
    }

    @GetMapping("/menu")
    public String globalMenu(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "veg", required = false) Boolean veg,
            Model model) {

        List<FoodItem> foodItems;

        if (search != null && !search.trim().isEmpty()) {
            foodItems = foodItemService.searchFoodItems(search);
            model.addAttribute("searchQuery", search);
        } else if (category != null && !category.trim().isEmpty()) {
            if (category.equalsIgnoreCase("Veg")) {
                foodItems = foodItemService.getAllFoodItems().stream()
                        .filter(FoodItem::getIsVeg)
                        .collect(Collectors.toList());
            } else if (category.equalsIgnoreCase("Non Veg")) {
                foodItems = foodItemService.getAllFoodItems().stream()
                        .filter(item -> !item.getIsVeg())
                        .collect(Collectors.toList());
            } else {
                foodItems = foodItemService.getFoodItemsByCategory(category);
            }
            model.addAttribute("selectedCategory", category);
        } else {
            foodItems = foodItemService.getAllFoodItems();
        }

        if (veg != null) {
            foodItems = foodItems.stream()
                    .filter(item -> item.getIsVeg() == veg)
                    .collect(Collectors.toList());
            model.addAttribute("vegFilter", veg);
        }

        model.addAttribute("foodItems", foodItems);
        return "menu-global";
    }
}
