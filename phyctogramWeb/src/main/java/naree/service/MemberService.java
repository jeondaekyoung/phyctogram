package naree.service;

import naree.db.domain.Member;

public interface MemberService {

	/**
	 * member 저장
	 * @param member
	 * @return 
	 */
	int registerMember(Member member);
	
	
}
