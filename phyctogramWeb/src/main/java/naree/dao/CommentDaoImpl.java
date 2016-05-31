package naree.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Comment;
import naree.db.mapper.CommentMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class CommentDaoImpl implements CommentDao {

	/**
	 * 커뮤니티(수다방) 글의 댓글 조회하기
	 * @param commnty_seq
	 * @return
	 */
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

	/**
	 * 커뮤니티(수다방) 글의 댓글 쓰기
	 * @param comment
	 * @return
	 */
	@Override
	public int insertComment(Comment comment) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			result = commentMapper.insertComment(comment);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 멤버가 쓴 수다방 댓글 지우기
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteCommentByMemberSeq(int member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			result = commentMapper.deleteCommentByMemberSeq(member_seq);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 수다방 글의 댓글 지우기
	 * @param commnty_seq
	 */
	@Override
	public int deleteCommentByCommntySeq(int commnty_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			result = commentMapper.deleteCommentByCommntySeq(commnty_seq);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}
}
