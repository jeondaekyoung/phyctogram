package naree.db.mapper;

import java.util.List;
import java.util.Map;

import naree.db.domain.Height;

public interface HeightMapper {

	/**
	 * 키정보 저장하기
	 * @param insHeight
	 */
	int insertHeight(Height insHeight);

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
	int deleteHeightByUserSeq(String user_seq);

	/**
	 * 내 아이(유저) 기록조회
	 * @param user_seq
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	List<Height> selectHeightByUserSeqFT(Map<String, Object> terms);

	/**
	 * 최근 키시퀀스 찾기
	 * @return
	 */
	String selectHeightSeq();

	/**
	 * 키 삭제
	 * @param height_seq
	 * @return
	 */
	int deleteHeightByHeightSeq(String height_seq);

	/**
	 * (안드로이드에서) 키 저장하기
	 * @param height
	 * @return
	 */
	int insertHeightAnd(Height height);
	
	/**
	 * 최근 키 기록 가져오기(12개)
	 * @param user_seq
	 * @return
	 */
	List<Height> selectHeightForGraphByUserSeq(int user_seq);

	/**
	 * 상위가져오기(나이가 19세초과,개월로 228개월초과시, 19세를 기준으로 상위를가져온다)
	 * 키가 커서 데이터가 없을 경우 상위3%가져온다
	 * @param h
	 */
	String selectRankByHeight(Height h);
	
	int selectMaxRankByHeight(Height h);

	/**
	 * 평균키가져오기(아이 생일, 마지막킨잰날)
	 * @param h
	 * @return
	 */
	double selectAveHeightByHeight(Height h);

	/**
	 * 입력된 키가 있는지 확인
	 * @param user_seq
	 * @return
	 */
	int selectExistHeightByUserSeq(int user_seq);

	/**
	 * 최근신장 2개 불러오기
	 * @param user_seq
	 * @return
	 */
	List<Height> selectMax2HeightByUserSeq(int user_seq);

	/**
	 * 캐릭터가져오기(나이가 19세초과,개월로 228개월초과시, 19세를 기준으로 상위를가져온다)
	 * 키가 커서 데이터가 없을 경우 상위3%가져온다
	 * @param h
	 * @return
	 */
	String selectAnimalByHeight(Height h);

	String selectMaxAnimalByHeight(Height h);
}