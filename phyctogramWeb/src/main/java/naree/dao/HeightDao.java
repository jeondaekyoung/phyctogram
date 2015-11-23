package naree.dao;

import java.util.List;

import naree.db.domain.Height;

public interface HeightDao {

	/**
	 * 키정보 저장하기
	 * @param insHeight
	 */
	void insertHeight(Height insHeight);

	/**
	 * user_seq의 height 조회하기
	 * @param user_seq
	 * @return
	 */
	List<Height> selectHeightByUser_seq(String user_seq);
}
