package naree.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Admin;
import naree.db.mapper.AdminMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Override
	public Admin searchAdminById(String id) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Admin admin = null;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			admin = adminMapper.searchAdminById(id);
		} finally {
			sqlSession.close();
		}
		return admin;
	}

	@Override
	public boolean searchAdminByAdmin(Admin admin) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			result = adminMapper.searchAdminByAdmin(admin);
		} finally {
			sqlSession.close();
		}
		if(result == 1){
			return true;
		} else {
			return false;
		}
	}

}
