package naree.dao;

import java.util.List;

import naree.db.domain.Notice;

public interface NoticeDao {

	/**
	 * 공지사항 저장
	 * @param notice
	 */
	void registerNotice(Notice notice);

	/**
	 * 공지사항 목록 읽어오기
	 * @param pageCnt
	 * @return
	 */
	List<Notice> listNotices(int pageCnt);

	/**
	 * 공지사항 읽어오기
	 * @param notice_seq
	 * @return
	 */
	Notice selectByNoticeSeq(int notice_seq);

	/**
	 * 공지사항 수정하기
	 * @param notice
	 * @return
	 */
	int updateByNotice(Notice notice);

	/**
	 * 공지사항 삭제하기
	 * @param notice_seq
	 * @return
	 */
	int deleteByNoticeSeq(int notice_seq);

}
