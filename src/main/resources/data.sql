insert into rl_product(id, date_created, date_modified, name)
    values
           (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Sugar'),
           (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Milk'),
           (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Potatoes'),
           (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Sea salt'),
           (5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Garlic'),
           (6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Ginger'),
           (7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Cheese'),
           (8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Crab meat'),
           (9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Egg'),
           (10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Flour'),
           (12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Butter'),
           (13, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Yogurt'),
           (14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Vanilla'),
           (11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Salmon');

insert into rl_recipe(id, version, date_created, date_modified, instructions, servings, status, title, is_vegetarian)
    values
           (100, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Combine all ingredients in a mixing bowl except for the almond flour. Add a sprinkling of almond flour to help bind mixture. Shape into small patties and then lightly coat in the almond flour. In a heavy based frypan, heat some peanut or olive oil, add the crab cakes and fry until golden on both sides. Finally garnish with some chopped chives and/ or fresh lemon juice squeezed over the top.', 8, 'ACTIVE', 'Gluten Free Crab Cake Recipe', false),
           (101, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Preheat oven to 350 degrees F (or 175 degrees C). Grease and flour a 9 x 5 x 3 inch (or 23 x 13 x 6cm) loaf pan. Or line with baking paper. In a large bowl, whisk self rising flour, baking soda, baking powder, salt, and cinnamon together. In a mixer using a paddle or whisk, beat the butter and brown sugar until it forms a creamy consistency. This should take a couple of minutes.', 2, 'ACTIVE', 'Self Rising Flour Banana Bread', false),
           (102, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Preheat oven to 360 F (180 C), Prepare baking tray with baking paper, Add flour to large mixing bowl, Make a well (hole) in the center of the flour. Make sure you do not compress the flour. Pour whipping cream, lemon soda (Sprite, 7Up, Lemonade) and chopped dates into the centre of the flour', 2, 'ACTIVE', 'Date Scone Recipe', false),
           (103, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Add shortening into sugar, Add rest of dry ingredients and stir together, Add pumpkin, eggs, and vanilla and stir together by hand till completely mixed, Stir firmly until the mixture is thick and stiff, Add pecans and stir the mixture until the pecans are spread evenly throughout' , 3, 'ACTIVE', 'Pumpkin Raisin Muffins', true),
           (104, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Preheat oven to 320 degrees Fahrenheit/ 160 degrees Celsius. Wash the lemon in warm, soapy water, then rinse in clean water. Finely grate the rind of the lemon, and set the grated rind aside. Squeeze the lemon – get as much juice out as you can. Set the juice aside.', 3, 'ACTIVE', 'Mary Berry Lemon Drizzle Cake', false),
           (105, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Preheat oven to 300 degrees Fahrenheit (150 degrees Celsius).Ensure all equipment is 100% dry – includes bowls, whisk, spatulas, etc – and that your hands are dry. Any additional moisture can and will affect the outcome of your pavlova base.', 4, 'ACTIVE', 'Australian Pavlova', false),
           (106, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Preheat oven to 180 degrees Celcius (moderate). Grease a deep square baking dish. Line the base and sides of dish with baking paper.In the base of the baking dish, sprinkle 2 tablespoons of dessicated coconut and 2 tablespoons brown sugar. In a large bowl, sift the flours and bi-carb soda.', 4, 'ACTIVE', 'Sticky Date Pudding', false);

insert into rl_ingredient(id, date_created, date_modified, amount, product_id, recipe_id, unit)
    values
           (100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 100, 'PACK'),
           (101, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 101, 'LITER'),
           (102, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 6, 101, 'POUND'),
           (103, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 7, 101, 'LITER'),
           (104, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 11, 101, 'PEACE'),
           (105, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 102, 'GRAMS'),
           (106, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3, 102, 'SPOON'),
           (107, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 4, 102, 'POUND'),
           (108, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 5, 102, 'LITER'),
           (109, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 8, 103, 'LITER'),
           (110, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 9, 103, 'PEACE'),
           (111, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 10, 103, 'TEASPOON'),
           (112, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 104, 'TEASPOON'),
           (113, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3, 105, 'TEASPOON'),
           (114, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3, 106, 'LITER'),
           (115, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 4, 106, 'PEACE');

