package naree.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import naree.db.domain.Member;
import naree.db.mapper.MemberMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class MemberDaoImpl implements MemberDao {

	private static final Logger logger = LoggerFactory.getLogger(MemberDaoImpl.class);
	
	/**
	 * 페이스북 로그인시 가입한 멤버인지 확인
	 * @param facebook_id
	 * @return
	 */
	@Override
	public int selectMemberExistByFacebook_id(String facebook_id) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberExistByFacebook_id(facebook_id);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 멤버 가입하기
	 * @param member
	 * @return
	 */
	@Override
	public int insertMember(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.insertMember(member);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 카카오 로그인시 가입한 멤버인지 확인
	 * @param kakao_id
	 * @return
	 */
	@Override
	public int selectMemberExistByKakao_id(String kakao_id) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberExistByKakao_id(kakao_id);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 픽토그램 로그인시 가입한 멤버인지 확인
	 * @param email
	 * @return
	 */
	@Override
	public int selectMemberExistByEmail(String email) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberExistByEmail(email);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 픽토그램 로그인 후 이용약관및개인정보동의
	 * @param member
	 * @return
	 */
	@Override
	public int insertJoinAgre(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.insertJoinAgre(member);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 이메일로 멤버찾기(이용약관및개인정보취급방침을 위해 멤버시퀀스가필요)
	 * @param email
	 * @return
	 */
	@Override
	public Member selectMemberByEmail(String email) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Member result;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberByEmail(email);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 페이스북아이디로 멤버찾기
	 * @param member
	 * @return
	 */
	@Override
	public Member selectMemberByFacebookId(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Member result;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberByFacebookId(member);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 카카오아이디로 멤버찾기
	 * @param member
	 * @return
	 */
	@Override
	public Member selectMemberByKakaoId(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Member result;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberByKakaoId(member);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 픽토그램가입시 이메일로 멤버찾기
	 * @param member
	 * @return
	 */
	@Override
	public Member selectMemberByPhyctoEmail(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Member result;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberByPhyctoEmail(member);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * member_seq로 멤버찾기
	 * @param member_seq
	 * @return
	 */
	@Override
	public Member selectMemberByMemberSeq(int member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Member result;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberByMemberSeq(member_seq);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 페이스북 가입 정보로 멤버찾기
	 * @param memberResult
	 * @return
	 */
	@Override
	public Member selectMemberByFacebookInfo(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Member result;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberByFacebookInfo(member);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 픽토그램 멤버 로그인하기
	 * @param member
	 * @return
	 */
	@Override
	public Member selectMemberByPhycto(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Member result;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberByPhycto(member);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * member_seq와  pw로 멤버찾기
	 * @param member
	 * @return
	 */
	@Override
	public int selectMemberByMemberSeqPw(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.selectMemberByMemberSeqPw(member);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 멤버의 가입동의 지우기
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteJoinAgreByMemberSeq(int member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.deleteJoinAgreByMemberSeq(member_seq);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 멤버 지우기
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteMemberByMemberSeq(int member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.deleteMemberByMemberSeq(member_seq);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 비밀번호 변경
	 * @param member
	 * @return
	 */
	@Override
	public int updatePwByMember(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.updatePwByMember(member);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}
	
	/**
	 * 비밀번호 찾기
	 * @param mailAddr
	 * @return
	 */
	@Override
	public int findPwMember(String mailAddr) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.findPwMember(mailAddr);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Device Token 저장 확인
	 * @param member
	 * @return
	 */
	@Override
	public String findMemberByToken(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		String result = null;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.findMemberByToken(member);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Save Device Token
	 * @param member
	 * @return
	 */
	@Override
	public int registerToken(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.registerToken(member);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * Update Device Token
	 * @param member
	 * @return
	 */
	@Override
	public int updateToken(Member member) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
			result = memberMapper.updateToken(member);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}
}
