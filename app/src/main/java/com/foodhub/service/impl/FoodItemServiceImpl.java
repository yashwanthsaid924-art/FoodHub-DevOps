package com.foodhub.service.impl;

import com.foodhub.dto.FoodItemDTO;
import com.foodhub.entity.FoodItem;
import com.foodhub.entity.Restaurant;
import com.foodhub.repository.FoodItemRepository;
import com.foodhub.repository.RestaurantRepository;
import com.foodhub.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public FoodItemServiceImpl(FoodItemRepository foodItemRepository, RestaurantRepository restaurantRepository) {
        this.foodItemRepository = foodItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    public List<FoodItem> getFoodItemsByRestaurant(Long restaurantId) {
        return foodItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<FoodItem> getFoodItemsByCategory(String category) {
        return foodItemRepository.findByCategoryIgnoreCase(category);
    }

    @Override
    public List<FoodItem> searchFoodItems(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllFoodItems();
        }
        return foodItemRepository.findByNameContainingIgnoreCase(query);
    }

    @Override
    public List<FoodItem> searchFoodItemsInRestaurant(Long restaurantId, String query) {
        if (query == null || query.trim().isEmpty()) {
            return getFoodItemsByRestaurant(restaurantId);
        }
        return foodItemRepository.findByRestaurantIdAndNameContainingIgnoreCase(restaurantId, query);
    }

    @Override
    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Food item not found with id: " + id));
    }

    @Override
    public FoodItem saveFoodItem(FoodItemDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Associated Restaurant not found with id: " + dto.getRestaurantId()));

        FoodItem foodItem;
        if (dto.getId() != null) {
            foodItem = getFoodItemById(dto.getId());
        } else {
            foodItem = new FoodItem();
        }

        foodItem.setName(dto.getName());
        foodItem.setDescription(dto.getDescription());
        foodItem.setPrice(dto.getPrice());
        foodItem.setImageUrl(dto.getImageUrl());
        foodItem.setIsVeg(dto.getIsVeg());
        foodItem.setCategory(dto.getCategory());
        foodItem.setRestaurant(restaurant);

        return foodItemRepository.save(foodItem);
    }

    @Override
    public void deleteFoodItem(Long id) {
        FoodItem foodItem = getFoodItemById(id);
        foodItemRepository.delete(foodItem);
    }
}
