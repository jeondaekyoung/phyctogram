package naree.service;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {

	/**
	 * 파일업로드
	 * @param myfile   업로드파일
	 * @param gubun   구분(일기, 수다방)
	 * @param image_nm   원본파일이름(phyctogram) 동일하다
	 * @param image_server_nm 서버에 저장되는 이름
	 */
	void writeFileSingle(MultipartFile myfile, String gubun, String image_nm, String image_server_nm);

}
