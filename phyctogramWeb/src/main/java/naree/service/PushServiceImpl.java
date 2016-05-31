package naree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.PushDao;
import naree.db.domain.Member;

@Service
public class PushServiceImpl implements PushService {

	@Autowired
	private PushDao pushDao;

	/**
	 * selectBox 목록 불러오기
	 * @param pageCnt
	 * @return
	 */
	@Override
	public List<Member> selectBoxList() {
		// TODO Auto-generated method stub
		return pushDao.selectBoxList();
	}
}
