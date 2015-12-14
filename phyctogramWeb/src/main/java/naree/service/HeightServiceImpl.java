package naree.service;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.HeightDao;
import naree.db.domain.Height;

@Service
public class HeightServiceImpl implements HeightService {
	
	private static final Logger logger = LoggerFactory.getLogger(HeightServiceImpl.class);

	@Autowired
	private HeightDao heightDao;
	
	@Override
	public void registerHeight(Height insHeight) {
		String nowHeightSeq = heightDao.selectHeightSeq();
		String nextHeightSeq = null;
		System.out.println(nowHeightSeq);
		
		//현재 날짜 계산
		Calendar cal = java.util.Calendar.getInstance();
		int nYear = cal.get(Calendar.YEAR);
		int nMonth = cal.get(Calendar.MONTH)+1;
		int nDay = cal.get(Calendar.DAY_OF_MONTH);
		String year = String.valueOf(nYear);
		String month = String.valueOf(nMonth);
		String day = String.valueOf(nDay);
		
		if(month.length() == 1){
			month = "0".concat(month);
		}
		if(day.length() == 1){
			day = "0".concat(day);
		}
		//키 시퀀스 만들기
		if(nowHeightSeq == null){
			nextHeightSeq = "".concat(year).concat(month).concat(day).concat("#").concat(String.format("%09d", 1));
		} else {
			String nowYear = nowHeightSeq.substring(0, 4);
			String nowMonth = nowHeightSeq.substring(4, 6);
			String nowDay = nowHeightSeq.substring(6, 8);
			if(!year.equals(nowYear)){
				nextHeightSeq = "".concat(year).concat(month).concat(day).concat("#").concat(String.format("%09d", 1));
			} else if(!month.equals(nowMonth)){
				nextHeightSeq = "".concat(nowYear).concat(month).concat(day).concat("#").concat(String.format("%09d", 1));
			} else if(!day.equals(nowDay)){
				nextHeightSeq = "".concat(nowYear).concat(nowMonth).concat(day).concat("#").concat(String.format("%09d", 1));
			} else {
				int no = Integer.parseInt(nowHeightSeq.substring(9));
				nextHeightSeq = "".concat(nowYear).concat(nowMonth).concat(nowDay).concat("#").concat(String.format("%09d", no+1));
			}
		}
		
		insHeight.setHeight_seq(nextHeightSeq);
		logger.info("저장 : " + insHeight);
		
		heightDao.insertHeight(insHeight);
		
	}

	@Override
	public List<Height> selectHeightByUser_seq(String user_seq) {
		
		return heightDao.selectHeightByUser_seq(user_seq);
	}

	@Override
	public List<Height> findHeightByUserSeqFT(String user_seq, String dateFrom, String dateTo) {
		
		return heightDao.selectHeightByUserSeqFT(user_seq, dateFrom, dateTo);
	}

	@Override
	public int delHeightByHeightSeq(String height_seq) {
		
		return heightDao.delectHeightByHeightSeq(height_seq);
	}

}
