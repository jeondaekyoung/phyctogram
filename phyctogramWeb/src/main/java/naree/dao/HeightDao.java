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

	/**
	 * 내 아이(유저)의 키 데이터 삭제
	 * @param user_seq
	 */
	void deleteHeightByUserSeq(String user_seq);

	/**
	 * 내 아이(유저) 기록조회
	 * @param user_seq
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	List<Height> selectHeightByUserSeqFT(String user_seq, String dateFrom, String dateTo);
}
