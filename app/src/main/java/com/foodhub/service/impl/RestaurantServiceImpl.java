package com.foodhub.service.impl;

import com.foodhub.dto.RestaurantDTO;
import com.foodhub.entity.Restaurant;
import com.foodhub.repository.RestaurantRepository;
import com.foodhub.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllRestaurants();
        }
        return restaurantRepository.findByNameContainingIgnoreCase(query);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + id));
    }

    @Override
    public Restaurant saveRestaurant(RestaurantDTO dto) {
        Restaurant restaurant;
        if (dto.getId() != null) {
            restaurant = getRestaurantById(dto.getId());
        } else {
            restaurant = new Restaurant();
        }
        restaurant.setName(dto.getName());
        restaurant.setRating(dto.getRating());
        restaurant.setDeliveryTime(dto.getDeliveryTime());
        restaurant.setLocation(dto.getLocation());
        restaurant.setImageUrl(dto.getImageUrl());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        Restaurant restaurant = getRestaurantById(id);
        restaurantRepository.delete(restaurant);
    }
}
