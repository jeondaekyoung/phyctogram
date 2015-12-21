package naree.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Comment;
import naree.db.mapper.CommentMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Override
	public List<Comment> selectCommentByCommntySeq(int commnty_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Comment> result;
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			result = commentMapper.selectCommentByCommntySeq(commnty_seq);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	@Override
	public int insertComment(Comment comment) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;;
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			result = commentMapper.insertComment(comment);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

}
