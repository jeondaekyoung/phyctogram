package naree.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Users;
import naree.db.mapper.UsersMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class UsersDaoImpl implements UsersDao {

	@Override
	public int insertUsers(Users users) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
			result = usersMapper.insertUsers(users);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	@Override
	public List<Users> selectUsersByMemberSeq(String member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Users> result;
		try{
			UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
			result = usersMapper.selectUsersByMemberSeq(member_seq);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	@Override
	public int delectUsersByUserSeq(String user_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
			result = usersMapper.delectUsersByUserSeq(user_seq);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

}
