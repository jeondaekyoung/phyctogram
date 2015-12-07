package naree.service;

import naree.db.domain.Users;

public interface UsersService {

	/**
	 * 내 아이 등록하기
	 * @param users
	 * @return
	 */
	int registerUsers(Users users);

}
