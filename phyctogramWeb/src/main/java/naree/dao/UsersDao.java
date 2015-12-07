package naree.dao;

import naree.db.domain.Users;

public interface UsersDao {

	/**
	 * 내 아이 등록
	 * @param users
	 * @return
	 */
	int insertUsers(Users users);

}
