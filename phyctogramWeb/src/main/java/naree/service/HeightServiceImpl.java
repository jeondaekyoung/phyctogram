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
	
	/**
	 * 키정보 저장하기
	 * @param insHeight
	 */
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

	/**
	 * user_seq의 height 불러오기
	 * @param user_seq
	 * @return
	 */
	@Override
	public List<Height> selectHeightByUser_seq(String user_seq) {
		
		return heightDao.selectHeightByUser_seq(user_seq);
	}

	/**
	 * 내 아이(유저) 기록조회
	 * @param user_seq
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	@Override
	public List<Height> findHeightByUserSeqFT(String user_seq, String dateFrom, String dateTo, int pageCnt) {
		//기록조회
		List<Height> heights = heightDao.selectHeightByUserSeqFT(user_seq, dateFrom, dateTo, pageCnt*10);
		for(Height h : heights){
			//각각의 데이터(키를잰날, 키)로 상위를 조회함(user_seq로 생일을 알수있음)
			//나이가 19세초과, 229개월 초과시 228개월로 계산(쿼리계산)
			//키가 커서 데이터가 없을 경우 상위3%가져온다
			h.setRank(heightDao.selectRankByHeight(h));
		}
		
		return heights;
	}

	/**
	 * 키 삭제
	 * @param height_seq
	 * @return
	 */
	@Override
	public int delHeightByHeightSeq(String height_seq) {
		
		return heightDao.deleteHeightByHeightSeq(height_seq);
	}

	/**
	 * 키 시퀀스 만들기
	 * @return
	 */
	@Override
	public String nextHeightSeq() {
		String nowHeightSeq = heightDao.selectHeightSeq();
		String nextHeightSeq = null;
		System.out.println("현재 키 시퀀스 : " + nowHeightSeq);
		
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
		
		System.out.println("다음 키 시퀀스 : " + nextHeightSeq);
		return nextHeightSeq;
	}

	/**
	 * (안드로이드에서) 키 저장하기
	 * @param height
	 * @return
	 */
	@Override
	public int registerHeightAnd(Height height) {
		
		return heightDao.insertHeightAnd(height);
	}

}
