/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.18-log : Database - goddess
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `goddess`;

/*Table structure for table `goddess_user` */

DROP TABLE IF EXISTS `goddess_user`;

CREATE TABLE `goddess_user` (
  `id` bigint(29) NOT NULL COMMENT '用户id',
  `code` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '用户code',
  `user_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '用户name',
  `password` varchar(80) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `mail` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `goddess_user_role` */

DROP TABLE IF EXISTS `goddess_user_role`;

CREATE TABLE `goddess_user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `godess_role` */

DROP TABLE IF EXISTS `godess_role`;

CREATE TABLE `godess_role` (
  `id` bigint(20) NOT NULL COMMENT '角色id',
  `role` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
