package naree.dao;

import java.util.List;

import naree.db.domain.QaWeb;

public interface QaWebDao {

	/**
	 * 회원의 문의내용 읽어오기
	 * @param member_seq
	 * @param pageCnt
	 * @return
	 */
	List<QaWeb> selectQaWebByMemberSeq(int member_seq, int pageCnt);

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
	List<QaWeb> listQaWeb(int pageCnt, String searchState);
	
	/**
	 * 문의사항 답변 저장하기
	 * @param qa_Web_seq, answer
	 * @return
	 */
	int modifyQaWeb(int qa_Web_seq, String answer);

	/**
	 * 문의사항 읽어오기
	 * @param qa_Web_seq
	 * @return
	 */
	QaWeb selectByQaWebSeq(int qa_Web_seq);
	
	/**
	 * 문의사항 답변상태 변경
	 * @param qa_Web_seq
	 * @return
	 */
	int updateStateQaWeb(int qa_Web_seq);

	
	/**
	 * 문의사항 삭제
	 * @param qa_Web_seq
	 * @return
	 */
	int eraseByqa_web_Seq(int qa_Web_seq);
}
