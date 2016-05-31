package naree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.CommentDao;
import naree.db.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;

	/**
	 * 커뮤니티(수다방) 글의 댓글 조회하기
	 * @param commnty_seq
	 * @return
	 */
	@Override
	public List<Comment> findCommentByCommntySeq(int commnty_seq) {
		
		return commentDao.selectCommentByCommntySeq(commnty_seq);
	}

	/**
	 * 커뮤니티(수다방) 글의 댓글 쓰기
	 * @param comment
	 * @return
	 */
	@Override
	public int registerComment(Comment comment) {
		
		return commentDao.insertComment(comment);
	}
}
