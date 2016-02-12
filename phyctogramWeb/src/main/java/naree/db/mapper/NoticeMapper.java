package naree.db.mapper;

import java.util.List;

import naree.db.domain.Notice;

public interface NoticeMapper {

	void registerNotice(Notice notice);

	List<Notice> listNotices(int pageCnt);

	Notice selectByNoticeSeq(int notice_seq);

	int updateByNotice(Notice notice);

	int deleteByNoticeSeq(int notice_seq);

}
