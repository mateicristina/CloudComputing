CREATE DATABASE `bgmeetup`;

USE bgmeetup;

CREATE TABLE `user` (
  `id` varchar(36) CHARACTER SET utf8 NOT NULL,
  `email` varchar(256) CHARACTER SET utf8 NOT NULL,
  `firstName` varchar(128) CHARACTER SET utf8 NOT NULL,
  `lastName` varchar(128) CHARACTER SET utf8 NOT NULL,
  `location` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `passwordHash` varbinary(128) NOT NULL,
  `passwordSalt` varbinary(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;