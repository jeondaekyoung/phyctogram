package naree.service;

import java.util.List;

import naree.db.domain.Notice;

public interface NoticeService {

	/**
	 * 공지사항 저장
	 * @param notice
	 */
	void registerNotice(Notice notice);

	/**
	 * 공지사항 목록 불러오기
	 * @param pageCnt
	 * @return
	 */
	List<Notice> listNotices(int pageCnt);

}
