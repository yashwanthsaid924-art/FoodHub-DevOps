package com.foodhub.repository;

import com.foodhub.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByNameContainingIgnoreCase(String name);
    List<FoodItem> findByCategoryIgnoreCase(String category);
    List<FoodItem> findByRestaurantId(Long restaurantId);
    List<FoodItem> findByRestaurantIdAndNameContainingIgnoreCase(Long restaurantId, String name);
}
