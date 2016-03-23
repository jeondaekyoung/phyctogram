package naree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.PushDao;

@Service
public class PushServiceImpl implements PushService {

	@Autowired
	private PushDao pushDao;

	@Override
	public List<String> selectBoxList() {
		// TODO Auto-generated method stub
		return pushDao.selectBoxList();
	}
}
