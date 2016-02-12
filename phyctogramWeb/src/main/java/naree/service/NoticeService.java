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

	/**
	 * 공지사항 읽어오기
	 * @param notice_seq
	 * @return
	 */
	Notice searchByNoticeSeq(int notice_seq);

	/**
	 * 공지사항 수정하기
	 * @param notice
	 * @return
	 */
	int modifyByNotice(Notice notice);

	/**
	 * 공지사항 삭제하기
	 * @param notice_seq
	 * @return
	 */
	int eraseByNoticeSeq(int notice_seq);

}
