package naree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.QaDao;
import naree.db.domain.Qa;

@Service
public class QaServiceImpl implements QaService {

	@Autowired
	private QaDao qaDao;
	
	/**
	 * 회원의 문의내용 읽어오기
	 * @param member_seq
	 * @param pageCnt
	 * @return
	 */
	@Override
	public List<Qa> findqaByMemberSeq(int member_seq, int pageCnt) {
		
		return qaDao.selectqaByMemberSeq(member_seq, pageCnt);
	}

	/**
	 * 문의내용 저장하기
	 * @param qa
	 * @return
	 */
	@Override
	public int registerQa(Qa qa) {
		
		return qaDao.insertQa(qa);
	}

	/**
	 * 문의사항 목록 불러오기
	 * @param pageCnt
	 * @return
	 */
	@Override
	public List<Qa> listQa(int pageCnt, String searchState) {
		
		return qaDao.listQa(pageCnt, searchState);
	}

	/**
	 * 문의사항 답변 저장하기
	 * @param qa_seq, answer
	 * @return
	 */
	@Override
	public int modifyQa(int qa_seq, String answer) {
		
		return qaDao.modifyQa(qa_seq, answer);
	}

	/**
	 * 문의사항 읽어오기
	 * @param qa_seq
	 * @return
	 */
	@Override
	public Qa searchByQaSeq(int qa_seq) {
		
		return qaDao.selectByQaSeq(qa_seq);
	}
}
