CREATE DATABASE `stock_management`;
USE `stock_management`;

CREATE TABLE `stock_management`.`product` (
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `uuid`        VARCHAR(36) NOT NULL UNIQUE,
    `name`       VARCHAR(60) NOT NULL,
    `description`  VARCHAR(60) NOT NULL,
    `expected_resale_price`       decimal(6,2) NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
	`deleted_at` TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `uuid_unique` (`uuid` ASC)
);

CREATE TABLE `stock_management`.`order` (
    `id`          BIGINT,
    `product_id`  BIGINT REFERENCES product(id),
    `uuid`        VARCHAR(36) NOT NULL UNIQUE,
    `purchase_price`       DECIMAL(6,2) NOT NULL,
    `tax`       DECIMAL(6,2) NOT NULL,
    `resale_price`       DECIMAL(6,2) NOT NULL,
	`status`     ENUM('ON_TRANSIT', 'AVAILABLE', 'SOLD') NOT NULL,
    `sold_at` 	 TIMESTAMP NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_order_product`
        FOREIGN KEY (`product_id`)
        REFERENCES `stock_management`.`product` (`id`)
);

CREATE TABLE `stock_management`.`category` (
    `id`          INT,
    `uuid`        VARCHAR(36) NOT NULL UNIQUE,
    `name`       VARCHAR(40) NOT NULL,
    `category_id`  int REFERENCES category(id),
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_category_category`
        FOREIGN KEY (`category_id`)
        REFERENCES `stock_management`.`category` (`id`)
);

CREATE TABLE `stock_management`.`product_category` (
    `product_id`  BIGINT REFERENCES product(id),
    `category_id`  INT REFERENCES category(id),
    CONSTRAINT `fk_pc_product`
        FOREIGN KEY (`product_id`)
        REFERENCES `stock_management`.`product` (`id`),
    CONSTRAINT `fk_pc_category`
        FOREIGN KEY (`category_id`)
        REFERENCES `stock_management`.`category` (`id`)
);