-- Drop existing tables to ensure clean startup
DROP TABLE IF EXISTS food_items;
DROP TABLE IF EXISTS restaurants;

-- Create Restaurants table
CREATE TABLE restaurants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    rating DOUBLE,
    delivery_time INT,
    location VARCHAR(255),
    image_url VARCHAR(500)
);

-- Create Food Items table
CREATE TABLE food_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    price DOUBLE NOT NULL,
    image_url VARCHAR(500),
    is_veg BOOLEAN NOT NULL,
    category VARCHAR(50),
    restaurant_id BIGINT NOT NULL,
    CONSTRAINT fk_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);
