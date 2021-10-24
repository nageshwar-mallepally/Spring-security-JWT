SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `object_id_seq`;

CREATE TABLE `object_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `city_name` varchar(255) NOT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `state_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_city_name_` (`city_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_country_name_` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `parent_object` RESTRICT;

CREATE TABLE `parent_object` (
  `object_id` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` date NOT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `modified_date` date NOT NULL,
  `object_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `default_group` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `image` tinyblob,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `mobile_device` varchar(255) DEFAULT NULL,
  `person_type` int(11) NOT NULL,
  `phone_mobile` varchar(255) DEFAULT NULL,
  `phone_office` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  PRIMARY KEY (`person_id`),
  CONSTRAINT `FK1_person_object_id` FOREIGN KEY (`person_id`) REFERENCES `parent_object` (`object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `external` bit(1) NOT NULL,
  `fingerprintdata` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `is_admin` bit(1) NOT NULL,
  `is_locked` bit(1) NOT NULL,
  `is_superuser` bit(1) NOT NULL,
  `login_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `login_id` int(11) NOT NULL,
  `person_id` int(11) NOT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `UK_bgthcqk4l4tobdqw0okhp6ein` (`login_name`),
  UNIQUE KEY `UK_9xxl9jxx522w1moi1qjre68ss` (`person_id`),
  CONSTRAINT `FKm9m2lrro6bv498jq3u0f413t2` FOREIGN KEY (`login_id`) REFERENCES `parent_object` (`object_id`),
  CONSTRAINT `FKd67i45ckswk2fr88oonlyjkyf` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `property`;

CREATE TABLE `property` (
  `address_text` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) NOT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pincode` bigint(20) DEFAULT NULL,
  `property_id` varchar(255) DEFAULT NULL,
  `property_type` varchar(255) DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `units` varchar(255) DEFAULT NULL,
  `is_deleted` bit,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKhqpq0e9586jw8ebdokv4svema` FOREIGN KEY (`id`) REFERENCES `parent_object` (`object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `property_image`;

CREATE TABLE `property_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` mediumblob,
  `name` varchar(255) DEFAULT NULL,
  `property_id` int(11) NOT NULL,
  `image_type`  varchar(255),
  PRIMARY KEY (`id`),
  KEY `FK8myddv2ina4svuo189ju03v9o` (`property_id`),
  CONSTRAINT `FK8myddv2ina4svuo189ju03v9o` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `property_for_sale`;

CREATE TABLE `property_for_sale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sale_units` varchar(255) DEFAULT NULL,
  `quantity_price` DOUBLE,
  `property_id` int(11) NOT NULL,
  `funded` DOUBLE,
   PRIMARY KEY (`id`),
   CONSTRAINT `sale_property_id` FOREIGN KEY (`property_id`) REFERENCES `property` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `bid_sale_property`;

CREATE TABLE `bid_sale_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number_of_units` varchar(255) DEFAULT NULL,
  `price_quotation` DOUBLE,
   PRIMARY KEY (`id`),
  `property_sale_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
   CONSTRAINT `bid_property_id` FOREIGN KEY (`property_id`) REFERENCES `property` (`Id`) ON DELETE CASCADE,
   CONSTRAINT `property_for_sale_bid` FOREIGN KEY (`property_sale_id`) REFERENCES `property_for_sale` (`Id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
  `session_id` int(11) NOT NULL,
  `ip_address` varchar(255) NOT NULL,
  `login_time` date NOT NULL,
  `logout_time` datetime(6) NOT NULL,
  `user_agent` varchar(255) NOT NULL,
  `login_id` int(11) NOT NULL,
  PRIMARY KEY (`session_id`),
  KEY `FKn8srjps2vj7tdyoawbmsrl1ao` (`login_id`),
  CONSTRAINT `FKn8srjps2vj7tdyoawbmsrl1ao` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `session_id_seq`;

CREATE TABLE `session_id_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  `state_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qtjsbpmp2ejq0753ktldenyqo` (`state_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `image` longtext,
  `last_name` varchar(20) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `phone_number` bigint(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

COMMIT;