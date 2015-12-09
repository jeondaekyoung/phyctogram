package naree.db.mapper;

import java.util.List;

import naree.db.domain.Users;

public interface UsersMapper {

	int insertUsers(Users users);

	List<Users> selectUsersByMemberSeq(String member_seq);

	int delectUsersByUserSeq(String user_seq);

	int updateUsersByUsers(Users users);

}
