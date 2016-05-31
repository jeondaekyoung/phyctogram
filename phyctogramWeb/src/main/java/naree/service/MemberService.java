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

	/**
	 * member_seq와 pw로 멤버 찾기
	 * @param member
	 * @return
	 */
	int findMemberByMemberSeqPw(Member member);

	/**
	 * 멤버가 쓴 댓글, 수다방 지우기
	 * @param member_seq
	 * @return
	 */
	int deleteCommntyCommentByMemberSeq(int member_seq);

	/**
	 * 멤버의 내 아이 지우기(키, 일기 포함)
	 * @param member_seq
	 * @return
	 */
	int deleteUsersHeightDiaryByMemberSeq(int member_seq);

	/**
	 * 멤버의 가입동의 지우기
	 * @param member_seq
	 * @return
	 */
	int deleteJoinAgreByMemberSeq(int member_seq);

	/**
	 * 멤버 지우기
	 * @param member_seq
	 * @return
	 */
	int deleteMemberByMemberSeq(int member_seq);

	/**
	 * 비빌번호 변경
	 * @param member
	 * @return
	 */
	int modifyPwByMember(Member member);
	
	/**
	 * 비빌번호 찾기
	 * @param mailAddr
	 * @return
	 */
	int findPwMember(String mailAddr);
	
	/**
	 * Device Token 저장 확인
	 * @param member
	 * @return
	 */
	String findMemberByToken(Member member);
	
	/**
	 * Save Device Token
	 * @param member
	 * @return
	 */
	int registerToken(Member member);
	
	/**
	 * Update Device Token
	 * @param member
	 * @return
	 */
	int updateToken(Member member);
}
