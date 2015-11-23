package naree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.HeightDao;
import naree.db.domain.Height;

@Service
public class HeightServiceImpl implements HeightService {

	@Autowired
	private HeightDao heightDao;
	
	@Override
	public void registerHeight(Height insHeight) {
		heightDao.insertHeight(insHeight);
		
	}

	@Override
	public List<Height> selectHeightByUser_seq(String user_seq) {
		
		return heightDao.selectHeightByUser_seq(user_seq);
	}

}
