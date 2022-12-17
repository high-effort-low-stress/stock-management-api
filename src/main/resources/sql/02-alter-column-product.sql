USE `stock_management`;
    ALTER TABLE `product` DROP COLUMN `expected_resale_price`;
    ALTER TABLE `product` ADD `quantity`int DEFAULT 0;


