package naree.dao;

import naree.db.domain.Commnty;

public interface CommntyDao {

	/**
	 * 수다방(커뮤니티) 글 저장
	 * @param commnty
	 * @return
	 */
	int insertCommnty(Commnty commnty);

}
