package naree.dao;

import naree.db.domain.Admin;

public interface AdminDao {

	/**
	 * 관리자 아이디 확인
	 * @param id
	 * @return
	 */
	Admin searchAdminById(String id);

	/**
	 *  관리자 비번 확인
	 * @param admin
	 * @return
	 */
	boolean searchAdminByAdmin(Admin admin);

}
