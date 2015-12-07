package naree.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.UsersDao;
import naree.db.domain.Users;

@Service
public class UsersServiceImpl implements UsersService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private UsersDao usersDao;

	@Override
	public int registerUsers(Users users) {
		
		return usersDao.insertUsers(users);
	}

}
