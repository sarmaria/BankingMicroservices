CREATE TABLE IF NOT EXISTS `customer`(
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `created_at` date DEFAULT NULL,
    `created_by` varchar(100) DEFAULT NULL,
    `updated_at` varchar(100) DEFAULT NULL,
    `updated_by` date DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `account`(
    `customer_id` int NOT NULL,
    `account_number` int AUTO_INCREMENT PRIMARY KEY,
    `account_type` varchar(100) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `created_at` date DEFAULT NULL,
    `created_by` varchar(100) DEFAULT NULL,
    `updated_at` varchar(100) DEFAULT NULL,
    `updated_by` date DEFAULT NULL
    );

