
-- DATA for voucher
INSERT INTO voucher (code,discount_amount,stock,start_date,end_date,activated) VALUES
	("CODETEST00000001",500000,3,"2023-10-25","2023-12-31", true),
    ("CODETEST00000002",500000,3,"2023-09-25","2023-10-31", true),
    ("CODETEST00000003",500000,3,"2023-10-25","2023-12-31", false);
    
-- DATA for product_voucher
INSERT INTO product_voucher (product_id,voucher_id) VALUES
	(1,1),
    (2,1),
    (5,1),
    (15,1);
    
-- DATA for news_topic
INSERT INTO news_topic (name) VALUES
	("Bản tin công nghệ"),
    ("Sản phẩm mới"),
    ("Đánh giá"),
    ("Mẹo hay"),
    ("Tư vấn"),
    ("Khuyến mãi");
    
-- DATA for user
INSERT INTO user (username,password,email,role,full_name,created_date,activated) VALUES
	("admin","$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi","beduclauhvtc@gmail.com","ADMIN","Bế Đức Lẩu","2023-10-25",true),
    ("lau_mod","$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi","lau_mod@gmail.com","MOD","Lẩu Mod","2023-10-25",true),
    ("lau_manager","$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi","lau_manager@gmail.com","MANAGER","Lẩu Manager","2023-10-25",true),
    ("lau_staff","$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi","lau_staff@gmail.com","STAFF","Lẩu Staff","2023-10-25",true),
    ("lau_member","$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi","lau_member@gmail.com","MEMBER","Lẩu Member","2023-10-25",true);

-- DATA for news_post
INSERT INTO news_post (user_id,topic_id,title,cover,content,created_date) VALUES
	(1,1,"iPhone 15 Pro Max lập kỷ lục mới: Đạt tốc độ tải xuống nhanh nhất từ trước đến nay","https://cdn.tgdd.vn/Files/2023/10/17/1552108/image6-171023-112819_1280x720-800-resize.jpg","iPhone 14 Pro Max từng được mệnh danh là chiếc smartphone có tốc độ tải xuống nhanh nhất thế giới, nhưng giờ đây, iPhone 15 Pro Max vừa vượt qua cả giới hạn đó. Theo báo cáo mới nhất từ Ookla, iPhone 15 Pro Max có tốc độ tải xuống trung bình nhanh hơn gần đến 100% so với iPhone 14 Pro Max.
Ookla, công ty cung cấp dịch vụ kiểm tra tốc độ Internet đã công bố báo cáo hiệu suất di động mới nhất của mình vào ngày hôm nay. Báo cáo này so sánh các nhà mạng lớn ở Mỹ về tốc độ tải xuống, tải lên, tính nhất quán và tính sẵn có,... Ngoài ra, báo cáo còn cung cấp danh sách các smartphone cho tốc độ tải xuống, tải lên nhanh nhất với độ trễ trong quý 3 năm 2023.
iPhone 14 Pro Max từng là \"ông vua\" về tốc độ tải xuống dữ liệu dù chỉ trong thời gian ngắn. Theo báo cáo của Ookla, tốc độ tải xuống trung bình của iPhone 14 Pro Max đã giảm xuống còn 127.83 Mbps vào quý 2 năm 2023. Cũng vào thời điểm đó, Galaxy S23 Ultra 5G đã vượt qua iPhone 14 Pro Max với tốc độ tải xuống trung bình đạt mức 161.86 Mbps.
Nhưng mọi thứ đã khác đi khi iPhone 15 Pro Max ra mắt, chiếc smartphone cao cấp nhất của Apple đã đè bẹp các thiết bị khác trong danh sách với tốc độ tải xuống trung bình là 251.37 Mbps. Tốc độ này thậm chí còn nhanh hơn khoảng 10% so với iPhone 15 Pro và nhanh hơn iPhone 14 Pro Max khoảng 96.6%.
Bạn cảm thấy tốc độ tải xuống của iPhone 15 Pro Max như thế nào? Đừng quên để lại bình luận nhé!","2023-10-25");