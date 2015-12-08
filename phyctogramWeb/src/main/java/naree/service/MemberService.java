package naree.service;

import naree.db.domain.Member;

public interface MemberService {

	/**
	 * member 저장
	 * @param member
	 * @return 
	 */
	int registerMember(Member member);

	/**
	 * 가입정보를 통하야 가입된 정보를 불러온다(member_seq)
	 * @param member
	 * @return
	 */
	Member findMemberByJoinRoute(Member member);

	/**
	 * member_seq로 멤버찾기
	 * @param member_seq
	 * @return
	 */
	Member findMemberByMemberSeq(int member_seq);

	/**
	 * 페이스북 가입 정보로 멤버찾기
	 * @param memberResult
	 * @return
	 */
	Member findMemberByFacebookInfo(Member member);

	/**
	 * 픽토그램 멤버 로그인하기
	 * @param member
	 * @return
	 */
	Member loginMemberByPhycto(Member member);
	
	
}
