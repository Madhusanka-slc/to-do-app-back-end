CREATE TABLE `user`(
    `user_name` VARCHAR(200) PRIMARY KEY,
    `full_name` VARCHAR(300) NOT NULL ,
    `password` VARCHAR(50) NOT NULL
);

CREATE  TABLE  `todo_item`(
    `id` INT AUTO_INCREMENT  PRIMARY KEY,
    `user_name` VARCHAR(200) NOT NULL,
    `description` VARCHAR(500) NOT NULL,
    `status` ENUM('DONE','NOT_DONE') NOT NULL,
    CONSTRAINT `fk_user` FOREIGN KEY (`user_name`) REFERENCES `user`(`user_name`)
);

/* Dummy Data */

INSERT INTO `user` (`user_name`, `full_name`, `password`) VALUES ('Kasun', 'Nuwan', '1234567');