CREATE DATABASE `bgmeetup`;

USE bgmeetup;

CREATE TABLE `game` (
  `id` varchar(36) CHARACTER SET utf8 NOT NULL,
  `title` varchar(128) CHARACTER SET utf8 NOT NULL,
  `description` mediumtext CHARACTER SET utf8 DEFAULT NULL,
  `minPlayers` int(11) NOT NULL,
  `maxPlayers` int(11) NOT NULL,
  `playingTime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8mb4;