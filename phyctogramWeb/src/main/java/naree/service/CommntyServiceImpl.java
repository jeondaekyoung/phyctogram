package naree.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.CommntyDao;
import naree.db.domain.Commnty;

@Service
public class CommntyServiceImpl implements CommntyService {

	private static final Logger logger = LoggerFactory.getLogger(CommntyServiceImpl.class);

	@Autowired
	private CommntyDao commntyDao;
	
	@Override
	public int registerCommnty(Commnty commnty) {
		
		return commntyDao.insertCommnty(commnty);
	}
	
}
