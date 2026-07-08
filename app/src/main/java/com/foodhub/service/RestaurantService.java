package com.foodhub.service;

import com.foodhub.dto.RestaurantDTO;
import com.foodhub.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();
    List<Restaurant> searchRestaurants(String query);
    Restaurant getRestaurantById(Long id);
    Restaurant saveRestaurant(RestaurantDTO dto);
    void deleteRestaurant(Long id);
}
