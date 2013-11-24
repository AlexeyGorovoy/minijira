USE `minijira`;
DELIMITER $$

CREATE TRIGGER customer_before_delete BEFORE DELETE ON `customer` FOR EACH ROW
begin
	
	delete from project where customer_id = OLD.customer_id;

end;
