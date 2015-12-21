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

	@Override
	public List<Comment> findCommentByCommntySeq(int commnty_seq) {
		
		return commentDao.selectCommentByCommntySeq(commnty_seq);
	}

	@Override
	public int registerComment(Comment comment) {
		
		return commentDao.insertComment(comment);
	}

}
