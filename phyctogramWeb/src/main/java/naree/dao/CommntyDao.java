package naree.dao;

import java.util.List;

import naree.db.domain.Commnty;
import naree.db.domain.SqlCommntyListView;

public interface CommntyDao {

	/**
	 * 커뮤니티(수다방) 글 저장
	 * @param commnty
	 * @return
	 */
	int insertCommnty(Commnty commnty);

	/**
	 * 커뮤니티(수다방) 최신 글 목록 읽어오기
	 * @return
	 */
	List<SqlCommntyListView> selectCommntyLatest(int pageCntFirstIndex);

	/**
	 * 커뮤니티(수다방) 인기 글 목록 읽어오기
	 * @return
	 */
	List<SqlCommntyListView> selectCommntyPopular(int pageCntFirstIndex);

	/**
	 * 커뮤니티(수다방) 글 조회하기
	 * @param commnty_seq
	 * @return
	 */
	Commnty selectCommntyByCommntySeq(int commnty_seq);

	/**
	 * 커뮤니티(수다방) 조회 카운트하기
	 * @param commnty
	 * @return
	 */
	int updateHitsCoByCommnty(Commnty commnty);

	/**
	 * 커뮤니티(수다방) 인기 Top3 목록 읽어오기
	 * @return
	 */
	List<SqlCommntyListView> selectCommntyPopularTop3();

	/**
	 * 멤버가 쓴 수다방글 읽어오기
	 * @param member_seq
	 * @return
	 */
	List<Commnty> selectCommntyByMemberSeq(int member_seq);

	/**
	 * 멤버가 쓴 수다방글 지우기
	 * @param member_seq
	 * @return
	 */
	int deleteCommntyByMemberSeq(int member_seq);



}
