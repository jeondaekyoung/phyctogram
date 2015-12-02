package naree.dao;

import naree.db.domain.Member;

public interface MemberDao {

	
	/**
	 * 페이스북 로그인시 가입한 멤버인지 확인
	 * @param facebook_id
	 * @return
	 */
	int selectMemberByFacebook_id(String facebook_id);

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
	int selectMemberByKakao_id(String kakao_id);

}
