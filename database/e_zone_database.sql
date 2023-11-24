DROP DATABASE IF EXISTS e_zone_database;
CREATE DATABASE e_zone_database;
USE e_zone_database;

-- Vietnam units:

-- DROP TABLE IF EXISTS wards;
-- DROP TABLE IF EXISTS districts;
-- DROP TABLE IF EXISTS provinces;
-- DROP TABLE IF EXISTS administrative_units;
-- DROP TABLE IF EXISTS administrative_regions;

-- CREATE administrative_regions TABLE
CREATE TABLE administrative_regions (
	id integer NOT NULL,
	name varchar(255) NOT NULL,
	name_en varchar(255) NOT NULL,
	code_name varchar(255) NULL,
	code_name_en varchar(255) NULL,
	CONSTRAINT administrative_regions_pkey PRIMARY KEY (id)
);


-- CREATE administrative_units TABLE
CREATE TABLE administrative_units (
	id integer NOT NULL,
	full_name varchar(255) NULL,
	full_name_en varchar(255) NULL,
	short_name varchar(255) NULL,
	short_name_en varchar(255) NULL,
	code_name varchar(255) NULL,
	code_name_en varchar(255) NULL,
	CONSTRAINT administrative_units_pkey PRIMARY KEY (id)
);


-- CREATE provinces TABLE
CREATE TABLE provinces (
	code varchar(20) NOT NULL,
	name varchar(255) NOT NULL,
	name_en varchar(255) NULL,
	full_name varchar(255) NOT NULL,
	full_name_en varchar(255) NULL,
	code_name varchar(255) NULL,
	administrative_unit_id integer NULL,
	administrative_region_id integer NULL,
	CONSTRAINT provinces_pkey PRIMARY KEY (code)
);


-- provinces foreign keys

ALTER TABLE provinces ADD CONSTRAINT provinces_administrative_region_id_fkey FOREIGN KEY (administrative_region_id) REFERENCES administrative_regions(id);
ALTER TABLE provinces ADD CONSTRAINT provinces_administrative_unit_id_fkey FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id);

CREATE INDEX idx_provinces_region ON provinces(administrative_region_id);
CREATE INDEX idx_provinces_unit ON provinces(administrative_unit_id);


-- CREATE districts TABLE
CREATE TABLE districts (
	code varchar(20) NOT NULL,
	name varchar(255) NOT NULL,
	name_en varchar(255) NULL,
	full_name varchar(255) NULL,
	full_name_en varchar(255) NULL,
	code_name varchar(255) NULL,
	province_code varchar(20) NULL,
	administrative_unit_id integer NULL,
	CONSTRAINT districts_pkey PRIMARY KEY (code)
);


-- districts foreign keys

ALTER TABLE districts ADD CONSTRAINT districts_administrative_unit_id_fkey FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id);
ALTER TABLE districts ADD CONSTRAINT districts_province_code_fkey FOREIGN KEY (province_code) REFERENCES provinces(code);

CREATE INDEX idx_districts_province ON districts(province_code);
CREATE INDEX idx_districts_unit ON districts(administrative_unit_id);


-- CREATE wards TABLE
CREATE TABLE wards (
	code varchar(20) NOT NULL,
	name varchar(255) NOT NULL,
	name_en varchar(255) NULL,
	full_name varchar(255) NULL,
	full_name_en varchar(255) NULL,
	code_name varchar(255) NULL,
	district_code varchar(20) NULL,
	administrative_unit_id integer NULL,
	CONSTRAINT wards_pkey PRIMARY KEY (code)
);


-- wards foreign keys

ALTER TABLE wards ADD CONSTRAINT wards_administrative_unit_id_fkey FOREIGN KEY (administrative_unit_id) REFERENCES administrative_units(id);
ALTER TABLE wards ADD CONSTRAINT wards_district_code_fkey FOREIGN KEY (district_code) REFERENCES districts(code);

CREATE INDEX idx_wards_district ON wards(district_code);
CREATE INDEX idx_wards_unit ON wards(administrative_unit_id);

-- ------------------------------------------------------------------------

-- E-Zone's TABLE

-- DROP TABLE IF EXISTS...

--

-- CREATE category TABLE
CREATE TABLE category (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE KEY,
    CONSTRAINT category_pkey PRIMARY KEY (id)
);

-- CREATE manufactory TABLE
CREATE TABLE manufactory (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    category_id integer UNSIGNED NULL, 
	name varchar(50) NOT NULL,
    CONSTRAINT manufactory_pkey PRIMARY KEY (id)
);

-- manufactory unique keys
ALTER TABLE manufactory ADD UNIQUE KEY (category_id, name);

-- manufactory foreign keys
ALTER TABLE manufactory ADD CONSTRAINT manufactory_category_id_fkey FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE CASCADE;


-- CREATE color TABLE
CREATE TABLE color (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE KEY,
    CONSTRAINT color_pkey PRIMARY KEY (id)
);

-- CREATE product TABLE
CREATE TABLE product (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL UNIQUE KEY,
	created_date datetime DEFAULT NOW(),
	status ENUM("ACTIVE", "DEACTIVE"),
	image_1 varchar(1000) NULL,
    image_2 varchar(1000) NULL,
    image_3 varchar(1000) NULL,
    image_4 varchar(1000) NULL,
    image_5 varchar(1000) NULL,
    os varchar(100) NULL,
    cpu varchar(100) NULL,
    gpu varchar(100) NULL,
    ram varchar(100) NULL,
    rom varchar(100) NULL,
    screen_size varchar(255) NULL,
    resolution varchar(255) NULL,
    camera varchar(255) NULL,
    battery varchar(255) NULL,
    sim varchar(255) NULL,
    bluetooth varchar(255) NULL,
    connection_port varchar(255) NULL,
    headphone_jack varchar(255) NULL,
    material varchar(255) NULL,
    dimensions varchar(255) NULL,
    water_resistance varchar(255) NULL,
    face_material varchar(255) NULL,
    `desc` text NULL,
    manufactory_id integer UNSIGNED NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id)
);

-- product foreign keys
ALTER TABLE product ADD CONSTRAINT product_manufactory_id_fkey FOREIGN KEY (manufactory_id) REFERENCES manufactory(id) ON DELETE SET NULL;

-- CREATE product_inventory TABLE
CREATE TABLE product_inventory (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	cost integer NOT NULL,
	price integer NOT NULL,
	stock integer NOT NULL,
    product_id integer UNSIGNED NULL,
    color_id integer UNSIGNED NULL,
	CONSTRAINT product_inventory_pkey PRIMARY KEY (id)
);

-- product_inventory unique keys
ALTER TABLE product_inventory ADD UNIQUE KEY (product_id, color_id);

-- product_inventory foreign keys
ALTER TABLE product_inventory ADD CONSTRAINT product_inventory_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE;
ALTER TABLE product_inventory ADD CONSTRAINT product_inventory_color_id_fkey FOREIGN KEY (color_id) REFERENCES color(id) ON DELETE CASCADE;

-- CREATE product_inventory_sale TABLE
CREATE TABLE product_inventory_sale (
    id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
    product_inventory_id INTEGER UNSIGNED NULL,
    sale_price INTEGER NOT NULL,
    CONSTRAINT product_inventory_pkey PRIMARY KEY (id)
);

-- product_inventory_sale foreign keys
ALTER TABLE product_inventory_sale ADD CONSTRAINT product_inventory_sale_product_inventory_id_fkey FOREIGN KEY (product_inventory_id) REFERENCES product_inventory(id) ON DELETE CASCADE;

-- CREATE voucher TABLE
CREATE TABLE voucher (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	code varchar(16) NOT NULL UNIQUE KEY,
	discount_amount integer NOT NULL,
	stock integer NOT NULL,
    claimed integer default 0,
    start_date datetime,
    end_date datetime,
    activated boolean,
	CONSTRAINT voucher_pkey PRIMARY KEY (id)
);

-- CREATE product_voucher TABLE
CREATE TABLE product_voucher (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	product_id integer UNSIGNED NOT NULL,
	voucher_id integer UNSIGNED NOT NULL,
	CONSTRAINT product_voucher_pkey PRIMARY KEY (id)
);

-- product_voucher unique keys
ALTER TABLE product_voucher ADD UNIQUE KEY (product_id, voucher_id);

-- product_voucher foreign keys
ALTER TABLE product_voucher ADD CONSTRAINT product_voucher_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE;
ALTER TABLE product_voucher ADD CONSTRAINT product_voucher_voucher_id_fkey FOREIGN KEY (voucher_id) REFERENCES voucher(id) ON DELETE CASCADE;

-- CREATE news_topic TABLE
CREATE TABLE news_topic (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	CONSTRAINT news_topic_pkey PRIMARY KEY (id)
);

-- CREATE bank TABLE
CREATE TABLE bank (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL UNIQUE KEY,
	CONSTRAINT bank_pkey PRIMARY KEY (id)
);

-- CREATE user TABLE
CREATE TABLE user (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
	username varchar(50) NOT NULL UNIQUE KEY,
    password text NOT NULL,
    email varchar(255) NOT NULL UNIQUE KEY,
    phone_number varchar(15) NULL UNIQUE KEY,
    role ENUM("ADMIN", "MOD", "MANAGER", "STAFF", "MEMBER") DEFAULT "MEMBER",
    full_name varchar(50) NOT NULL,
    gender ENUM("MALE","FEMALE","OTHER") DEFAULT "MALE",
    avatar varchar(1000) NULL,
    address varchar(100) NULL,
    created_date datetime DEFAULT NOW(),
    activated boolean,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);

-- CREATE conversation TABLE
CREATE TABLE conversation (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    create_user_id integer UNSIGNED NOT NULL,
	name varchar(100) NOT NULL,
    status ENUM("OPENING", "CLOSED"),
	CONSTRAINT conversation_pkey PRIMARY KEY (id)
);

-- conversation foreign keys
ALTER TABLE conversation ADD CONSTRAINT conversation_create_user_id_fkey FOREIGN KEY (create_user_id) REFERENCES user(id) ON DELETE CASCADE;

-- CREATE customer TABLE
CREATE TABLE customer (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    created_date datetime DEFAULT NOW(),
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

-- customer foreign keys
ALTER TABLE customer ADD CONSTRAINT customer_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;

-- CREATE product_rating TABLE
CREATE TABLE product_rating (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    product_id integer UNSIGNED NOT NULL,
    user_id integer UNSIGNED NOT NULL,
    rating smallint UNSIGNED NOT NULL,
    created_date datetime DEFAULT NOW(),
	confirmed boolean,
	CONSTRAINT product_rating_pkey PRIMARY KEY (id),
    CHECK (rating >= 1 AND rating <=5)
);

-- product_rating unique keys
ALTER TABLE product_rating ADD UNIQUE KEY (product_id, user_id);

-- product_rating foreign keys
ALTER TABLE product_rating ADD CONSTRAINT product_rating_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE;
ALTER TABLE product_rating ADD CONSTRAINT product_rating_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;

-- CREATE product_comment TABLE
CREATE TABLE product_comment (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    product_id integer UNSIGNED NOT NULL,
    content text NULL,
    created_date datetime DEFAULT NOW(),
	confirmed boolean,
	CONSTRAINT product_comment_pkey PRIMARY KEY (id)
);

-- product_comment foreign keys
ALTER TABLE product_comment ADD CONSTRAINT product_comment_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE product_comment ADD CONSTRAINT product_comment_product_id_fkey FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE;

-- CREATE product_comment_like TABLE
CREATE TABLE product_comment_like (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    product_comment_id integer UNSIGNED NOT NULL,
    like_date datetime DEFAULT NOW(),
	CONSTRAINT product_comment_like_pkey PRIMARY KEY (id)
);

-- product_comment_like unique keys
ALTER TABLE product_comment_like ADD UNIQUE KEY (product_comment_id, user_id);

-- product_comment_like foreign keys
ALTER TABLE product_comment_like ADD CONSTRAINT product_comment_like_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE product_comment_like ADD CONSTRAINT product_comment_like_product_comment_id_fkey FOREIGN KEY (product_comment_id) REFERENCES product_comment(id) ON DELETE CASCADE;

-- CREATE product_sub_comment TABLE
CREATE TABLE product_sub_comment (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    product_comment_id integer UNSIGNED NOT NULL,
    content text NULL,
    created_date datetime DEFAULT NOW(),
	confirmed boolean,
	CONSTRAINT product_sub_comment_pkey PRIMARY KEY (id)
);

-- product_sub_comment foreign keys
ALTER TABLE product_sub_comment ADD CONSTRAINT product_sub_comment_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE product_sub_comment ADD CONSTRAINT product_sub_comment_product_comment_id_fkey FOREIGN KEY (product_comment_id) REFERENCES product_comment(id) ON DELETE CASCADE;

-- CREATE news_post TABLE
CREATE TABLE news_post (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    topic_id integer UNSIGNED NOT NULL,
    title varchar(100) NOT NULL,
    cover varchar(1000) NULL,
    content text NOT NULL,
    created_date datetime DEFAULT NOW(),
	CONSTRAINT news_post_pkey PRIMARY KEY (id)
);

-- news_post foreign keys
ALTER TABLE news_post ADD CONSTRAINT news_post_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE news_post ADD CONSTRAINT news_post_topic_id_fkey FOREIGN KEY (topic_id) REFERENCES news_topic(id) ON DELETE CASCADE;

-- CREATE news_comment TABLE
CREATE TABLE news_comment (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    post_id integer UNSIGNED NOT NULL,
    content text NULL,
    created_date datetime DEFAULT NOW(),
	CONSTRAINT news_comment_pkey PRIMARY KEY (id)
);

-- news_comment foreign keys
ALTER TABLE news_comment ADD CONSTRAINT news_comment_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE news_comment ADD CONSTRAINT news_comment_post_id_fkey FOREIGN KEY (post_id) REFERENCES news_post(id) ON DELETE CASCADE;

-- CREATE news_sub_comment TABLE
CREATE TABLE news_sub_comment (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    news_comment_id integer UNSIGNED NOT NULL,
    content text NULL,
    created_date datetime DEFAULT NOW(),
	CONSTRAINT news_sub_comment_pkey PRIMARY KEY (id)
);

-- news_sub_comment foreign keys
ALTER TABLE news_sub_comment ADD CONSTRAINT news_sub_comment_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE news_sub_comment ADD CONSTRAINT news_sub_comment_news_comment_id_fkey FOREIGN KEY (news_comment_id) REFERENCES news_comment(id) ON DELETE CASCADE;

-- CREATE news_post_like TABLE
CREATE TABLE news_post_like (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    news_post_id integer UNSIGNED NOT NULL,
    like_date datetime DEFAULT NOW(),
	CONSTRAINT news_post_like_pkey PRIMARY KEY (id)
);

-- news_post_like unique keys
ALTER TABLE news_post_like ADD UNIQUE KEY (news_post_id, user_id);

-- news_post_like foreign keys
ALTER TABLE news_post_like ADD CONSTRAINT news_post_like_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE news_post_like ADD CONSTRAINT news_post_like_news_post_id_fkey FOREIGN KEY (news_post_id) REFERENCES news_post(id) ON DELETE CASCADE;

-- CREATE news_comment_like TABLE
CREATE TABLE news_comment_like (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    news_comment_id integer UNSIGNED NOT NULL,
    like_date datetime DEFAULT NOW(),
	CONSTRAINT news_comment_like_pkey PRIMARY KEY (id)
);

-- news_comment_like unique keys
ALTER TABLE news_comment_like ADD UNIQUE KEY (news_comment_id, user_id);

-- news_comment_like foreign keys
ALTER TABLE news_comment_like ADD CONSTRAINT news_comment_like_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE news_comment_like ADD CONSTRAINT news_comment_like_news_comment_id_fkey FOREIGN KEY (news_comment_id) REFERENCES news_comment(id) ON DELETE CASCADE;

-- CREATE message TABLE
CREATE TABLE message (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    from_user_id integer UNSIGNED NOT NULL,
    conversation_id integer UNSIGNED NOT NULL,
    content varchar(1000) NOT NULL,
    created_at datetime DEFAULT NOW(),
	CONSTRAINT message_pkey PRIMARY KEY (id)
);

-- message foreign keys
ALTER TABLE message ADD CONSTRAINT message_from_user_id_fkey FOREIGN KEY (from_user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE message ADD CONSTRAINT message_conversation_id_fkey FOREIGN KEY (conversation_id) REFERENCES conversation(id) ON DELETE CASCADE;

-- CREATE channel TABLE
CREATE TABLE channel (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    conversation_id integer UNSIGNED NOT NULL,
    joined_date datetime DEFAULT NOW(),
    left_date datetime NULL,
	CONSTRAINT channel_pkey PRIMARY KEY (id)
);

-- channel unique keys
ALTER TABLE channel ADD UNIQUE KEY (conversation_id, user_id);

-- channel foreign keys
ALTER TABLE channel ADD CONSTRAINT channel_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE channel ADD CONSTRAINT channel_conversation_id_fkey FOREIGN KEY (conversation_id) REFERENCES conversation(id) ON DELETE CASCADE;

-- CREATE customer_payment TABLE
CREATE TABLE customer_payment (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    customer_id integer UNSIGNED NOT NULL,
    bank_id integer UNSIGNED NOT NULL,
    card_number varchar(16) NOT NULL,
    card_name varchar(50) NOT NULL,
    card_expired date NOT NULL,
    cvc varchar(3) NOT NULL,
	CONSTRAINT customer_payment_pkey PRIMARY KEY (id)
);

-- customer_payment unique keys
ALTER TABLE customer_payment ADD UNIQUE KEY (bank_id, card_number);

-- customer_payment foreign keys
ALTER TABLE customer_payment ADD CONSTRAINT customer_payment_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE;
ALTER TABLE customer_payment ADD CONSTRAINT customer_payment_bank_id_fkey FOREIGN KEY (bank_id) REFERENCES bank(id) ON DELETE CASCADE;

-- CREATE customer_address TABLE
CREATE TABLE customer_address (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    customer_id integer UNSIGNED NOT NULL,
    ward_code varchar(20) NOT NULL,
	shipping_address varchar(100) NOT NULL,
	CONSTRAINT customer_address_pkey PRIMARY KEY (id)
);

-- customer_address unique keys
ALTER TABLE customer_address ADD UNIQUE KEY (customer_id, shipping_address);

-- customer_address foreign keys
ALTER TABLE customer_address ADD CONSTRAINT customer_address_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE;
ALTER TABLE customer_address ADD CONSTRAINT customer_address_ward_code_fkey FOREIGN KEY (ward_code) REFERENCES wards(code) ON DELETE CASCADE;

-- CREATE order TABLE
CREATE TABLE `order` (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id integer UNSIGNED NOT NULL,
    order_address varchar(100) NOT NULL,
    ward_code varchar(20) NOT NULL,
    created_date datetime DEFAULT NOW(),
    status ENUM("PREPARING", "ONSHIPPING", "DONE", "FAILED") NULL,
	CONSTRAINT order_pkey PRIMARY KEY (id)
);

-- order foreign keys
ALTER TABLE `order` ADD CONSTRAINT order_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE `order` ADD CONSTRAINT order_ward_code_fkey FOREIGN KEY (ward_code) REFERENCES wards(code) ON DELETE CASCADE;

-- CREATE order_voucher TABLE
CREATE TABLE order_voucher (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    order_id integer UNSIGNED NOT NULL,
    voucher_id integer UNSIGNED NOT NULL,
	CONSTRAINT order_pkey PRIMARY KEY (id)
);

-- order_voucher unique keys
ALTER TABLE order_voucher ADD UNIQUE KEY (order_id, voucher_id);

-- order_voucher foreign keys
ALTER TABLE order_voucher ADD CONSTRAINT order_voucher_order_id_fkey FOREIGN KEY (order_id) REFERENCES `order`(id) ON DELETE CASCADE;
ALTER TABLE order_voucher ADD CONSTRAINT order_voucher_voucher_id_fkey FOREIGN KEY (voucher_id) REFERENCES voucher(id) ON DELETE CASCADE;

-- CREATE order_detail TABLE
CREATE TABLE order_detail (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    order_id integer UNSIGNED NOT NULL,
    product_inventory_id integer UNSIGNED NOT NULL,
	quantity smallint NOT NULL,
	CONSTRAINT order_detail_pkey PRIMARY KEY (id)
);

-- order_detail unique keys
ALTER TABLE order_detail ADD UNIQUE KEY (order_id, product_inventory_id);

-- order_detail foreign keys
ALTER TABLE order_detail ADD CONSTRAINT order_detail_order_id_fkey FOREIGN KEY (order_id) REFERENCES `order`(id) ON DELETE CASCADE;
ALTER TABLE order_detail ADD CONSTRAINT order_detail_product_inventory_id_fkey FOREIGN KEY (product_inventory_id) REFERENCES product_inventory(id) ON DELETE CASCADE;

-- CREATE bill TABLE
CREATE TABLE bill (
	id integer UNSIGNED NOT NULL AUTO_INCREMENT,
    order_id integer UNSIGNED NOT NULL,
    user_id integer UNSIGNED NOT NULL,
    amount smallint NOT NULL,
    discount integer NULL,
    total integer NOT NULL,
    created_date datetime DEFAULT NOW(),
	CONSTRAINT bill_pkey PRIMARY KEY (id)
);

-- bill foreign keys
ALTER TABLE bill ADD CONSTRAINT bill_order_id_fkey FOREIGN KEY (order_id) REFERENCES `order`(id) ON DELETE CASCADE;
ALTER TABLE bill ADD CONSTRAINT bill_user_id_fkey FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE;
