package naree.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Users;
import naree.db.mapper.UsersMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class UserDaoImpl implements UsersDao {

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

}
