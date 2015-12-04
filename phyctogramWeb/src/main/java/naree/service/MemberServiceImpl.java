package naree.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.MemberDao;
import naree.db.domain.Member;

@Service
public class MemberServiceImpl implements MemberService{

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public int registerMember(Member member) {
		int result = 100;
		
		if(member.getJoin_route().equals("facebook")){
			//페이스북으로 현재 가입된 멤버인지 확인한다.
			result = memberDao.selectMemberExistByFacebook_id(member.getFacebook_id());
			logger.info("페이스북 가입확인 : " + result);
			if(result == 0){
				result = memberDao.insertMember(member);
			}
			return result;
		} else if(member.getJoin_route().equals("kakao")){
			result = memberDao.selectMemberExistByKakao_id(member.getKakao_id());
			logger.info("카카오 가입확인 : " + result);
			if(result == 0){
				result = memberDao.insertMember(member);
			}
			return result;
		} else {
			result = memberDao.selectMemberExistByEmail(member.getEmail());
			logger.info("픽토그램 가입확인 : " + result);
			if(result == 0){
				//가입
				result = memberDao.insertMember(member);
				Member phyctoMember = memberDao.selectMemberByEmail(member.getEmail());
				//가입동의 저장
				int join_agre = memberDao.insertJoinAgre(phyctoMember);
				logger.info("픽토그램 가입동의 입력 : " + join_agre);
			} else if(result == 1){
				logger.info("이미 픽토그램에 가입된 이메일입니다");
				return 5;
			}
			return result;
		}
		
	}

}
