-- Seed Restaurants
INSERT INTO restaurants (id, name, rating, delivery_time, location, image_url) VALUES
(1, 'Burger King', 4.2, 25, 'Indiranagar, Bangalore', 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=500&q=80'),
(2, 'Domino''s', 4.4, 30, 'Koramangala, Bangalore', 'https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&w=500&q=80'),
(3, 'KFC', 4.1, 20, 'MG Road, Bangalore', 'https://images.unsplash.com/photo-1513639773648-19144254972c?auto=format&fit=crop&w=500&q=80'),
(4, 'Starbucks', 4.6, 15, 'Whitefield, Bangalore', 'https://images.unsplash.com/photo-1509042239860-f550ce710b93?auto=format&fit=crop&w=500&q=80'),
(5, 'Meghana Biryani', 4.7, 35, 'Jayanagar, Bangalore', 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?auto=format&fit=crop&w=500&q=80'),
(6, 'Taco Bell', 4.3, 25, 'Malleshwaram, Bangalore', 'https://images.unsplash.com/photo-1565299585323-38d6b0865b47?auto=format&fit=crop&w=500&q=80');

-- Seed Food Items
-- ID, Name, Description, Price, Image URL, is_veg, Category, Restaurant_ID
INSERT INTO food_items (id, name, description, price, image_url, is_veg, category, restaurant_id) VALUES
-- Burger King (Rest 1)
(1, 'Whopper Burger', 'Our signature flame-grilled Whopper burger with juicy beef, fresh tomatoes, lettuce, pickles, and onions.', 199.00, 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=500&q=80', false, 'Burger', 1),
(2, 'Veggie Strips', 'Golden brown crispy vegetable strips served with tang sauce.', 99.00, 'https://images.unsplash.com/photo-1569058242253-92a9c755a0ec?auto=format&fit=crop&w=500&q=80', true, 'Fast Food', 1),
(3, 'Crispy Chicken Burger', 'Crispy chicken patty with crisp lettuce and creamy mayonnaise.', 149.00, 'https://images.unsplash.com/photo-1625813506062-0aeb1d7a094b?auto=format&fit=crop&w=500&q=80', false, 'Burger', 1),
(4, 'Onion Rings', 'Crispy fried golden batter-coated onion rings.', 89.00, 'https://images.unsplash.com/photo-1639024471283-2bc7b3c6a267?auto=format&fit=crop&w=500&q=80', true, 'Fast Food', 1),
(5, 'Chocolate Shake', 'Creamy cocoa blended milkshake topped with chocolate drizzle.', 119.00, 'https://images.unsplash.com/photo-1572490122747-3968b75cc699?auto=format&fit=crop&w=500&q=80', true, 'Drinks', 1),

-- Domino's (Rest 2)
(6, 'Farmhouse Pizza', 'Delightful combination of onion, capsicum, tomato and grilled mushroom.', 399.00, 'https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&w=500&q=80', true, 'Pizza', 2),
(7, 'Pepperoni Pizza', 'Loaded with classic pork pepperoni and melted mozzarella cheese.', 499.00, 'https://images.unsplash.com/photo-1628840042765-356cda07504e?auto=format&fit=crop&w=500&q=80', false, 'Pizza', 2),
(8, 'Garlic Breadsticks', 'Baked garlic breadsticks served with a warm cheese dip.', 109.00, 'https://images.unsplash.com/photo-1619535860434-ba1d8fa12536?auto=format&fit=crop&w=500&q=80', true, 'Fast Food', 2),
(9, 'Choco Lava Cake', 'Warm chocolate cake with a rich molten chocolate center.', 99.00, 'https://images.unsplash.com/photo-1606313564200-e75d5e30476c?auto=format&fit=crop&w=500&q=80', true, 'Desserts', 2),
(10, 'Cheese Burst Pizza', 'Creamy liquid cheese filled crust pizza topped with golden corn.', 459.00, 'https://images.unsplash.com/photo-1590947132387-155cc02f3212?auto=format&fit=crop&w=500&q=80', true, 'Pizza', 2),
(11, 'Paneer Zingy Parcel', 'Baked pastry envelope filled with paneer, harissa sauce, and cheese.', 89.00, 'https://images.unsplash.com/photo-1601050690597-df056fb4ce78?auto=format&fit=crop&w=500&q=80', true, 'Fast Food', 2),

-- KFC (Rest 3)
(12, '8 Pc Hot & Crispy Bucket', 'Signature crispy chicken bucket seasoned to perfection.', 699.00, 'https://images.unsplash.com/photo-1562967914-608f82629710?auto=format&fit=crop&w=500&q=80', false, 'Fast Food', 3),
(13, 'Chicken Zinger Burger', 'Extra crispy chicken fillet in a soft sesame bun with lettuce.', 179.00, 'https://images.unsplash.com/photo-1521305916504-4a1121188589?auto=format&fit=crop&w=500&q=80', false, 'Burger', 3),
(14, 'Popcorn Chicken', 'Bite-sized tender boneless chicken nuggets with seasoning.', 149.00, 'https://images.unsplash.com/photo-1569691836895-b389496b3643?auto=format&fit=crop&w=500&q=80', false, 'Fast Food', 3),
(15, 'French Fries', 'Perfectly salted crispy golden potato fries.', 89.00, 'https://images.unsplash.com/photo-1573080496219-bb080dd4f877?auto=format&fit=crop&w=500&q=80', true, 'Fast Food', 3),
(16, 'Pepsi Can', 'Chilled sparkling carbonated cola soda.', 59.00, 'https://images.unsplash.com/photo-1622483767028-3f66f32aef97?auto=format&fit=crop&w=500&q=80', true, 'Drinks', 3),
(17, 'Veg Zinger Burger', 'Crispy veggie patty with creamy mayo and fresh lettuce in a soft bun.', 159.00, 'https://images.unsplash.com/photo-1525059696034-4967a8e1dca2?auto=format&fit=crop&w=500&q=80', true, 'Burger', 3),

-- Starbucks (Rest 4)
(18, 'Caffe Latte', 'Rich espresso shot with steamed milk and a thin layer of foam.', 249.00, 'https://images.unsplash.com/photo-1541167760496-1628856ab772?auto=format&fit=crop&w=500&q=80', true, 'Drinks', 4),
(19, 'Java Chip Frappuccino', 'Blended coffee beverage with chocolate chips, milk, ice, and whipped cream.', 299.00, 'https://images.unsplash.com/photo-1572490122747-3968b75cc699?auto=format&fit=crop&w=500&q=80', true, 'Drinks', 4),
(20, 'Blueberry Muffin', 'Freshly baked moist muffin packed with sweet blueberries.', 169.00, 'https://images.unsplash.com/photo-1607958996333-41aef7caefaa?auto=format&fit=crop&w=500&q=80', true, 'Desserts', 4),
(21, 'Butter Croissant', 'Flaky, golden, buttery classic French pastry.', 149.00, 'https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&w=500&q=80', true, 'Desserts', 4),
(22, 'Cold Brew Coffee', 'Slow-steeped smooth iced black coffee served over ice.', 219.00, 'https://images.unsplash.com/photo-1517701604599-bb29b565090c?auto=format&fit=crop&w=500&q=80', true, 'Drinks', 4),
(23, 'Red Velvet Cake Slice', 'Decadent crimson sponge layered with rich cream cheese frosting.', 189.00, 'https://images.unsplash.com/photo-1588195538326-c5b1e9f80a1b?auto=format&fit=crop&w=500&q=80', true, 'Desserts', 4),

-- Meghana Biryani (Rest 5)
(24, 'Chicken Boneless Biryani', 'Aromatic basmati rice cooked with spicy tender boneless chicken pieces and authentic spices.', 329.00, 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?auto=format&fit=crop&w=500&q=80', false, 'Biryani', 5),
(25, 'Special Veg Biryani', 'Fresh seasonal vegetables cooked with fragrant spices and long grain basmati rice.', 249.00, 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?auto=format&fit=crop&w=500&q=80', true, 'Biryani', 5),
(26, 'Paneer Biryani', 'Traditional spiced biryani served with marinated soft paneer cubes.', 279.00, 'https://images.unsplash.com/photo-1631452180519-c014fe946bc7?auto=format&fit=crop&w=500&q=80', true, 'Biryani', 5),
(27, 'Mutton Biryani', 'Rich and spicy biryani cooked with melt-in-the-mouth mutton pieces.', 399.00, 'https://images.unsplash.com/photo-1601050690597-df056fb4ce78?auto=format&fit=crop&w=500&q=80', false, 'Biryani', 5),
(28, 'Chicken Tikka Kabab', 'Clay oven roasted chicken chunks marinated in spicy yogurt and herbs.', 289.00, 'https://images.unsplash.com/photo-1599487488170-d11ec9c172f0?auto=format&fit=crop&w=500&q=80', false, 'South Indian', 5),
(29, 'Chilled Raita', 'Chilled seasoned yogurt mixed with finely chopped onions, green chillies, and fresh coriander.', 49.00, 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&w=500&q=80', true, 'South Indian', 5),

-- Taco Bell (Rest 6)
(30, 'Naked Chicken Taco', 'Spicy chicken shell taco stuffed with fresh lettuce, tomatoes, and cheese.', 189.00, 'https://images.unsplash.com/photo-1565299585323-38d6b0865b47?auto=format&fit=crop&w=500&q=80', false, 'Fast Food', 6),
(31, '7 Layer Burrito', 'Hearty burrito packed with beans, rice, cheese, lettuce, sour cream, and guacamole.', 199.00, 'https://images.unsplash.com/photo-1626700051175-6518c4793f0f?auto=format&fit=crop&w=500&q=80', true, 'Healthy Food', 6),
(32, 'Cheese Quesadilla', 'Grilled flour tortilla loaded with melted three-cheese blend and jalapeño sauce.', 129.00, 'https://images.unsplash.com/photo-1615870216519-2f9fa575fa5c?auto=format&fit=crop&w=500&q=80', true, 'Fast Food', 6),
(33, 'Nachos with Cheese', 'Crispy tortilla chips served with a side of warm creamy nacho cheese sauce.', 119.00, 'https://images.unsplash.com/photo-1513456852971-30c0b8199d4d?auto=format&fit=crop&w=500&q=80', true, 'Fast Food', 6),
(34, 'Cinnamon Churros', 'Crispy fried dough sticks rolled in aromatic cinnamon sugar and syrup.', 99.00, 'https://images.unsplash.com/photo-1588195538326-c5b1e9f80a1b?auto=format&fit=crop&w=500&q=80', true, 'Desserts', 6),
(35, 'Mexican Seasoned Rice', 'Aromatic rice seasoned with authentic Mexican spices, bell peppers, and herbs.', 99.00, 'https://images.unsplash.com/photo-1512058564366-18510be2db19?auto=format&fit=crop&w=500&q=80', true, 'Healthy Food', 6);
