/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.9 : Database - ssm
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_sys_bill` */

DROP TABLE IF EXISTS `t_sys_bill`;

CREATE TABLE `t_sys_bill` (
  `code` varchar(20) NOT NULL,
  `prefix` varchar(20) DEFAULT NULL,
  `dateFormat` varchar(20) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_bill` */

insert  into `t_sys_bill`(`code`,`prefix`,`dateFormat`,`num`) values ('menuId','M',NULL,5),('permissionId','P',NULL,5),('roleId','R',NULL,2);

/*Table structure for table `t_sys_bill_sequnce` */

DROP TABLE IF EXISTS `t_sys_bill_sequnce`;

CREATE TABLE `t_sys_bill_sequnce` (
  `code` varchar(255) NOT NULL,
  `sequnce` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_bill_sequnce` */

insert  into `t_sys_bill_sequnce`(`code`,`sequnce`) values ('menuId',21),('permissionId',39),('roleId',3);

/*Table structure for table `t_sys_menu` */

DROP TABLE IF EXISTS `t_sys_menu`;

CREATE TABLE `t_sys_menu` (
  `id` varchar(6) NOT NULL DEFAULT '' COMMENT '菜单ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `parentName` varchar(50) DEFAULT '' COMMENT '父菜单名称',
  `url` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单url',
  `view` varchar(50) DEFAULT NULL COMMENT '视图',
  `level` int(11) NOT NULL DEFAULT '-1' COMMENT '菜单级别',
  `seq` int(11) NOT NULL DEFAULT '-1' COMMENT '菜单顺序',
  `icon` varchar(50) NOT NULL DEFAULT '' COMMENT '菜单图标',
  `parent` varchar(50) NOT NULL DEFAULT '' COMMENT '父菜单编码',
  `creator` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(50) NOT NULL DEFAULT '' COMMENT '更新人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='`t_sys_menu`';

/*Data for the table `t_sys_menu` */

insert  into `t_sys_menu`(`id`,`name`,`parentName`,`url`,`view`,`level`,`seq`,`icon`,`parent`,`creator`,`createTime`,`updateBy`,`updateTime`) values ('M00001','系统综合管理','','',NULL,0,1,'fa fa-tachometer','0','admin','2017-04-06 16:00:52','admin','2017-07-11 11:26:25'),('M00003','系统菜单维护','系统综合管理','/core/menu','/core/menu',1,1,'','M00001','admin','2017-04-06 15:59:23','admin','2017-04-10 14:42:25'),('M00004','系统角色维护','系统综合管理','/core/role','/core/role',1,2,'','M00001','admin','2017-04-06 15:52:43','admin','2017-04-10 14:42:30'),('M00006','状态类型维护','系统综合管理','/core/type','/core/type',1,3,'','M00001','admin','2017-04-07 10:11:41','admin','2017-04-10 14:42:41'),('M00007','状态信息维护','系统综合管理','/core/status','/core/status',1,4,'','M00001','admin','2017-04-07 13:35:38','admin','2017-04-10 14:42:46'),('M00008','系统监控','系统综合管理','/druid/index.html',NULL,1,99,'','M00001','admin','2017-04-07 13:36:07','admin','2017-04-08 10:32:56'),('M00009','系统权限维护','系统综合管理','/core/permission','/core/permission',1,6,'','M00001','admin','2017-04-08 10:37:07','admin','2017-04-10 14:42:58'),('M00011','系统用户维护','系统综合管理','/core/user','/core/user',1,7,'','M00001','admin','2017-04-08 11:52:55','admin','2017-04-10 14:43:02');

/*Table structure for table `t_sys_menu_permission` */

DROP TABLE IF EXISTS `t_sys_menu_permission`;

CREATE TABLE `t_sys_menu_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuId` varchar(6) NOT NULL,
  `permissionId` varchar(6) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_menu_permission` */

/*Table structure for table `t_sys_permission` */

DROP TABLE IF EXISTS `t_sys_permission`;

CREATE TABLE `t_sys_permission` (
  `id` varchar(6) NOT NULL COMMENT '权限ID',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '权限名称',
  `sign` varchar(20) NOT NULL DEFAULT '' COMMENT '权限标识',
  `desc` varchar(100) NOT NULL DEFAULT '' COMMENT '权限描述',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(30) NOT NULL DEFAULT '' COMMENT '创建人',
  `updateBy` varchar(30) NOT NULL DEFAULT '' COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='`权限信息表`';

/*Data for the table `t_sys_permission` */

insert  into `t_sys_permission`(`id`,`name`,`sign`,`desc`,`createTime`,`updateTime`,`creator`,`updateBy`) values ('P00001','新建','create','新建数据的权限','2017-04-07 08:31:15','2017-04-07 09:54:47','admin','admin'),('P00002','删除','delete','删除数据的权限','2017-04-07 08:31:36','2017-04-07 08:31:36','admin','admin'),('P00003','更新','update','更新数据的权限','2017-04-07 08:31:50','2017-04-07 08:31:50','admin','admin'),('P00004','查询','query','查询数据的权限','2017-04-07 08:32:00','2017-04-07 08:32:00','admin','admin'),('P00005','添加权限','addPermission','菜单添加权限的权限','2017-04-07 08:32:29','2017-04-07 08:32:29','admin','admin'),('P00006','状态修改','updateStatus','状态修改的权限','2017-04-07 10:12:32','2017-04-07 10:12:32','admin','admin'),('P00007','添加角色','addRole','添加角色的权限','2017-04-08 11:54:14','2017-04-08 11:54:14','admin','admin'),('P00008','添加菜单','addMenu','添加菜单的权限','2017-04-08 14:14:43','2017-04-08 14:14:43','admin','admin'),('P00009','添加菜单权限','addMenuPermission','添加菜单权限的权限','2017-04-08 14:15:05','2017-04-08 14:15:05','admin','admin'),('P00012','设置积分兑换默认值','saveSetting','设置积分兑换默认值权限','2017-06-28 09:23:35','2017-07-19 09:28:23','admin','admin'),('P00015','添加子菜单','addSubMenu','添加子菜单权限','2017-06-28 09:30:16','2017-06-28 09:30:16','admin','admin'),('P00016','导出','export','导出功能权限','2017-07-18 14:43:45','2017-07-18 14:43:45','admin','admin'),('P00029','合作单位列表查询','queryCityList','合作单位列表查询权限','2017-07-19 09:00:41','2017-07-19 09:28:07','admin','admin'),('P00030','查询默认配置','querySetting','查询默认配置权限','2017-07-19 09:04:51','2017-07-19 09:28:17','admin','admin'),('P00031','区域列表查询','queryAreaList','区域列表查询权限','2017-07-19 11:05:46','2017-07-19 11:05:46','admin','admin'),('P00032','街道列表查询','querySubdistrictList','街道列表查询权限','2017-07-19 11:06:16','2017-07-19 11:06:16','admin','admin'),('P00033','小区列表查询','queryVillageList','小区列表查询权限','2017-07-19 11:06:32','2017-07-19 11:06:32','admin','admin'),('P00034','区域信息保存','saveArea','区域信息保存权限','2017-07-19 11:06:57','2017-07-19 11:06:57','admin','admin'),('P00035','街道信息保存','saveSubdistrict','街道信息保存权限','2017-07-19 11:07:41','2017-07-19 11:07:41','admin','admin'),('P00036','小区信息保存','saveVillage','小区信息保存权限','2017-07-19 11:07:56','2017-07-19 11:07:56','admin','admin'),('P00037','信息统计','sum','信息统计权限','2017-07-19 14:48:32','2017-07-19 14:48:32','admin','admin'),('P00039','修改密码','modifyPwd','修改密码的权限','2017-07-19 15:57:59','2017-07-19 15:57:59','admin','admin');

/*Table structure for table `t_sys_role` */

DROP TABLE IF EXISTS `t_sys_role`;

CREATE TABLE `t_sys_role` (
  `id` varchar(6) NOT NULL COMMENT '主键id',
  `name` varchar(30) NOT NULL COMMENT '角色名称',
  `desc` varchar(255) NOT NULL COMMENT '角色描述',
  `sign` varchar(20) NOT NULL COMMENT '角色标识',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) NOT NULL COMMENT '创建人',
  `updateBy` varchar(50) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='`系统角色表`';

/*Data for the table `t_sys_role` */

insert  into `t_sys_role`(`id`,`name`,`desc`,`sign`,`createTime`,`updateTime`,`creator`,`updateBy`) values ('R00001','超级管理员','超级管理员角色','admin','2017-04-07 17:43:39','2017-06-27 15:19:53','admin','admin');

/*Table structure for table `t_sys_role_menu` */

DROP TABLE IF EXISTS `t_sys_role_menu`;

CREATE TABLE `t_sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `roleId` varchar(6) NOT NULL COMMENT '角色id',
  `menuId` varchar(6) NOT NULL COMMENT '菜单id',
  `creator` varchar(30) NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_role_menu` */

insert  into `t_sys_role_menu`(`id`,`roleId`,`menuId`,`creator`,`createTime`) values (195,'R00001','M00004','admin','2017-07-19 15:59:34'),(196,'R00001','M00001','admin','2017-07-19 15:59:34'),(200,'R00001','M00003','admin','2017-07-19 15:59:34'),(201,'R00001','M00011','admin','2017-07-19 15:59:34'),(202,'R00001','M00007','admin','2017-07-19 15:59:34'),(203,'R00001','M00009','admin','2017-07-19 15:59:34'),(204,'R00001','M00006','admin','2017-07-19 15:59:34');

/*Table structure for table `t_sys_role_menu_permission` */

DROP TABLE IF EXISTS `t_sys_role_menu_permission`;

CREATE TABLE `t_sys_role_menu_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `roleId` varchar(6) NOT NULL COMMENT '角色id',
  `menuId` varchar(6) NOT NULL COMMENT '菜单id',
  `permissionId` varchar(6) NOT NULL COMMENT '菜单权限id',
  `permissionSign` varchar(200) NOT NULL COMMENT '权限标识（冗余）',
  `creator` varchar(30) NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_role_menu_permission` */

insert  into `t_sys_role_menu_permission`(`id`,`roleId`,`menuId`,`permissionId`,`permissionSign`,`creator`,`createTime`) values (47,'R00001','M00004','P00001','/core/role:create','admin','2017-04-08 16:41:50'),(48,'R00001','M00004','P00002','/core/role:delete','admin','2017-04-08 16:41:50'),(49,'R00001','M00004','P00003','/core/role:update','admin','2017-04-08 16:41:50'),(50,'R00001','M00004','P00004','/core/role:query','admin','2017-04-08 16:41:50'),(51,'R00001','M00004','P00008','/core/role:addMenu','admin','2017-04-08 16:41:50'),(52,'R00001','M00004','P00009','/core/role:addMenuPermission','admin','2017-04-08 16:41:50'),(61,'R00001','M00009','P00001','/core/permission:create','admin','2017-04-08 16:42:00'),(62,'R00001','M00009','P00002','/core/permission:delete','admin','2017-04-08 16:42:00'),(63,'R00001','M00009','P00003','/core/permission:update','admin','2017-04-08 16:42:00'),(64,'R00001','M00009','P00004','/core/permission:query','admin','2017-04-08 16:42:00'),(83,'R00001','M00003','P00001','/core/menu:create','admin','2017-06-28 11:06:32'),(84,'R00001','M00003','P00002','/core/menu:delete','admin','2017-06-28 11:06:32'),(85,'R00001','M00003','P00003','/core/menu:update','admin','2017-06-28 11:06:32'),(86,'R00001','M00003','P00004','/core/menu:query','admin','2017-06-28 11:06:32'),(87,'R00001','M00003','P00005','/core/menu:addPermission','admin','2017-06-28 11:06:32'),(88,'R00001','M00003','P00015','/core/menu:addSubMenu','admin','2017-06-28 11:06:32'),(89,'R00001','M00006','P00001','/core/type:create','admin','2017-06-28 11:06:39'),(90,'R00001','M00006','P00002','/core/type:delete','admin','2017-06-28 11:06:39'),(91,'R00001','M00006','P00003','/core/type:update','admin','2017-06-28 11:06:39'),(92,'R00001','M00006','P00004','/core/type:query','admin','2017-06-28 11:06:39'),(93,'R00001','M00007','P00001','/core/status:create','admin','2017-06-28 11:06:42'),(94,'R00001','M00007','P00003','/core/status:update','admin','2017-06-28 11:06:42'),(95,'R00001','M00007','P00004','/core/status:query','admin','2017-06-28 11:06:42'),(96,'R00001','M00007','P00006','/core/status:updateStatus','admin','2017-06-28 11:06:42'),(270,'R00001','M00011','P00001','/core/user:create','admin','2017-07-19 15:59:48'),(271,'R00001','M00011','P00002','/core/user:delete','admin','2017-07-19 15:59:48'),(272,'R00001','M00011','P00003','/core/user:update','admin','2017-07-19 15:59:48'),(273,'R00001','M00011','P00004','/core/user:query','admin','2017-07-19 15:59:48'),(274,'R00001','M00011','P00007','/core/user:addRole','admin','2017-07-19 15:59:48'),(275,'R00001','M00011','P00039','/core/user:modifyPwd','admin','2017-07-19 15:59:48');

/*Table structure for table `t_sys_status` */

DROP TABLE IF EXISTS `t_sys_status`;

CREATE TABLE `t_sys_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(20) NOT NULL COMMENT '状态名称',
  `value` varchar(10) NOT NULL COMMENT '状态值',
  `type` int(11) NOT NULL COMMENT '状态类型',
  `status` bit(1) NOT NULL DEFAULT b'1' COMMENT '数据状态(0:启用,1:停用)',
  `desc` varchar(255) NOT NULL COMMENT '状态描述',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(30) NOT NULL COMMENT '创建人',
  `updateBy` varchar(30) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='`系统状态表`';

/*Data for the table `t_sys_status` */

insert  into `t_sys_status`(`id`,`name`,`value`,`type`,`status`,`desc`,`createTime`,`updateTime`,`creator`,`updateBy`) values (1,'正常','00',1,'','正常的用户状态','2017-04-07 14:46:02','2017-06-13 18:00:44','admin','admin'),(2,'停用','01',1,'','停用的用户状态','2017-04-07 14:59:17','2017-04-08 14:13:24','admin','admin'),(3,'注销','02',1,'','注销的用户状态','2017-04-07 15:05:00','2017-04-07 15:26:37','admin','admin'),(4,'启用','00',3,'','启用的权限状态','2017-04-07 15:47:40','2017-04-07 15:47:40','admin','admin');

/*Table structure for table `t_sys_status_type` */

DROP TABLE IF EXISTS `t_sys_status_type`;

CREATE TABLE `t_sys_status_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(30) NOT NULL COMMENT '类型名称',
  `value` varchar(50) NOT NULL COMMENT '类型值',
  `desc` varchar(255) NOT NULL COMMENT '类型描述',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(30) NOT NULL COMMENT '创建人',
  `updateBy` varchar(30) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='`状态类型表`';

/*Data for the table `t_sys_status_type` */

insert  into `t_sys_status_type`(`id`,`name`,`value`,`desc`,`createTime`,`updateTime`,`creator`,`updateBy`) values (1,'用户状态类型','t_sys_user','系统用户的状态类型','2017-04-07 11:53:09','2017-04-07 11:55:19','admin','admin'),(3,'权限状态类型','t_sys_permission','系统权限的状态类型','2017-04-07 15:45:08','2017-04-07 15:45:08','admin','admin');

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `loginId` varchar(30) NOT NULL COMMENT '登录id',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `disabled` bit(1) DEFAULT b'0' COMMENT '是否禁用0:启用1:禁用',
  `creator` varchar(30) NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateBy` varchar(30) NOT NULL COMMENT '更新人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isFirstLogin` bit(1) DEFAULT b'1' COMMENT '是否首次登录0:非首次1:首次',
  PRIMARY KEY (`id`),
  UNIQUE KEY `FLoginId` (`loginId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`id`,`loginId`,`name`,`password`,`email`,`disabled`,`creator`,`createTime`,`updateBy`,`updateTime`,`isFirstLogin`) values ('581476892ea3c04dfdc8bfabf52d2a74','admin','胡新胜','91bfefcd500418a51864e1f92a8c1ab903ae5977','wxxx@qq.com','\0','admin','2017-06-29 09:03:16','admin','2017-07-21 15:23:06','\0');

/*Table structure for table `t_sys_user_login` */

DROP TABLE IF EXISTS `t_sys_user_login`;

CREATE TABLE `t_sys_user_login` (
  `seqNo` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `id` varchar(32) NOT NULL COMMENT '登录用户id',
  `loginId` varchar(30) NOT NULL COMMENT '登录名',
  `ip` varchar(30) NOT NULL COMMENT '登录ip',
  `loginTime` timestamp NOT NULL COMMENT '登录时间',
  `name` varchar(50) NOT NULL COMMENT '登录用户名',
  PRIMARY KEY (`seqNo`),
  KEY `FK_LOGIN_ID` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=650 DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_user_login` */

/*Table structure for table `t_sys_user_role` */

DROP TABLE IF EXISTS `t_sys_user_role`;

CREATE TABLE `t_sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userId` varchar(32) NOT NULL COMMENT '用户id',
  `roleId` varchar(6) NOT NULL COMMENT '角色id',
  `creator` varchar(30) NOT NULL COMMENT '创建人',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `t_sys_user_role` */

insert  into `t_sys_user_role`(`id`,`userId`,`roleId`,`creator`,`createTime`) values (3,'581476892ea3c04dfdc8bfabf52d2a74','R00001','admin','2017-04-08 16:02:44');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
