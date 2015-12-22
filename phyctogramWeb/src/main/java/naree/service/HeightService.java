package naree.service;

import java.util.List;

import naree.db.domain.Height;

public interface HeightService {

	/**
	 * 키 시퀀스 만들기
	 * @return
	 */
	String nextHeightSeq();
	
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

	/**
	 * 내 아이(유저) 기록조회
	 * @param user_seq
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	List<Height> findHeightByUserSeqFT(String user_seq, String dateFrom, String dateTo, int pageCnt);

	/**
	 * 키 삭제
	 * @param height_seq
	 * @return
	 */
	int delHeightByHeightSeq(String height_seq);

	/**
	 * (안드로이드에서) 키 저장하기
	 * @param height
	 * @return
	 */
	int registerHeightAnd(Height height);
	
	
}
