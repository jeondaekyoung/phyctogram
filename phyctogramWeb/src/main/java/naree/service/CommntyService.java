package naree.service;

import java.util.List;

import naree.db.domain.Commnty;
import naree.db.domain.SqlCommntyListView;

public interface CommntyService {

	/**
	 * 커뮤니티(수다방) 글 저장
	 * @param commnty
	 * @return
	 */
	int registerCommnty(Commnty commnty);

	/**
	 * 커뮤니티(수다방) 최신 글 목록 읽어오기
	 * @return
	 */
	List<SqlCommntyListView> findCommntyLatest(int pageCnt);

	/**
	 * 커뮤니티(수다방) 인기 글 목록 읽어오기
	 * @return
	 */
	List<SqlCommntyListView> findCommntyPopular(int pageCnt);

	/**
	 * 커뮤니티(수다방) 글 조회하기
	 * @param commnty_seq
	 * @return
	 */
	Commnty findCommntyByCommntySeq(int commnty_seq);

	/**
	 * 커뮤니티(수다방) 조회 카운트하기
	 * @param commnty
	 * @return
	 */
	int increaseHitsCoByCommnty(Commnty commnty);

	/**
	 * 커뮤니티(수다방) 인기 Top3 목록 읽어오기
	 * @return
	 */
	List<SqlCommntyListView> findCommntyPopularTop3();

}
