package naree.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Admin;
import naree.db.domain.Notice;
import naree.db.domain.Qa;
import naree.db.mapper.AdminMapper;
import naree.db.mapper.NoticeMapper;
import naree.db.mapper.QaMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class AdminDaoImpl implements AdminDao {

	/**
	 * 관리자 아이디 확인
	 * @param id
	 * @return
	 */
	@Override
	public Admin searchAdminById(String id) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		//Admin domain 객체 선언 
		Admin admin = null;
		try {
			//DB 연결 객체
			AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
			//DB 연결 객체의 searchAdminById 함수 실행
			admin = adminMapper.searchAdminById(id);
		} finally {
			//끝난 후 DB 연결 객체 닫음
			sqlSession.close();
		}
		return admin;
	}

	/**
	 *  관리자 비번 확인
	 * @param admin
	 * @return
	 */
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

	/**
	 *  구매내역 저장
	 * @param admin
	 * @return
	 */
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

	/**
	 *  구매내역 조회
	 * @param admin
	 * @return
	 */
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
