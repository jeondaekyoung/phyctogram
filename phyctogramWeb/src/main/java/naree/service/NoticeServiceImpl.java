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
	
	@Override
	public void registerNotice(Notice notice) {
		
		noticeDao.registerNotice(notice);
	}

	@Override
	public List<Notice> listNotices(int pageCnt) {
		
		return noticeDao.listNotices(pageCnt);
	}

}
