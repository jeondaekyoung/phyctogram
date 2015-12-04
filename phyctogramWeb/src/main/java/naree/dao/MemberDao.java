package naree.dao;

import naree.db.domain.Member;

public interface MemberDao {

	
	/**
	 * 페이스북 로그인시 가입한 멤버인지 확인
	 * @param facebook_id
	 * @return
	 */
	int selectMemberExistByFacebook_id(String facebook_id);

	/**
	 * 멤버 가입하기
	 * @param member
	 * @return
	 */
	int insertMember(Member member);

	/**
	 * 카카오 로그인시 가입한 멤버인지 확인
	 * @param kakao_id
	 * @return
	 */
	int selectMemberExistByKakao_id(String kakao_id);

	/**
	 * 픽토그램 로그인시 가입한 멤버인지 확인
	 * @param email
	 * @return
	 */
	int selectMemberExistByEmail(String email);

	/**
	 * 픽토그램 로그인 후 이용약관및개인정보동의
	 * @param member
	 * @return
	 */
	int insertJoinAgre(Member member);

	/**
	 * 이메일로 멤버찾기(이용약관및개인정보취급방침을 위해 멤버시퀀스가필요)
	 * @param email
	 * @return
	 */
	Member selectMemberByEmail(String email);

}
