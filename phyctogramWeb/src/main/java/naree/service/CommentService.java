package naree.service;

import java.util.List;

import naree.db.domain.Comment;

public interface CommentService {

	/**
	 * 커뮤니티(수다방) 글의 댓글 조회하기
	 * @param commnty_seq
	 * @return
	 */
	List<Comment> findCommentByCommntySeq(int commnty_seq);

	/**
	 * 커뮤니티(수다방) 글의 댓글 쓰기
	 * @param comment
	 * @return
	 */
	int registerComment(Comment comment);

}
