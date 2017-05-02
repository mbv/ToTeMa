CREATE DATABASE IF NOT EXISTS `totema`
  DEFAULT CHARACTER SET utf8;
USE `totema`;

CREATE USER IF NOT EXISTS 'totema_admin'@'localhost'
  IDENTIFIED BY '.GT`7rW*"NHQP@/A';
GRANT ALL PRIVILEGES ON `totema`.* TO 'totema_admin'@'localhost';

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `conversion_rate`;
CREATE TABLE `conversion_rate` (
  `ID` int(11) NOT NULL,
  `COUNTRY_KEY` int(11) NOT NULL,
  `PERIOD_KEY` int(11) NOT NULL,
  `CONVERSION_TO_LOCAL` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `ISO_THREE_LETTER_CODE` varchar(3) DEFAULT NULL,
  `ISO_TWO_LETTER_CODE` varchar(2) DEFAULT NULL,
  `ISO_THREE_DIGIT_CODE` int(11) DEFAULT NULL,
  `CURRENCY_NAME` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `date`;
CREATE TABLE `date` (
  `ID` int(11) NOT NULL,
  `TIME_STAMP` timestamp NULL DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `QUARTER` int(11) DEFAULT NULL,
  `MONTH_NAME` varchar(45) DEFAULT NULL,
  `MONTH_INT` int(11) DEFAULT NULL,
  `WEEK` int(11) DEFAULT NULL,
  `DAY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `PASSWORD_HASH` varchar(255) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `HIRE_DATE` date DEFAULT NULL,
  `OFFICE_KEY` int(11) DEFAULT NULL,
  `TITLE` varchar(45) DEFAULT NULL,
  `YEAR_SALARY` int(11) DEFAULT NULL,
  `CONTRACT_TILL` date DEFAULT NULL,
  `REPORTS_TO` int(11) DEFAULT NULL,
  `ROLE` ENUM ('SALESMAN', 'ANALYST', 'ADMIN', 'DISABLED')  DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `HISTORY`;
CREATE TABLE `HISTORY` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `HIRE_DATE` date DEFAULT NULL,
  `OFFICE_KEY` int(11) DEFAULT NULL,
  `TITLE` varchar(45) DEFAULT NULL,
  `YEAR_SALARY` int(11) DEFAULT NULL,
  `CONTRACT_TILL` date DEFAULT NULL,
  `REPORTS_TO` int(11) DEFAULT NULL,
  `EMPLOYEE_KEY` int(11) DEFAULT NULL,
  `EDIT_DATE` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `office`;
CREATE TABLE `office` (
  `ID` int(11) NOT NULL,
  `COUNTRY_KEY` int(11) DEFAULT NULL,
  `CITY` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `FAX` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `POSTAL_CODE` varchar(45) DEFAULT NULL,
  `STATUS` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `ID` int(11) NOT NULL,
  `DATE_KEY` int(11) NOT NULL,
  `EMPLOYEE_KEY` int(11) NOT NULL,
  `OFFICE_KEY` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PRICE` int(11) DEFAULT NULL,
  `COST` int(11) DEFAULT NULL,
  `GROSS_MARGIN` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `ID` int(11) NOT NULL,
  `CODE` int(11) DEFAULT NULL,
  `TYPE_KEY` int(11) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `SIZE` varchar(45) DEFAULT NULL,
  `COLOR` varchar(45) DEFAULT NULL,
  `STATUS` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_list`;
CREATE TABLE `product_list` (
  `ID` int(11) NOT NULL,
  `ORDER_KEY` int(11) DEFAULT NULL,
  `PRODUCT_KEY` int(11) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `UNIT_COST` int(11) DEFAULT NULL,
  `UNIT_PRICE` int(11) DEFAULT NULL,
  `GROSS_MARGIN` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `ID` int(11) NOT NULL,
  `GENDER` varchar(1) DEFAULT NULL,
  `AGE` varchar(45) DEFAULT NULL,
  `SEASON` varchar(45) DEFAULT NULL,
  `TYPE` varchar(45) DEFAULT NULL,
  `STATUS` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `conversion_rate`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `country`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `date`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `employee`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `HISTORY`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `office`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `order`
  ADD PRIMARY KEY (`ID`,`DATE_KEY`,`EMPLOYEE_KEY`,`OFFICE_KEY`);

ALTER TABLE `product`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `product_list`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `product_type`
  ADD PRIMARY KEY (`ID`);


ALTER TABLE `conversion_rate`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `country`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `date`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `employee`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `HISTORY`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `office`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `order`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `product`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `product_type`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

SET FOREIGN_KEY_CHECKS=1;


-- -----------------------------------------------------------
-- Spring OAuth2 required tables
-- -----------------------------------------------------------

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id`          VARCHAR(256) DEFAULT NULL,
  `token`             BLOB,
  `authentication_id` VARCHAR(256) DEFAULT NULL,
  `user_name`         VARCHAR(256) DEFAULT NULL,
  `client_id`         VARCHAR(256) DEFAULT NULL,
  `authentication`    BLOB,
  `refresh_token`     VARCHAR(256) DEFAULT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id`               VARCHAR(256) NOT NULL,
  `resource_ids`            VARCHAR(256)  DEFAULT NULL,
  `client_secret`           VARCHAR(256)  DEFAULT NULL,
  `scope`                   VARCHAR(256)  DEFAULT NULL,
  `authorized_grant_types`  VARCHAR(256)  DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(256)  DEFAULT NULL,
  `authorities`             VARCHAR(256)  DEFAULT NULL,
  `access_token_validity`   INT(11)       DEFAULT NULL,
  `refresh_token_validity`  INT(11)       DEFAULT NULL,
  `additional_information`  VARCHAR(4096) DEFAULT NULL,
  `autoapprove`             VARCHAR(256)  DEFAULT NULL,
  PRIMARY KEY (`client_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code`           VARCHAR(256) DEFAULT NULL,
  `authentication` BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id`       VARCHAR(256) DEFAULT NULL,
  `token`          BLOB,
  `authentication` BLOB
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------------
-- OAuth2 clients data
-- -----------------------------------------------------------
INSERT INTO `oauth_client_details` VALUES (
  'web_client' /* Client ID */,
               NULL,
               'lH2HvQ4Vk3f8k0S', /* Client Secret */
               'read,write,trust',
               'password,authorization_code,refresh_token,implicit',
               '',
               'ROLE_USER',
               NULL,
               NULL,
               NULL,
               NULL
);

