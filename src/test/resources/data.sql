delete from rl_ingredient;
delete from rl_recipe;
delete from rl_product;

insert into rl_product(id, date_created, date_modified, name)
    values
           (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Sugar'),
           (2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Milk'),
           (3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Potatoes'),
           (4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'Salmon');

insert into rl_recipe(id, version, date_created, date_modified, instructions, servings, status, title, is_vegetarian)
    values
           (100, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'instructions', 4, 'ACTIVE', 'title', false),
           (101, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'instructions', 2, 'ACTIVE', 'title', false),
           (102, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'instructions', 2, 'ACTIVE', 'title', false),
           (103, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'instructions-3', 3, 'ACTIVE', 'title-3', true),
           (104, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'instructions-4 Oven cooked', 3, 'ACTIVE', 'title-4', false),
           (105, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'instructions', 4, 'ACTIVE', 'title', false),
           (106, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,  'instructions oven', 4, 'ACTIVE', 'title', false);

insert into rl_ingredient(id, date_created, date_modified, amount, product_id, recipe_id, unit)
    values
           (100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1, 100, 'PACK'),
           (101, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 101, 'LITER'),
           (102, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 102, 'LITER'),
           (104, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 2, 104, 'LITER'),
           (105, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3, 105, 'LITER'),
           (106, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3, 106, 'LITER'),
           (107, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 4, 106, 'LITER');

