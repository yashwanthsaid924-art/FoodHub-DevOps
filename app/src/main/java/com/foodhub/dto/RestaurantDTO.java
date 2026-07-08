package com.foodhub.dto;

import jakarta.validation.constraints.*;

public class RestaurantDTO {

    private Long id;

    @NotBlank(message = "Restaurant name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Rating is required")
    @DecimalMin(value = "1.0", message = "Rating must be at least 1.0")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    private Double rating;

    @NotNull(message = "Delivery time is required")
    @Min(value = 5, message = "Delivery time must be at least 5 minutes")
    private Integer deliveryTime;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Long id, String name, Double rating, Integer deliveryTime, String location, String imageUrl) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.deliveryTime = deliveryTime;
        this.location = location;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
