package naree.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.CommntyDao;
import naree.db.domain.Commnty;
import naree.db.domain.SqlCommntyListView;

@Service
public class CommntyServiceImpl implements CommntyService {

	private static final Logger logger = LoggerFactory.getLogger(CommntyServiceImpl.class);

	@Autowired
	private CommntyDao commntyDao;
	
	@Override
	public int registerCommnty(Commnty commnty) {
		
		return commntyDao.insertCommnty(commnty);
	}

	@Override
	public List<SqlCommntyListView> findCommntyLatest(int pageCnt) {
		//목록 10개 가져오기위한 첫 번째 인덱스를 넘겨준다.
		return commntyDao.selectCommntyLatest(pageCnt*10);
	}

	@Override
	public List<SqlCommntyListView> findCommntyPopular(int pageCnt) {
		
		return commntyDao.selectCommntyPopular(pageCnt*10);
	}

	@Override
	public Commnty findCommntyByCommntySeq(int commnty_seq) {
		
		return commntyDao.selectCommntyByCommntySeq(commnty_seq);
	}

	@Override
	public int increaseHitsCoByCommnty(Commnty commnty) {
		
		return commntyDao.updateHitsCoByCommnty(commnty);
	}
	
}
