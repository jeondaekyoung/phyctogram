package naree.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import naree.db.domain.Analysis;
import naree.db.domain.Users;
import naree.db.mapper.UsersMapper;
import naree.util.factory.ConnectionFactory;

@Repository
public class UsersDaoImpl implements UsersDao {

	/**
	 * 유저(내 아이) 등록
	 * @param users
	 * @return
	 */
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

	/**
	 * 멤버로 유저(내 아이) 찾기
	 * @param member_seq
	 * @return
	 */
	@Override
	public List<Users> selectUsersByMemberSeq(String member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		List<Users> result = new ArrayList<Users>();
		try{
			UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
			result = usersMapper.selectUsersByMemberSeq(member_seq);
		}finally{
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 내 아이(유저) 삭제
	 * @param user_seq
	 * @return
	 */
	@Override
	public int deleteUsersByUserSeq(String user_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
			result = usersMapper.deleteUsersByUserSeq(user_seq);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 내 아이(유저) 수정
	 * @param users
	 * @return
	 */
	@Override
	public int updateUsersByUsers(Users users) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
			result = usersMapper.updateUsersByUsers(users);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 멤버의 내 아이(유저) 삭제
	 * @param member_seq
	 * @return
	 */
	@Override
	public int deleteUsersByMemberSeq(int member_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		int result = 0;
		try{
			UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
			result = usersMapper.deleteUsersByMemberSeq(member_seq);
		}finally{
			sqlSession.commit();
			sqlSession.close();
		}
		return result;
	}

	/**
	 * 개월 및 캐릭터이미지파일 계산하기 
	 * @param user_seq
	 * @return
	 */
	@Override
	public Analysis selectMonthNumAnimalByUserSeq(int user_seq) {
		SqlSession sqlSession = ConnectionFactory.getInstance().getSqlSession();
		Analysis result;
		try{
			UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
			result = usersMapper.selectMonthNumAnimalByUserSeq(user_seq);
			//키가 커서 데이터가 없을 경우 상위3%가져온다
			if(result == null){
				result = usersMapper.selectMaxMonthNumAnimalByUserSeq(user_seq);
			}		
		} finally{
			sqlSession.close();
		}
		return result;
	}
}
