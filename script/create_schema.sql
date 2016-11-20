CREATE DATABASE `stockcontrol_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE USER 'sc_user'@'localhost' IDENTIFIED BY 'aga342x';

GRANT ALL PRIVILEGES ON stockcontrol_db. * TO 'sc_user'@'localhost';