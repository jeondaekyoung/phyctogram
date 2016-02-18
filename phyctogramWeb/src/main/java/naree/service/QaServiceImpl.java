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
	
	@Override
	public List<Qa> findqaByMemberSeq(int member_seq, int pageCnt) {
		
		return qaDao.selectqaByMemberSeq(member_seq, pageCnt);
	}

	@Override
	public int registerQa(Qa qa) {
		
		return qaDao.insertQa(qa);
	}

	@Override
	public List<Qa> listQa(int pageCnt, String searchState) {
		// TODO Auto-generated method stub
		return qaDao.listQa(pageCnt, searchState);
	}

	@Override
	public int modifyQa(int qa_seq, String answer) {
		// TODO Auto-generated method stub
		return qaDao.modifyQa(qa_seq, answer);
	}
}
