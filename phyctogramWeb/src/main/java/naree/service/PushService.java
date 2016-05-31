package naree.service;

import java.util.List;

import naree.db.domain.Member;

public interface PushService {
	/**
	 * selectBox 목록 불러오기
	 * @param pageCnt
	 * @return
	 */
	List<Member> selectBoxList();
}
