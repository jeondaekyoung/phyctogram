package naree.db.mapper;

import java.util.HashMap;
import java.util.List;

import naree.db.domain.Qa;

public interface QaMapper {

	//List<Qa> selectqaByMemberSeq(int member_seq, int pageCnt);

	/**
	 * 회원의 문의내용 읽어오기
	 * @param member_seq
	 * @param pageCnt
	 * @return
	 */
	List<Qa> selectqaByMemberSeq(HashMap<String, Object> map);

	/**
	 * 문의내용 저장하기
	 * @param qa
	 * @return
	 */
	int insertQa(Qa qa);

	/**
	 * 문의사항 목록 읽어오기
	 * @param pageCnt, searchState
	 * @return
	 */
	List<Qa> listQa(HashMap<String, Object> map);
	
	/**
	 * 문의사항 답변 저장하기
	 * @param qa_seq, answer
	 * @return
	 */
	int modifyQa(HashMap<String, Object> map);

	/**
	 * 문의사항 읽어오기
	 * @param qa_seq
	 * @return
	 */
	Qa selectByQaSeq(int qa_seq);
	
	
	/**
	 * 문의사항 삭제
	 * @param member_seq
	 * @return
	 */
	int deleteByMember_seq(int member_seq);
}
