-- stores 테이블에 더미 데이터 삽입
INSERT INTO stores (store_name, store_password, store_email, created_at, updated_at) VALUES
('store_1', 'password_1', 'store_1@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('store_2', 'password_2', 'store_2@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- items 테이블에 더미 데이터 삽입
INSERT INTO items (category, item_price, sales_quantity, stock, view_count, created_at, store_id, updated_at,item_photos, item_desc_img, item_name) VALUES
(1, 1000, 10, 100, 50, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'www.localhost:8080','desc_img_1', 'item_1'),
(2, 2000, 20, 200, 100, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'www.localhost:8787','desc_img_2', 'item_2');

-- carts 테이블에 더미 데이터 삽입
INSERT INTO carts (created_at, updated_at) VALUES
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- users 테이블에 더미 데이터 삽입
INSERT INTO users (grade, password, user_name, email, refresh_token) VALUES
('A', 'password_1', 'user_1', 'user_1@example.com', 'token_1'),
('B', 'password_2', 'user_2', 'user_2@example.com', 'token_2');

-- likes 테이블에 더미 데이터 삽입
INSERT INTO likes (item_id, user_id, created_at, updated_at) VALUES
(1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- cart_items 테이블에 더미 데이터 삽입
INSERT INTO cart_items (cart_item_quantity, cart_id, item_id, created_at, updated_at) VALUES
(1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- orders 테이블에 더미 데이터 삽입
INSERT INTO orders (order_id, order_addr, order_phone, user_id) VALUES
(1, '서울시', '010-1234-1234', 1),
(2, '서울시', '010-1234-1234', 1),
(3, '서울시', '010-1234-1234', 1),
(4, '서울시', '010-1234-1234', 1);

-- order_items 테이블에 더미 데이터 삽입
INSERT INTO order_items (order_item_price, order_item_quantity, item_id, order_id, store_id, created_at, updated_at, order_item_status) VALUES
(1000, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORDER'),
(1000, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORDER'),
(1000, 1, 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORDER'),
(1000, 1, 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORDER'),
(1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORDER'),
(1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORDER'),
(1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORDER'),
(2000, 2, 2, 4, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORDER');

-- oauth 테이블에 더미 데이터 삽입
INSERT INTO oauth (oauth_provider, oauth_provider_id, user_email, oauth_id) VALUES
('google', 'google_id_1', 'user_1@example.com', 'oauth_1'),
('facebook', 'facebook_id_2', 'user_2@example.com', 'oauth_2');