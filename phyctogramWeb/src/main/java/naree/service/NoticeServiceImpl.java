package naree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.NoticeDao;
import naree.db.domain.Notice;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;
	
	/**
	 * 공지사항 저장
	 * @param notice
	 */
	@Override
	public void registerNotice(Notice notice) {
		
		noticeDao.registerNotice(notice);
	}

	/**
	 * 공지사항 목록 불러오기
	 * @param pageCnt
	 * @return
	 */
	@Override
	public List<Notice> listNotices(int pageCnt) {
		
		return noticeDao.listNotices(pageCnt);
	}

	/**
	 * 공지사항 읽어오기
	 * @param notice_seq
	 * @return
	 */
	@Override
	public Notice searchByNoticeSeq(int notice_seq) {
		
		return noticeDao.selectByNoticeSeq(notice_seq);
	}

	/**
	 * 공지사항 수정하기
	 * @param notice
	 * @return
	 */
	@Override
	public int modifyByNotice(Notice notice) {
		
		return noticeDao.updateByNotice(notice);
	}

	/**
	 * 공지사항 삭제하기
	 * @param notice_seq
	 * @return
	 */
	@Override
	public int eraseByNoticeSeq(int notice_seq) {
		
		return noticeDao.deleteByNoticeSeq(notice_seq);
	}

}
