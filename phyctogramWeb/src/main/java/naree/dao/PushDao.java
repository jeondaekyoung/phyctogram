package naree.dao;

import java.util.List;

import naree.db.domain.Member;

public interface PushDao {
	
	/**
	 * selectBox 목록 읽어오기
	 * @param
	 * @return
	 */
	List<Member> selectBoxList();
}
