package naree.service;

import naree.db.domain.Admin;

public interface AdminService {

	/**
	 * 관리자 로그인하기
	 * 관리자 아이디가 사용중인지, 비밀번호는 맞는지 확인한다.
	 * @param id
	 * @param pw
	 * @return
	 */
	Admin login(String id, String pw);
	
	/**
	 * 구매내역 저장
	 * @param price
	 * @param etc
	 * @return
	 */
	int buy(String price, String etc);
	
	/**
	 * 구매내역 조회
	 * @param price
	 * @param etc
	 * @return
	 */
	int totalPrice();

}
