package naree.db.mapper;

import java.util.HashMap;
import java.util.List;

import naree.db.domain.QaWeb;

public interface QaWebMapper {

	//List<QaWeb> selectQaWebByMemberSeq(int member_seq, int pageCnt);

	/**
	 * 회원의 문의내용 읽어오기
	 * @param member_seq
	 * @param pageCnt
	 * @return
	 */
	List<QaWeb> selectQaWebByMemberSeq(HashMap<String, Object> map);

	/**
	 * 문의내용 저장하기
	 * @param QaWeb
	 * @return
	 */
	int insertQaWeb(QaWeb QaWeb);

	/**
	 * 문의사항 목록 읽어오기
	 * @param pageCnt, searchState
	 * @return
	 */
	List<QaWeb> listQaWeb(HashMap<String, Object> map);
	
	/**
	 * 문의사항 답변 저장하기
	 * @param QaWeb_seq, answer
	 * @return
	 */
	int modifyQaWeb(HashMap<String, Object> map);

	/**
	 * 문의사항 읽어오기
	 * @param QaWeb_seq
	 * @return
	 */
	QaWeb selectByQaWebSeq(int QaWeb_seq);
}
