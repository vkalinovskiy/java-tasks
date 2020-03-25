SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `track`;
DROP TABLE IF EXISTS `track_location`;
DROP TABLE IF EXISTS `user`;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `track_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `house` varchar(255) DEFAULT NULL,
  `postal_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `track` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lat` decimal(9,6) NOT NULL,
  `lng` decimal(9,6) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  `track_location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `track_user_id_fk` (`user_id`),
  KEY `track_track_location_id_fk` (`track_location_id`),
  CONSTRAINT `track_track_location_id_fk` FOREIGN KEY (`track_location_id`) REFERENCES `track_location` (`id`),
  CONSTRAINT `track_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;