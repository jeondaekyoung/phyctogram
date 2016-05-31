package naree.dao;

import java.util.List;

import naree.db.domain.Admin;
import naree.db.domain.Notice;

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
	
	/**
	 *  구매내역 저장
	 * @param admin
	 * @return
	 */
	int registerBuy(String price, String etc);
	
	/**
	 *  구매내역 조회
	 * @param admin
	 * @return
	 */
	int totalPrice();
	
	
}
