-- Dumping database structure for msdpizza
drop database IF EXISTS msdpizza;
create database msdpizza;
use msdpizza;


-- Dumping structure for table msdpizza.PizzaOrder
DROP TABLE IF EXISTS PizzaOrder;
CREATE TABLE PizzaOrder (
  orderId int(11) unsigned NOT NULL AUTO_INCREMENT,
  pizzaName varchar(20) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  bill double DEFAULT NULL,
  customerContactNumber varchar(20) DEFAULT NULL,
  PRIMARY KEY (orderId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

commit;