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

}
