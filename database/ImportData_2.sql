
-- DATA for bank
INSERT INTO bank (name) VALUES
	("Agribank"),
    ("MB"),
    ("VPBank"),
    ("VietinBank"),
    ("Vietcombank"),
    ("VIB"),
    ("TPBank"),
    ("BIDV"),
    ("ACB"),
    ("Techcombank");
    
-- DATA for category
INSERT INTO category (name) VALUES
	("Điện thoại"),
    ("Laptop"),
    ("Tablet"),
    ("Smartwatch"),
    ("Đồng hồ"),
    ("Sạc, cáp"),
    ("Tai nghe");
    
-- DATA for manufactory
INSERT INTO manufactory (category_id, name) VALUES
	(1, "iPhone"),
    (1, "Samsung"),
    (1, "Oppo"),
    (1, "Xiaomi"),
    (1, "Nokia"),
    (2, "Macbook"),
    (2, "Dell"),
    (2, "Lenovo"),
    (2, "Asus"),
    (2, "Hp"),
    (2, "Lg"),
    (2, "Acer"),
    (2, "Msi"),
    (2, "Surface"),
    (3, "iPad"),
    (3, "Samsung"),
    (3, "Xiaomi"),
    (3, "Oppo"),
    (3, "Lenovo"),
    (4, "Applewatch"),
    (4, "Samsung"),
    (4, "Xiaomi"),
    (4, "Befit"),
    (4, "Garmin"),
    (4, "Oppo"),
    (4, "Amazfit"),
    (5, "Casio"),
    (5, "Orient"),
    (5, "Citizen"),
    (5, "G-Sock"),
    (6, "Apple"),
    (6, "Samsung"),
    (6, "Anker"),
    (6, "Baseus"),
    (7, "Apple"),
    (7, "Samsung"),
    (7, "Sony"),
    (7, "Xiaomi"),
    (7, "Jbl"),
    (7, "Soundpeats");

-- DATA for color
INSERT INTO color (name) VALUES
	("Đen"),
    ("Trắng"),
    ("Vàng"),
    ("Xanh");
    
