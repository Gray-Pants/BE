-- stores 테이블에 더미 데이터 삽입
INSERT INTO stores (store_name, store_password, store_email, created_at, updated_at) VALUES
('store_1', 'password_1', 'store_1@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('store_2', 'password_2', 'store_2@example.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- items 테이블에 더미 데이터 삽입
INSERT INTO items (category, item_price, sales_quantity, stock, view_count, created_at, store_id, updated_at, item_photos, item_desc_img, item_name) VALUES
    ('SHOULDER_BAG', 20000, 10, 100, 50, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4190429_17183341481426_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4190429_17183341481426_320.jpg', '모든 게 다 들어가는 크로스백'),
    ('SHOULDER_BAG', 25000, 10, 100, 50, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4190429_17183341481426_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4190429_17183341481426_320.jpg', '모든 게 다 들어가는 크로스백'),
    ('SHOULDER_BAG', 30000, 10, 100, 50, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4190429_17183341481426_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4190429_17183341481426_320.jpg', '모든 게 다 들어가는 크로스백'),

    ('BACKPACK', 40000, 10, 100, 50, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/04cfba70ad4746a4b12d300d4272cd45.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/04cfba70ad4746a4b12d300d4272cd45.jpg', '귀여운 백팩'),
    ('BACKPACK', 45000, 10, 100, 50, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/04cfba70ad4746a4b12d300d4272cd45.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/04cfba70ad4746a4b12d300d4272cd45.jpg', '귀여운 백팩'),
    ('BACKPACK', 47000, 10, 100, 50, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/04cfba70ad4746a4b12d300d4272cd45.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/04cfba70ad4746a4b12d300d4272cd45.jpg', '귀여운 백팩'),
    ('BACKPACK', 49000, 10, 100, 50, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/04cfba70ad4746a4b12d300d4272cd45.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/04cfba70ad4746a4b12d300d4272cd45.jpg', '귀여운 백팩'),

    ('SHORT_SLEEVE', 30000, 20, 200, 100, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4fda663856d6477395213cef09058855.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4fda663856d6477395213cef09058855.jpg', '샤방한 반팔'),
    ('SHORT_SLEEVE', 31000, 20, 200, 100, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4fda663856d6477395213cef09058855.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4fda663856d6477395213cef09058855.jpg', '샤방한 반팔'),
    ('SHORT_SLEEVE', 33000, 20, 200, 100, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4fda663856d6477395213cef09058855.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4fda663856d6477395213cef09058855.jpg', '샤방한 반팔'),
    ('SHORT_SLEEVE', 35000, 20, 200, 100, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4fda663856d6477395213cef09058855.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4fda663856d6477395213cef09058855.jpg', '샤방한 반팔'),

    ('SNIKERS', 69000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/3464841_16920023767111_500.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/3464841_16920023767111_500.jpg', '짱 이쁜 운동화'),
    ('SNIKERS', 72000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/3464841_16920023767111_500.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/3464841_16920023767111_500.jpg', '짱 이쁜 운동화'),
    ('SNIKERS', 75000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/3464841_16920023767111_500.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/3464841_16920023767111_500.jpg', '짱 이쁜 운동화'),
    ('SNIKERS', 77000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/3464841_16920023767111_500.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/3464841_16920023767111_500.jpg', '짱 이쁜 운동화'),

    ('SHORTS', 16900, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4063420_17134280302959_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4063420_17134280302959_320.jpg', '깔쌈한 청반바지'),
    ('SHORTS', 15900, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4063420_17134280302959_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4063420_17134280302959_320.jpg', '깔쌈한 청반바지'),
    ('SHORTS', 19900, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4063420_17134280302959_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4063420_17134280302959_320.jpg', '깔쌈한 청반바지'),
    ('SHORTS', 23000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4063420_17134280302959_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4063420_17134280302959_320.jpg', '깔쌈한 청반바지'),

    ('SHORTS', 30000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/grey-pants.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/grey-pants.jpg', '근본 회색바지'),
    ('SHORTS', 31000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/grey-pants.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/grey-pants.jpg', '근본 회색바지'),
    ('SHORTS', 33000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/grey-pants.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/grey-pants.jpg', '근본 회색바지'),
    ('SHORTS', 35000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/grey-pants.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/grey-pants.jpg', '근본 회색바지'),

    ('PANTS', 69000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/b567f1f8b17b4b37b0242aa4a7f89d63.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/b567f1f8b17b4b37b0242aa4a7f89d63.jpg', '시원한 흰바지'),
    ('PANTS', 72000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/b567f1f8b17b4b37b0242aa4a7f89d63.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/b567f1f8b17b4b37b0242aa4a7f89d63.jpg', '시원한 흰바지'),
    ('PANTS', 75000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/b567f1f8b17b4b37b0242aa4a7f89d63.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/b567f1f8b17b4b37b0242aa4a7f89d63.jpg', '시원한 흰바지'),
    ('PANTS', 77000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/b567f1f8b17b4b37b0242aa4a7f89d63.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/b567f1f8b17b4b37b0242aa4a7f89d63.jpg', '시원한 흰바지'),

    ('BALL_CAP', 16900, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4120693_17153075595595_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4120693_17153075595595_320.jpg', '머리가 작아보이는 볼캡'),
    ('BALL_CAP', 15900, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4120693_17153075595595_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4120693_17153075595595_320.jpg', '머리가 작아보이는 볼캡'),
    ('BALL_CAP', 19900, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4120693_17153075595595_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4120693_17153075595595_320.jpg', '머리가 작아보이는 볼캡'),
    ('BALL_CAP', 23000, 30, 300, 150, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4120693_17153075595595_320.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/4120693_17153075595595_320.jpg', '머리가 작아보이는 볼캡'),

    ('SLIPPER', 16900, 15, 150, 75, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/2473265_1_500.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/2473265_1_500.jpg', '개편한 슬리퍼'),
    ('SLIPPER', 15900, 15, 150, 75, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/2473265_1_500.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/2473265_1_500.jpg', '개편한 슬리퍼'),
    ('SLIPPER', 19900, 15, 150, 75, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/2473265_1_500.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/2473265_1_500.jpg', '개편한 슬리퍼'),
    ('SLIPPER', 23000, 15, 150, 75, CURRENT_TIMESTAMP, 1, CURRENT_TIMESTAMP, 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/2473265_1_500.jpg', 'https://greypants-img-bucket.s3.ap-northeast-2.amazonaws.com/2473265_1_500.jpg', '개편한 슬리퍼');




-- users 테이블에 더미 데이터 삽입
INSERT INTO users (grade, password, user_name, email, refresh_token) VALUES
('A', 'password_1', 'user_1', 'user_1@example.com', 'token_1'),
('B', 'password_2', 'user_2', 'user_2@example.com', 'token_2');

-- likes 테이블에 더미 데이터 삽입
INSERT INTO likes (item_id, user_id, created_at, updated_at) VALUES
(1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- cart_items 테이블에 더미 데이터 삽입
INSERT INTO cart_items (cart_item_quantity, user_id, item_id, created_at, updated_at) VALUES
(1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 1, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


-- orders 테이블에 더미 데이터 삽입
INSERT INTO orders (tid, order_addr, order_phone, user_id, order_status, total_amount) VALUES
( 'tid', '서울시', '010-1234-1234', 1, 'COMPLETE' ,9999),
( 'tid', '서울시', '010-1234-1234', 1, 'COMPLETE' ,9999),
( 'tid', '서울시', '010-1234-1234', 1, 'COMPLETE' ,9999),
( 'tid', '서울시', '010-1234-1234', 1, 'COMPLETE' ,9999);


-- order_items 테이블에 더미 데이터 삽입
INSERT INTO order_items (order_item_price, order_item_quantity, item_id, order_id, store_id, created_at, updated_at, order_item_status) VALUES
(1000, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'COMPLETE'),
(1000, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'COMPLETE'),
(1000, 1, 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'COMPLETE'),
(1000, 1, 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'COMPLETE'),
(1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'COMPLETE'),
(1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'COMPLETE'),
(1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'COMPLETE'),
(2000, 2, 2, 4, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'COMPLETE');
-- INSERT INTO order_items (order_item_price, order_item_quantity, item_id, order_id, store_id, created_at, updated_at, order_item_addr, order_item_phone, order_item_status) VALUES
-- (1000, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'address_1', '010-1234-5678', 'ORDER'),
-- (1000, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'address_1', '010-1234-5678', 'ORDER'),
-- (1000, 1, 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'address_1', '010-1234-5678', 'ORDER'),
-- (1000, 1, 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'address_1', '010-1234-5678', 'ORDER'),
-- (1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'address_1', '010-1234-5678', 'ORDER'),
-- (1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'address_1', '010-1234-5678', 'ORDER'),
-- (1000, 1, 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'address_1', '010-1234-5678', 'ORDER'),
-- (2000, 2, 2, 4, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'address_2', '010-8765-4321', 'ORDER');


-- oauth 테이블에 더미 데이터 삽입
INSERT INTO oauth (oauth_provider, oauth_provider_id, user_email, oauth_id) VALUES
('google', 'google_id_1', 'user_1@example.com', 'oauth_1'),
('facebook', 'facebook_id_2', 'user_2@example.com', 'oauth_2');

INSERT INTO reviews (item_id, order_item_id, user_id, review_content, review_score, created_at) VALUES
(1, 1, 1, '아주아주아주 좋아요', 1, CURRENT_TIMESTAMP),
(1, 2, 1, '아주아주아주 좋아요', 2, CURRENT_TIMESTAMP),
(1, 3, 1, '아주아주아주 좋아요', 3, CURRENT_TIMESTAMP),
(1, 4, 1, '아주아주아주 좋아요', 4, CURRENT_TIMESTAMP),
(2, 8, 1, '아주아주아주 좋아요', 5, CURRENT_TIMESTAMP);