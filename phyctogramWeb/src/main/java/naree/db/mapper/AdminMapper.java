package naree.db.mapper;

import naree.db.domain.Admin;

public interface AdminMapper {

	Admin searchAdminById(String id);

	int searchAdminByAdmin(Admin admin);

}
