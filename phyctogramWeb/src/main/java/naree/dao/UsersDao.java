package naree.dao;

import java.util.List;

import naree.db.domain.Users;

public interface UsersDao {

	/**
	 * 유저(내 아이) 등록
	 * @param users
	 * @return
	 */
	int insertUsers(Users users);

	/**
	 * 멤버로 유저(내 아이) 찾기
	 * @param member_seq
	 * @return
	 */
	List<Users> selectUsersByMemberSeq(String member_seq);

	/**
	 * 내 아이(유저) 삭제
	 * @param user_seq
	 * @return
	 */
	int delectUsersByUserSeq(String user_seq);

	/**
	 * 내 아이(유저) 수정
	 * @param users
	 * @return
	 */
	int updateUsersByUsers(Users users);

}
