package naree.service;

import java.util.List;

import naree.db.domain.Analysis;
import naree.db.domain.Users;

public interface UsersService {

	/**
	 * 유저(내 아이) 등록하기
	 * @param users
	 * @return
	 */
	int registerUsers(Users users);

	/**
	 * 멤버로 유저(내 아이) 찾기
	 * @param member_seq
	 * @return
	 */
	List<Users> findUsersByMemberSeq(String member_seq);

	/**
	 * 내 아이(유저) 삭제(키도 삭제)
	 * @param user_seq
	 * @return 
	 */
	int delUsersByUserSeq(String user_seq);

	/**
	 * 내 아이(유저) 수정
	 * @param users
	 * @return
	 */
	int modifyUsersByUsers(Users users);

	/**
	 * 아이의 개월수와 캐릭터 계산하기
	 * @param users
	 * @return
	 */
	Analysis findMonthNumAnimalByUserSeq(int user_seq);

}
