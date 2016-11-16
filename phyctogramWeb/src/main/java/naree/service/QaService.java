package naree.service;

import java.util.List;

import naree.db.domain.Qa;

public interface QaService {

	/**
	 * 회원의 문의내용 읽어오기
	 * @param member_seq
	 * @param pageCnt
	 * @return
	 */
	List<Qa> findqaByMemberSeq(int member_seq, int pageCnt);

	/**
	 * 문의내용 저장하기
	 * @param qa
	 * @return
	 */
	int registerQa(Qa qa);
	
	/**
	 * 문의사항 목록 불러오기
	 * @param pageCnt
	 * @return
	 */
	List<Qa> listQa(int pageCnt, String searchState);
	
	/**
	 * 문의사항 답변 저장하기
	 * @param qa_seq, answer
	 * @return
	 */
	int modifyQa(int qa_seq, String answer);

	/**
	 * 문의사항 읽어오기
	 * @param qa_seq
	 * @return
	 */
	Qa searchByQaSeq(int qa_seq);

	/**
	 * 문의사항 삭제
	 * @param member_seq
	 * @return
	 */
	int deleteByMember_seq(int member_seq);
	
}
