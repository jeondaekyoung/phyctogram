package naree.service;

import naree.db.domain.Commnty;

public interface CommntyService {

	/**
	 * 수다방(커뮤니티) 글 저장
	 * @param commnty
	 * @return
	 */
	int registerCommnty(Commnty commnty);

}
