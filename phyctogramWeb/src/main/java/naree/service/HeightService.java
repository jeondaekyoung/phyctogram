package naree.service;

import java.util.List;

import naree.db.domain.Height;

public interface HeightService {

	/**
	 * 키정보 저장하기
	 * @param insHeight
	 */
	void registerHeight(Height insHeight);

	/**
	 * user_seq의 height 불러오기
	 * @param user_seq
	 * @return
	 */
	List<Height> selectHeightByUser_seq(String user_seq);
}
