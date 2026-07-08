package com.foodhub.service;

import com.foodhub.dto.FoodItemDTO;
import com.foodhub.entity.FoodItem;

import java.util.List;

public interface FoodItemService {
    List<FoodItem> getAllFoodItems();
    List<FoodItem> getFoodItemsByRestaurant(Long restaurantId);
    List<FoodItem> getFoodItemsByCategory(String category);
    List<FoodItem> searchFoodItems(String query);
    List<FoodItem> searchFoodItemsInRestaurant(Long restaurantId, String query);
    FoodItem getFoodItemById(Long id);
    FoodItem saveFoodItem(FoodItemDTO dto);
    void deleteFoodItem(Long id);
}
