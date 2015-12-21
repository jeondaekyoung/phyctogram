package naree.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import naree.db.domain.Comment;
import naree.service.CommentService;

@RestController
@RequestMapping(value = "rest/comment")
public class CommentRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentRestController.class);
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 커뮤니티(수다방) 글의 댓글 조회하기
	 * @param commnty_seq
	 * @return
	 */
	@RequestMapping(value = "findCommentByCommntySeq", method = RequestMethod.GET)
	public List<Comment> findCommentByCommntySeq(@RequestParam("commnty_seq") int commnty_seq){
		logger.info("findCommentByCommntySeq 실행 : " + commnty_seq);
		
		return commentService.findCommentByCommntySeq(commnty_seq);
	}
	
	/**
	 * 커뮤니티(수다방) 글의 댓글 쓰기
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "registerComment", method = RequestMethod.POST)
	public String registerComment(@RequestBody Comment comment){
		logger.info("registerComment 실행 : " + comment.toString());
		
		int result = commentService.registerComment(comment);
		if(result == 1){
			return "success";
		} else {
			return "fail";
		}
	}
	
	
	

}
