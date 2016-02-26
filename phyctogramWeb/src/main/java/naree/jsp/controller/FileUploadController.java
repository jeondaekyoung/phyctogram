package naree.jsp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import naree.db.domain.Diary;
import naree.rest.controller.TestDomail;
import naree.service.CommonService;
import naree.service.DiaryService;

@Controller
@RequestMapping("fileUpload")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
	private CommonService commonService;

	@Autowired
	private DiaryService diaryService;
	
	//single
	@RequestMapping(value = "single", method = RequestMethod.POST)
	public @ResponseBody String single(@RequestParam(value = "diary_seq") int diary_seq,
			@RequestParam(value = "myfile") MultipartFile myfile){
		System.out.println("single1 실행 : " + diary_seq);
		if(myfile == null){
			logger.info("fileUpload/single1 : " + "myfile은 없다");
			
		} else {
			logger.info("fileUpload/single1 : " + myfile.getOriginalFilename());
			
			Diary diary = new Diary();
			diary.setDiary_seq(diary_seq);
			//업로드 파일이름
			String image_nm = myfile.getOriginalFilename();
			diary.setImage_nm(image_nm);
			
			String image_nmExt = image_nm.substring(image_nm.indexOf("."), image_nm.length());
			//서버에 저장될 파일 이름
			diary.setImage_server_nm(new StringBuilder().append(diary_seq).append(image_nmExt).toString());
			
			//서버에 저장
			logger.info("파일 저장 정보 : " + diary.toString());
			commonService.writeFileSingle(myfile, "diary", diary.getImage_nm(), diary.getImage_server_nm());
			
			//디비 업데이트
			int result = diaryService.modifyDiaryForFile(diary);
			if(result == 1){
				return "success";
			} 
		}
		return "fail";
	}
	
	//single1
	@RequestMapping(value = "single1", method = RequestMethod.POST)
	public @ResponseBody String single1(@RequestParam(value = "myfile") MultipartFile myfile){
		System.out.println("single1 실행");
		if(myfile == null){
			logger.info("fileUpload/single1 : " + "myfile은 없다");
		} else {
			logger.info("fileUpload/single1 : " + myfile.getOriginalFilename());
		}
		
		return "test1";
	}
	
	//single2
	@RequestMapping(value = "single2", method = RequestMethod.POST)
	public @ResponseBody String single2(@RequestBody MultipartFile myfile){
		System.out.println("single2 실행");
		if(myfile == null){
			logger.info("fileUpload/single2 : " + "myfile은 없다");
		} else {
			logger.info("fileUpload/single2 : " + myfile.getOriginalFilename());
		}
		
		return "test2";
	}
	
	//single3
	@RequestMapping(value = "single3", method = RequestMethod.POST)
	public @ResponseBody String single3(MultipartFile myfile){
		System.out.println("single3 실행");
		if(myfile == null){
			logger.info("fileUpload/single3 : " + "myfile은 없다");
		} else {
			logger.info("fileUpload/single3 : " + myfile.getOriginalFilename());
		}
		
		return "test3";
	}
	
	//single4
	@RequestMapping(value = "single4", method = RequestMethod.POST)
	public @ResponseBody String single4(TestDomail testDomail){
		System.out.println("single4 실행");
		logger.info(testDomail.toString());
		
		if(testDomail == null){
			logger.info("fileUpload/single4 : " + "testDomail은 없다");
		} else {
			logger.info("fileUpload/single4 : " + "testDomail은 있다");
			MultipartFile myFile = testDomail.getMyfile();
			if(myFile != null){
				String fileName = myFile.getOriginalFilename();
				//업로드 파일이름
				testDomail.setMyfilename(fileName);
				String fileNameExt = fileName.substring(fileName.indexOf("."), fileName.length());
				//서버에 저장된 파일이름
				testDomail.setMyfileserver("서버에저장되는파일이름_" + fileNameExt);
				
				//서버에 저장
				//commonService.writeFile(adSoundFile, "sound", ad.getAd_sound_name(), ad.getAd_sound_server());
			} else {
				logger.info("testDomail.myfile 은 NULL 이다");
			}
		}
		
		return "test4";
	}
	
	@RequestMapping(value = "single41", method = RequestMethod.POST)
	public @ResponseBody String single41(@RequestParam("diary") Diary diary, @RequestParam(value = "myfile") MultipartFile myfile){
		System.out.println("single41 실행");
		logger.info(diary.toString() + ", " + myfile.getOriginalFilename());
		
		
		/*if(testDomail == null){
			logger.info("fileUpload/single4 : " + "testDomail은 없다");
		} else {
			logger.info("fileUpload/single4 : " + "testDomail은 있다");
			MultipartFile myFile = testDomail.getMyfile();
			if(myFile != null){
				String fileName = myFile.getOriginalFilename();
				//업로드 파일이름
				testDomail.setMyfilename(fileName);
				String fileNameExt = fileName.substring(fileName.indexOf("."), fileName.length());
				//서버에 저장된 파일이름
				testDomail.setMyfileserver("서버에저장되는파일이름_" + fileNameExt);
				
				//서버에 저장
				//commonService.writeFile(adSoundFile, "sound", ad.getAd_sound_name(), ad.getAd_sound_server());
			} else {
				logger.info("testDomail.myfile 은 NULL 이다");
			}
		}*/
		
		return "test41";
	}
	
	@RequestMapping(value = "single42", method = RequestMethod.POST)
	public @ResponseBody String single42(@RequestBody Diary diary, @RequestParam(value = "myfile") MultipartFile myfile){
		System.out.println("single42 실행");
		logger.info(diary.toString() + ", " + myfile.getOriginalFilename());
		
		
		return "test42";
	}
	
	@RequestMapping(value = "single43", method = RequestMethod.POST)
	public @ResponseBody String single43(Diary diary){
		System.out.println("single43 실행");
		//logger.info(diary.toString() + ", " + myfile.getOriginalFilename());
		logger.info(diary.toString());
		
		
		return "test43";
	}
	
	
	
	//single5
	@RequestMapping(value = "single5", method = RequestMethod.POST)
	@ResponseBody
	public String databaseInsert(TestDomail testDomail){
		System.out.println("single5 실행");
		logger.info(testDomail.toString());
		
		String returnVal = "{\"ok\" : false, \"msg\": \"Cannot find multipart file\"}";
		try{
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
			MultipartFile pictureFile = testDomail.getMyfile();
			//String fileName = pictureFile.getOriginalFilename();
			if (pictureFile != null) {
				String originalFileName = pictureFile.getOriginalFilename();
				logger.info("파일 이름 : " + originalFileName);
				/*String contentType = pictureFile.getContentType();
				//if (contentType.endsWith("image/jpeg")) { //안드로이드에선 사친첩에서만 보내기때문에 검사 안함
				testDomail.setMyfilename(originalFileName);
				try {
					Date now = new Date();
					String directory = siteConfig.getImagesPath(); //미리 세팅해둔 이미지 저장 경로
					String dateString = new SimpleDateFormat("yyyy-MM-dd").format(now);
					String uniqueFileName = UUID.randomUUID().toString();
					File dir = new File(directory + "/" + dateString);
					if (!dir.exists() || !dir.isDirectory()) {
					if (!dir.mkdir())
					return returnVal;
					}
			
					// 1. FileOutputStream 사용
					// byte[] fileData = file.getBytes();
					// FileOutputStream output = new FileOutputStream("C:/images/" +fileName);
					// output.write(fileData);
			
					// 2. File 사용
					File file = new File(directory + "/" + dateString + "/" + uniqueFileName + ".jpg");
					pictureFile.transferTo(file);
			
					dto.setFilePath(dateString + "/" + uniqueFileName + ".jpg");
					// 데이터 베이스 처리를 현재 위치에서 처리
			
					returnVal = "{\"ok\" : true, \"msg\": \"sucess\"}";
					} catch (IOException e) {
					e.printStackTrace();
					} // try - catch */
				
			} else {
				logger.info("fileUpload/single5 : " + "testDomail.myfile 은 NULL 이다");
			}
		}catch(Exception e){
		e.printStackTrace();
		}
		return returnVal;
	}
		
	
	@RequestMapping(value = "hallym", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/plain;charset=UTF-8")
	public String hallym(@RequestParam("myfile") MultipartFile file){
		System.out.println("hallym 실행");
		//String whoAmi = Thread.currentThread().getStackTrace()[1].toString();
		
		if(file != null){
			logger.info("file size : " + file.getSize());
		} else {
			logger.info("file이 없다");
		}
		
		return "hallym";
	}

}
