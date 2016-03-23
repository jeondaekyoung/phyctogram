package naree.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Admin;
import naree.db.domain.Qa;
import naree.db.mapper.AdminMapper;
import naree.db.mapper.QaMapper;
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

	@Override
	public int registerBuy(String price, String etc) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		//insert parsing price Logic 
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("price", price);
		map.put("etc", etc);
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			result = adminMapper.registerBuy(map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result; 
	}

	@Override
	public int totalPrice() {
		// TODO Auto-generated method stub
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try {
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			result = adminMapper.totalPrice();
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}
}
