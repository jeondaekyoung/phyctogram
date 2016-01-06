package naree.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.DiaryDao;
import naree.dao.HeightDao;
import naree.dao.UsersDao;
import naree.db.domain.Users;

@Service
public class UsersServiceImpl implements UsersService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private HeightDao heightDao;
	
	@Autowired
	private DiaryDao diaryDao;

	@Override
	public int registerUsers(Users users) {
		
		return usersDao.insertUsers(users);
	}

	@Override
	public List<Users> findUsersByMemberSeq(String member_seq) {
		
		return usersDao.selectUsersByMemberSeq(member_seq);
	}

	@Override
	public int delUsersByUserSeq(String user_seq) {
		logger.info(user_seq);
		
		//키 삭제
		heightDao.deleteHeightByUserSeq(user_seq);
		//일기 삭제
		diaryDao.deleteDiaryByUserSeq(Integer.valueOf(user_seq));
		
		return usersDao.deleteUsersByUserSeq(user_seq);
	}

	@Override
	public int modifyUsersByUsers(Users users) {
		
		return usersDao.updateUsersByUsers(users);
	}



}
