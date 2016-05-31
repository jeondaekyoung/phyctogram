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
	
	/**
	 * 커뮤니티(수다방) 글 저장
	 * @param commnty
	 * @return
	 */
	@Override
	public int registerCommnty(Commnty commnty) {
		
		return commntyDao.insertCommnty(commnty);
	}

	/**
	 * 커뮤니티(수다방) 최신 글 목록 읽어오기
	 * @return
	 */
	@Override
	public List<SqlCommntyListView> findCommntyLatest(int pageCnt) {
		//목록 10개 가져오기위한 첫 번째 인덱스를 넘겨준다.
		return commntyDao.selectCommntyLatest(pageCnt*10);
	}

	/**
	 * 커뮤니티(수다방) 인기 글 목록 읽어오기
	 * @return
	 */
	@Override
	public List<SqlCommntyListView> findCommntyPopular(int pageCnt) {
		
		return commntyDao.selectCommntyPopular(pageCnt*10);
	}

	/**
	 * 커뮤니티(수다방) 글 조회하기
	 * @param commnty_seq
	 * @return
	 */
	@Override
	public Commnty findCommntyByCommntySeq(int commnty_seq) {
		
		return commntyDao.selectCommntyByCommntySeq(commnty_seq);
	}

	/**
	 * 커뮤니티(수다방) 조회 카운트하기
	 * @param commnty
	 * @return
	 */
	@Override
	public int increaseHitsCoByCommnty(Commnty commnty) {
		
		return commntyDao.updateHitsCoByCommnty(commnty);
	}

	/**
	 * 커뮤니티(수다방) 인기 Top3 목록 읽어오기
	 * @return
	 */
	@Override
	public List<SqlCommntyListView> findCommntyPopularTop3() {
		
		return commntyDao.selectCommntyPopularTop3();
	}
	
}
