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
}
