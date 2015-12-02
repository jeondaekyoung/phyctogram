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
			result = memberDao.selectMemberByFacebook_id(member.getFacebook_id());
			logger.info("페이스북 가입확인 : " + result);
			if(result == 0){
				result = memberDao.insertMember(member);
			}
			return result;
		} else if(member.getJoin_route().equals("kakao")){
			result = memberDao.selectMemberByKakao_id(member.getKakao_id());
			logger.info("카카오 가입확인 : " + result);
			if(result == 0){
				result = memberDao.insertMember(member);
			}
			return result;
		} else {
			logger.info("픽토그램 가입확인해야함");
			return result;
		}
		
	}

}
