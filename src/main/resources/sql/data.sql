--
--  RUN THIS ONLY WITH NEW SCHEMA OR
--  WITH EMPTY TABLES AND AUTO_INCREMENTS = 0
--
--  IN OTHER CASE IT WILL BE FK VIOLATION
--

USE `totema`;

INSERT INTO `conversion_rate` (`ID`, `COUNTRY_KEY`, `PERIOD_KEY`, `CONVERSION_TO_LOCAL`) VALUES
  (1, 1, 2, 3),
  (2, 1, 3, 12);

INSERT INTO `country` (`ID`, `NAME`, `ISO_THREE_LETTER_CODE`, `ISO_TWO_LETTER_CODE`, `ISO_THREE_DIGIT_CODE`, `CURRENCY_NAME`) VALUES
  (1, 'United States', 'USA', 'US', 840, 'American dollar'),
  (2, 'Canada', 'CAN', 'CA', 124, 'Canadian dollar'),
  (3, 'Mexico', 'MEX', 'MX', 484, 'Mexican peso');


INSERT INTO `date` (`ID`, `TIME_STAMP`, `YEAR`, `QUARTER`, `MONTH_NAME`, `MONTH_INT`, `WEEK`, `DAY`) VALUES
  (1, NULL, 2017, 1, 'January', 1, 1, 10),
  (2, NULL, 2017, 1, 'January', 1, 1, 13),
  (3, NULL, 2017, 1, 'January', 1, 1, 15),
  (4, NULL, 2017, 1, 'January', 1, 1, 16),
  (5, NULL, 2017, 1, 'January', 1, 1, 17),
  (6, NULL, 2017, 1, 'January', 1, 1, 18),
  (7, NULL, 2017, 1, 'January', 1, 1, 20),
  (8, NULL, 2017, 1, 'January', 1, 1, 21);


INSERT INTO `employee` (`ID`, `USERNAME`, `PASSWORD_HASH`, `NAME`, `HIRE_DATE`, `OFFICE_KEY`, `TITLE`, `YEAR_SALARY`, `CONTRACT_TILL`, `REPORTS_TO`, `ROLE`) VALUES
  (1, 'test', '$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Erik Presley', '2006-12-11', 1, 'President', 500000, '2016-12-11', 1, 'ADMIN'),
  (2, 'test1', '$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Helen Brolin', '2013-09-02', 1, 'Sales Representative', 180000, '2014-08-31', 1, 'SALESMAN'),
  (3, 'qqq', '$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Judy Crawford', '2012-09-01', 2, 'Systems Manager', 100000, '2017-08-31', 1, 'DISABLED'),
  (4, 'www', '$2a$12$5EeGW2rSJVdrg4g8bp9g0exNjaGsjHNAq0BkR6UTU5baFr8Y3i522', 'Joan Callins', '2012-09-01', 1, 'Sales Manager', 200000, '2017-08-31', 1, 'ANALYST');


INSERT INTO `office` (`ID`, `COUNTRY_KEY`, `CITY`, `ADDRESS`, `FAX`, `PHONE`, `POSTAL_CODE`) VALUES
  (1, 1, 'New York', '234 Sun Street', '(206) 567 5690', '(206) 567 5670', '98122'),
  (2, 1, 'Seattle', '234 Jump Street', '(226) 456 5690', '(226) 456 5670', '12122'),
  (3, 2, 'Montreal', '1400 Boul. De Maissoneuve, app-204', '+1 (514) 841 2642', '+1 (514) 841 2640', 'H3T 7N8');


INSERT INTO `order` (`ID`, `DATE_KEY`, `EMPLOYEE_KEY`, `OFFICE_KEY`, `QUANTITY`, `PRICE`, `COST`, `GROSS_MARGIN`) VALUES
  (1, 1, 3, 1, 11, 6540, 7790, 1250),
  (2, 2, 3, 1, 2, 3100, 3780, 680);


INSERT INTO `product` (`ID`, `CODE`, `TYPE_KEY`, `NAME`, `SIZE`, `COLOR`) VALUES
  (1, 1110, 2, 'Women''s winter jeans', '29', 'blue'),
  (2, 1110, 2, 'Women''s winter jeans', '30', 'blue'),
  (3, 1110, 2, 'Women''s winter jeans', '31', 'blue'),
  (4, 1111, 2, 'Women''s winter jeans', '29', 'dark blue'),
  (5, 1111, 2, 'Women''s winter jeans', '30', 'dark blue'),
  (6, 1111, 2, 'Women''s winter jeans', '31', 'dark blue'),
  (7, 3210, 1, 'Teen Tennis Suit', 'S', 'blue'),
  (8, 3210, 1, 'Teen Tennis Suit', 'M', 'blue'),
  (9, 3210, 1, 'Teen Tennis Suit', 'L', 'blue');


INSERT INTO `product_list` (`ID`, `ORDER_KEY`, `PRODUCT_KEY`, `QUANTITY`, `UNIT_COST`, `UNIT_PRICE`, `GROSS_MARGIN`) VALUES
  (1, 1, 1, 1, 1550, 1790, 240),
  (2, 1, 7, 10, 499, 600, 101),
  (3, 2, 2, 2, 1550, 1890, 340);


INSERT INTO `product_type` (`ID`, `GENDER`, `AGE`, `SEASON`, `TYPE`) VALUES
  (1, 'm', '3-15 Years', 'summer', 'sportswear'),
  (2, 'w', 'Adult', 'winter', 'jeans'),
  (3, 'm', '3-15 Years', 'summer', 'swimwear');
