package naree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naree.dao.AdminDao;
import naree.db.domain.Admin;
import naree.util.exception.PhyctogramWebException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public Admin login(String id, String pw) {
		System.out.println("AdminServiceImpl.login()");
		Admin admin = adminDao.searchAdminById(id);
		
		if(admin == null){
			throw new PhyctogramWebException("존재하지 않는 아이디입니다");
		}
		
		admin.setPw(pw);
		if(!adminDao.searchAdminByAdmin(admin)){
			throw new PhyctogramWebException("잘못된 패스워드입니다");
		}
		
		return admin;
	}
	
	
}
