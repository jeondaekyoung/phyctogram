package naree.db.mapper;

import java.util.HashMap;

import naree.db.domain.Admin;

public interface AdminMapper {

	Admin searchAdminById(String id);

	int searchAdminByAdmin(Admin admin);
	
	int registerBuy(HashMap<String, Object> map);
	
	int totalPrice();

}
