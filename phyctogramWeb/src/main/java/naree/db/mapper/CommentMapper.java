package naree.db.mapper;

import java.util.List;

import naree.db.domain.Comment;

public interface CommentMapper {

	/**
	 * 커뮤니티(수다방) 글의 댓글 조회하기
	 * @param commnty_seq
	 * @return
	 */
	List<Comment> selectCommentByCommntySeq(int commnty_seq);

	/**
	 * 커뮤니티(수다방) 글의 댓글 쓰기
	 * @param comment
	 * @return
	 */
	int insertComment(Comment comment);

	/**
	 * 멤버가 쓴 수다방 댓글 지우기
	 * @param member_seq
	 * @return
	 */
	int deleteCommentByMemberSeq(int member_seq);

	/**
	 * 수다방 글의 댓글 지우기
	 * @param commnty_seq
	 */
	int deleteCommentByCommntySeq(int commnty_seq);
}
