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

}
