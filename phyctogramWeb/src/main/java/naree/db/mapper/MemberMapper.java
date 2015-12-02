package naree.db.mapper;

import naree.db.domain.Member;

public interface MemberMapper {

	int selectMemberByFacebook_id(String facebook_id);

	int insertMember(Member member);

	int selectMemberByKakao_id(String kakao_id);
}
