CREATE DATABASE `hangyub_url_shortener`;

USE hangyub_url_shortener;

DROP TABLE IF EXISTS url_map;
DROP TABLE IF EXISTS users;

CREATE TABLE `users`(
    `user_id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_email` VARCHAR(100) NOT NULL,
    `user_password` VARCHAR(30) NOT NULL,
    `user_nickname` VARCHAR(20) NOT NULL,
    `user_created_at` DATETIME NOT NULL,
    PRIMARY KEY(user_id)
);

CREATE TABLE `url_map`(
    `url_map_id` BIGINT NOT NULL AUTO_INCREMENT,
    `url_map_original` VARCHAR(255) NOT NULL,
    `url_map_short` VARCHAR(20) NOT NULL,
    `url_map_is_active` TINYINT(1) NOT NULL,
    `user_id` BIGINT NULL,
    `url_map_click` BIGINT NOT NULL DEFAULT 0,
    `url_map_created_at` DATETIME NOT NULL,
    `url_map_updated_at` DATETIME NOT NULL,
    `url_map_expired_at` DATETIME NULL,
    PRIMARY KEY(url_map_id),
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);
