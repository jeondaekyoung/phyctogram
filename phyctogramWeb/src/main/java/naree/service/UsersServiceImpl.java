package naree.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.DiaryDao;
import naree.dao.HeightDao;
import naree.dao.UsersDao;
import naree.db.domain.Analysis;
import naree.db.domain.Height;
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

	@Override
	public Analysis findMonthNumAnimalByUserSeq(int user_seq) {
		
		
		Analysis analysis = usersDao.selectMonthNumAnimalByUserSeq(user_seq);
		
		//데이터가 없을 때 표시할 이미지가 없다. 
		/*int result = heightDao.selectExistHeightByUserSeq(user_seq);
		if(result == 0){
			//입력된 데이터가 없음을 500을 넣어준다
			analysis.setMonth_num(500);
			return analysis;
		}*/
		
		return analysis;
	}

	@Override
	public List<Height> findUsersMainInfoByUserSeq(int user_seq) {
		// 최근신장 2개 불러오기
		List<Height> heights = heightDao.selectMax2HeightByUserSeq(user_seq);
		System.out.println("최근신장 몇개 : " + heights.size());
		for(Height h : heights){
			h.setRank(heightDao.selectRankByHeight(h));		//상위
			h.setAnimal_img(heightDao.selectAnimalByHeight(h));	//이미지
			System.out.println(h.toString());
		}
		
		return heights;
	}

	@Override
	public List<Height> findUsersAnalysisByUserSeq(int user_seq) {
		//그래프 그리기 : 최근 키 기록 가져오기(12개)
		List<Height> heights = heightDao.selectHeightForGraphByUserSeq(user_seq);
		for(Height h : heights){
			//상위 가져오기
			int rank = heightDao.selectRankByHeight(h);
			h.setRank(rank);
			//평균키 가져오기
			double height_50 = heightDao.selectAveHeightByHeight(h);
			h.setHeight_50(height_50);
		}
		for(Height h : heights){
			System.out.println("키 목록 : " + h.toString());
		}
		return heights;
	}



}
