package naree.service;

import java.util.List;

import naree.db.domain.QaWeb;

public interface QaWebService {

	/**
	 * 회원의 문의내용 읽어오기
	 * @param member_seq
	 * @param pageCnt
	 * @return
	 */
	List<QaWeb> findQaWebByMemberSeq(int member_seq, int pageCnt);

	/**
	 * 문의내용 저장하기
	 * @param QaWeb
	 * @return
	 */
	int registerQaWeb(QaWeb QaWeb);
	
	/**
	 * 문의사항 목록 불러오기
	 * @param pageCnt
	 * @return
	 */
	List<QaWeb> listQaWeb(int pageCnt, String searchState);
	
	/**
	 * 문의사항 답변 저장하기
	 * @param QaWeb_seq, answer
	 * @return
	 */
	int modifyQaWeb(int qa_Web_seq, String answer);

	/**
	 * 문의사항 읽어오기
	 * @param QaWeb_seq
	 * @return
	 */
	QaWeb searchByQaWebSeq(int qa_Web_seq);
	
	
	/**
	 * 문의사항 답변상태 변경
	 * @param QaWeb_seq
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
