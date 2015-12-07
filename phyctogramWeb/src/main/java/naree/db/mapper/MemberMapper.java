package naree.db.mapper;

import naree.db.domain.Member;

public interface MemberMapper {

	int selectMemberExistByFacebook_id(String facebook_id);

	int insertMember(Member member);

	int selectMemberExistByKakao_id(String kakao_id);

	int selectMemberExistByEmail(String email);

	int insertJoinAgre(Member member);

	Member selectMemberByEmail(String email);

	Member selectMemberByFacebookId(Member member);

	Member selectMemberByKakaoId(Member member);

	Member selectMemberByPhyctoEmail(Member member);

	Member selectMemberByMemberSeq(int member_seq);

	Member selectMemberByFacebookInfo(Member memberResult);
}


