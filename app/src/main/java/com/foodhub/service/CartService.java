package com.foodhub.service;

import com.foodhub.entity.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<Long, Integer> items = new HashMap<>();

    @Autowired
    private transient FoodItemService foodItemService;

    public void addItem(Long foodItemId) {
        items.put(foodItemId, items.getOrDefault(foodItemId, 0) + 1);
    }

    public void removeItem(Long foodItemId) {
        if (items.containsKey(foodItemId)) {
            int quantity = items.get(foodItemId);
            if (quantity <= 1) {
                items.remove(foodItemId);
            } else {
                items.put(foodItemId, quantity - 1);
            }
        }
    }

    public void deleteItem(Long foodItemId) {
        items.remove(foodItemId);
    }

    public void updateQuantity(Long foodItemId, int quantity) {
        if (quantity <= 0) {
            items.remove(foodItemId);
        } else {
            items.put(foodItemId, quantity);
        }
    }

    public void clearCart() {
        items.clear();
    }

    public Map<FoodItem, Integer> getCartDetails() {
        Map<FoodItem, Integer> details = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            try {
                FoodItem foodItem = foodItemService.getFoodItemById(entry.getKey());
                details.put(foodItem, entry.getValue());
            } catch (Exception e) {
                // If an item was deleted from DB while in cart
                items.remove(entry.getKey());
            }
        }
        return details;
    }

    public int getCartSize() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    public Double getCartTotal() {
        double total = 0.0;
        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            try {
                FoodItem foodItem = foodItemService.getFoodItemById(entry.getKey());
                total += foodItem.getPrice() * entry.getValue();
            } catch (Exception e) {
                // Ignore missing item
            }
        }
        return total;
    }

    public Map<Long, Integer> getItems() {
        return items;
    }
}
