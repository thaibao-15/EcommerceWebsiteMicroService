USE master;
GO

IF EXISTS (SELECT name FROM sys.databases WHERE name = N'dbBanHang')
BEGIN
    EXEC sp_MSforeachdb 'IF ''?'' = ''dbBanHang''
    BEGIN
        ALTER DATABASE [dbBanHang] SET SINGLE_USER WITH ROLLBACK IMMEDIATE 
    END'
    USE master
    DROP DATABASE dbBanHang
END

CREATE DATABASE dbBanHang
GO

USE dbBanHang
GO

-- Bảng user
CREATE TABLE [user] (
    id VARCHAR(255) PRIMARY KEY,
    dob DATE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    username VARCHAR(255)
);

-- Bảng role
CREATE TABLE role (
    name VARCHAR(255) PRIMARY KEY,
    description VARCHAR(255)
);

-- Bảng permission
CREATE TABLE permission (
    name VARCHAR(255) PRIMARY KEY,
    description VARCHAR(255)
);

-- Bảng role_permissions (quan hệ N-N giữa role và permission)
CREATE TABLE role_permissions (
    role_name VARCHAR(255),
    permissions_name VARCHAR(255),
    PRIMARY KEY (role_name, permissions_name),
    FOREIGN KEY (role_name) REFERENCES role(name) ON DELETE CASCADE,
    FOREIGN KEY (permissions_name) REFERENCES permission(name) ON DELETE CASCADE
);

-- Bảng user_roles (quan hệ N-N giữa user và role)
CREATE TABLE user_roles (
    user_id VARCHAR(255),
    roles_name VARCHAR(255),
    PRIMARY KEY (user_id, roles_name),
    FOREIGN KEY (user_id) REFERENCES [user](id) ON DELETE CASCADE,
    FOREIGN KEY (roles_name) REFERENCES role(name) ON DELETE CASCADE
);

-- Bảng invalidated_token (lưu token hết hạn)
CREATE TABLE invalidated_token (
    id VARCHAR(255) PRIMARY KEY,
    expiry_time DATETIME2(6)
);

-- Bảng Category (Danh mục sản phẩm)
CREATE TABLE category (
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    description NVARCHAR(500)
);

-- Bảng Product (Sản phẩm)
CREATE TABLE product (
    id INT IDENTITY PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    description NVARCHAR(MAX),
	image Nvarchar(max),
    price DECIMAL(18,2) NOT NULL,
	status nvarchar(250),
    stock INT NOT NULL,
    category_id INT,
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    updated_at DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Bảng Cart (Giỏ hàng - mỗi user có 1 giỏ hàng)
CREATE TABLE cart (
    id INT IDENTITY PRIMARY KEY,
    user_id VARCHAR(255),
	status VARCHAR(50),
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (user_id) REFERENCES [user](id)
);

-- Bảng CartItem (chi tiết giỏ hàng)
CREATE TABLE cart_item (
    id INT IDENTITY PRIMARY KEY,
    cart_id INT,
    product_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

-- Bảng Order (Đơn hàng)
CREATE TABLE [order] (
    id INT IDENTITY PRIMARY KEY,
    user_id VARCHAR(255),
    order_date DATETIME2 DEFAULT SYSDATETIME(),
    status NVARCHAR(50) DEFAULT N'PENDING',  -- PENDING, PAID, SHIPPED, COMPLETED, CANCELED
    total_amount DECIMAL(18,2) ,
    FOREIGN KEY (user_id) REFERENCES [user](id)
);

-- Bảng OrderDetail (Chi tiết đơn hàng)
CREATE TABLE order_detail (
    id INT IDENTITY PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    price DECIMAL(18,2) NOT NULL, -- giá tại thời điểm đặt
    FOREIGN KEY (order_id) REFERENCES [order](id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id)
);

-- Bảng Payment (Thanh toán)
CREATE TABLE payment (
    id INT IDENTITY PRIMARY KEY,
    order_id INT,
    payment_method NVARCHAR(50), -- e.g., COD, CreditCard, Paypal, VNPay
    payment_date DATETIME2 DEFAULT SYSDATETIME(),
    amount DECIMAL(18,2),
    status NVARCHAR(50) DEFAULT N'PENDING', -- PENDING, SUCCESS, FAILED
    FOREIGN KEY (order_id) REFERENCES [order](id)
);

-- Bảng Shipping (Vận chuyển)
CREATE TABLE shipping (
    id INT IDENTITY PRIMARY KEY,
    order_id INT,
    address NVARCHAR(500),
    city NVARCHAR(100),
    district NVARCHAR(100),
    ward NVARCHAR(100),
    phone NVARCHAR(20),
    status NVARCHAR(50) DEFAULT N'PROCESSING', -- PROCESSING, SHIPPING, DELIVERED
    FOREIGN KEY (order_id) REFERENCES [order](id)
);


-- USER
INSERT INTO [user] (id, dob, first_name, last_name, password, username) VALUES
('U1', '1998-05-20', 'Nguyen', 'An', '123456', 'an.nguyen'),
('U2', '2000-10-10', 'Tran', 'Binh', '654321', 'binh.tran'),
('U3', '1995-03-15', 'Le', 'Cuong', 'abcdef', 'cuong.le');

-- ROLE
INSERT INTO role (name, description) VALUES
('ADMIN', 'Quản trị hệ thống'),
('SELLER', 'Người bán hàng'),
('CUSTOMER', 'Khách hàng');

-- PERMISSION
INSERT INTO permission (name, description) VALUES
('MANAGE_USERS', 'Quản lý người dùng'),
('MANAGE_PRODUCTS', 'Quản lý sản phẩm'),
('VIEW_PRODUCTS', 'Xem sản phẩm');

-- USER_ROLES
INSERT INTO user_roles (user_id, roles_name) VALUES
('U1', 'ADMIN'),
('U2', 'SELLER'),
('U3', 'CUSTOMER');

-- ROLE_PERMISSIONS
INSERT INTO role_permissions (role_name, permissions_name) VALUES
('ADMIN', 'MANAGE_USERS'),
('SELLER', 'MANAGE_PRODUCTS'),
('CUSTOMER', 'VIEW_PRODUCTS');

-- INVALIDATED TOKEN
INSERT INTO invalidated_token (id, expiry_time) VALUES
('T1', '2025-09-01 10:00:00'),
('T2', '2025-09-02 12:30:00'),
('T3', '2025-09-03 15:45:00');

-- CATEGORY
INSERT INTO category (name, description) VALUES
(N'Điện thoại', N'Các loại điện thoại di động'),
(N'Laptop', N'Máy tính xách tay'),
(N'Phụ kiện', N'Tai nghe, sạc, ốp lưng');

-- PRODUCT
INSERT INTO product (name, description, price, stock, category_id) VALUES
(N'iPhone 15', N'Điện thoại Apple mới nhất', 25000000, 10, 1),
(N'Dell XPS 13', N'Laptop mỏng nhẹ cao cấp', 30000000, 5, 2),
(N'Tai nghe Bluetooth', N'Phụ kiện tai nghe không dây', 500000, 50, 3);

-- CART
INSERT INTO cart (user_id) VALUES
('U1'),
('U2'),
('U3');

-- CART_ITEM
INSERT INTO cart_item (cart_id, product_id, quantity) VALUES
(1, 1, 1), -- U1 mua iPhone 15
(2, 2, 1), -- U2 mua Laptop Dell
(3, 3, 2); -- U3 mua tai nghe

-- ORDER
INSERT INTO [order] (user_id, status, total_amount) VALUES
('U1', N'PAID', 25000000),
('U2', N'PENDING', 30000000),
('U3', N'PAID', 1000000);

-- ORDER_DETAIL
INSERT INTO order_detail (order_id, product_id, quantity, price) VALUES
(1, 1, 1, 25000000),
(2, 2, 1, 30000000),
(3, 3, 2, 500000);

-- PAYMENT
INSERT INTO payment (order_id, payment_method, amount, status) VALUES
(1, N'CreditCard', 25000000, N'SUCCESS'),
(2, N'COD', 30000000, N'PENDING'),
(3, N'VNPay', 1000000, N'SUCCESS');

-- SHIPPING
INSERT INTO shipping (order_id, address, city, district, ward, phone, status) VALUES
(1, N'123 Lê Lợi', N'Hà Nội', N'Cầu Giấy', N'Dịch Vọng', '0901234567', N'DELIVERED'),
(2, N'456 Nguyễn Huệ', N'Hồ Chí Minh', N'Quận 1', N'Bến Nghé', '0912345678', N'SHIPPING'),
(3, N'789 Lê Văn Sỹ', N'Hồ Chí Minh', N'Tân Bình', N'Phường 3', '0923456789', N'PROCESSING');

select * from [user]
select * from category
select * from product
select * from cart
select * from cart_item
select * from [order]
select * from role