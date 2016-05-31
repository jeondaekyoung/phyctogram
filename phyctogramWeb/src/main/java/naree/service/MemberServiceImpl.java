package naree.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.CommentDao;
import naree.dao.CommntyDao;
import naree.dao.DiaryDao;
import naree.dao.HeightDao;
import naree.dao.MemberDao;
import naree.dao.UsersDao;
import naree.db.domain.Commnty;
import naree.db.domain.Member;
import naree.db.domain.Users;

@Service
public class MemberServiceImpl implements MemberService{

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private CommntyDao commntyDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private HeightDao heightDao;
	
	@Autowired
	private DiaryDao diaryDao;
	
	/**
	 * member 저장
	 * @param member
	 * @return 
	 */
	@Override
	public int registerMember(Member member) {
		int result = 100;
		
		if(member.getJoin_route().equals("facebook")){
			//페이스북으로 현재 가입된 멤버인지 확인한다.
			result = memberDao.selectMemberExistByFacebook_id(member.getFacebook_id());
			logger.info("페이스북 가입확인 : " + result);
			if(result == 0){
				result = memberDao.insertMember(member);
			}
			return result;
		} else if(member.getJoin_route().equals("kakao")){
			result = memberDao.selectMemberExistByKakao_id(member.getKakao_id());
			logger.info("카카오 가입확인 : " + result);
			if(result == 0){
				result = memberDao.insertMember(member);
			}
			return result;
		} else {
			result = memberDao.selectMemberExistByEmail(member.getEmail());
			logger.info("픽토그램 가입확인 : " + result);
			if(result == 0){
				//가입
				result = memberDao.insertMember(member);
				Member phyctoMember = memberDao.selectMemberByEmail(member.getEmail());
				//가입동의 저장
				int join_agre = memberDao.insertJoinAgre(phyctoMember);
				logger.info("픽토그램 가입동의 입력 : " + join_agre);
			} else if(result == 1){
				logger.info("이미 픽토그램에 가입된 이메일입니다");
				return 5;
			}
			return result;
		}
		
	}

	/**
	 * 가입정보를 통하야 가입된 정보를 불러온다(member_seq)
	 * @param member
	 * @return
	 */
	@Override
	public Member findMemberByJoinRoute(Member member) {
		Member m = new Member();
		if(member.getJoin_route().equals("facebook")){
			m = memberDao.selectMemberByFacebookId(member);
		} else if(member.getJoin_route().equals("kakao")){
			m = memberDao.selectMemberByKakaoId(member);
		} else {
			m = memberDao.selectMemberByPhyctoEmail(member);
		}
		return m;
	}

	/**
	 * member_seq로 멤버찾기
	 * @param member_seq
	 * @return
	 */
	@Override
	public Member findMemberByMemberSeq(int member_seq) {
		
		return memberDao.selectMemberByMemberSeq(member_seq);
	}

	/**
	 * 페이스북 가입 정보로 멤버찾기
	 * @param memberResult
	 * @return
	 */
	@Override
	public Member findMemberByFacebookInfo(Member member) {
		
		return memberDao.selectMemberByFacebookInfo(member);
	}

	/**
	 * 픽토그램 멤버 로그인하기
	 * @param member
	 * @return
	 */
	@Override
	public Member loginMemberByPhycto(Member member) {
		
		return memberDao.selectMemberByPhycto(member);
	}

	/**
	 * member_seq와 pw로 멤버 찾기
	 * @param member
	 * @return
	 */
	@Override
	public int findMemberByMemberSeqPw(Member member) {
		
		return memberDao.selectMemberByMemberSeqPw(member);
	}

	/**
	 * 멤버가 쓴 댓글, 수다방 지우기
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteCommntyCommentByMemberSeq(int member_seq) {
		//멤버가 쓴 댓글 지우기
		logger.info("멤버가 쓴 댓글 지우기");
		int result = commentDao.deleteCommentByMemberSeq(member_seq);
		//멤버가 쓴 수다방 목록 읽어오기
		List<Commnty> commntys = commntyDao.selectCommntyByMemberSeq(member_seq);
		logger.info("멤버가 쓴 수다방 목록의 댓글지우기");
		for(Commnty c : commntys) {
			//멤버가 쓴 수다방 목록의 댓글지우기
			commentDao.deleteCommentByCommntySeq(c.getCommnty_seq());
		}
		//멤버가 쓴 수다방 목록 지우기
		logger.info("멤버가 쓴 수다방 목록 지우기");
		int result1 = commntyDao.deleteCommntyByMemberSeq(member_seq);
		
		return result1;
	}

	/**
	 * 멤버의 내 아이 지우기(키, 일기 포함)
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteUsersHeightDiaryByMemberSeq(int member_seq) {
		//멤버의 내 아이 목록 가져오기
		List<Users> userss = usersDao.selectUsersByMemberSeq(String.valueOf(member_seq));
		logger.info("멤버의 내 아이 키, 일기 지우기");
		for(Users u : userss) {
			//내 아이 키 지우기
			heightDao.deleteHeightByUserSeq(String.valueOf(u.getUser_seq()));
			//내 아이 일기 지우기
			diaryDao.deleteDiaryByUserSeq(u.getUser_seq());
		}
		//멤버의 내 아이 지우기
		logger.info("멤버의 내 아이 지우기");
		int result = usersDao.deleteUsersByMemberSeq(member_seq);
		return result;
	}

	/**
	 * 멤버의 가입동의 지우기
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteJoinAgreByMemberSeq(int member_seq) {
		//멤버의 가입동의 지우기
		logger.info("멤버의 가입동의 지우기");
		return memberDao.deleteJoinAgreByMemberSeq(member_seq);
	}

	/**
	 * 멤버 지우기
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteMemberByMemberSeq(int member_seq) {
		//멤버 지우기
		logger.info("멤버 지우기");
		return memberDao.deleteMemberByMemberSeq(member_seq);
	}

	/**
	 * 비빌번호 변경
	 * @param member
	 * @return
	 */
	@Override
	public int modifyPwByMember(Member member) {
		
		return memberDao.updatePwByMember(member);
	}

	/**
	 * 비빌번호 찾기
	 * @param mailAddr
	 * @return
	 */
	@Override
	public int findPwMember(String mailAddr) {
		// TODO Auto-generated method stub
		return memberDao.findPwMember(mailAddr);
	}

	/**
	 * Device Token 저장 확인
	 * @param member
	 * @return
	 */
	@Override
	public String findMemberByToken(Member member) {
		// TODO Auto-generated method stub
		return memberDao.findMemberByToken(member);
	}

	/**
	 * Save Device Token
	 * @param member
	 * @return
	 */
	@Override
	public int registerToken(Member member) {
		return memberDao.registerToken(member);
	}

	/**
	 * Update Device Token
	 * @param member
	 * @return
	 */
	@Override
	public int updateToken(Member member) {
		// TODO Auto-generated method stub
		return  memberDao.updateToken(member);
	}
}
