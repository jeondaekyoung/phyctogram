package naree.rest.controller;

import org.springframework.web.multipart.MultipartFile;

public class TestDomail {

	private String aaa;
	private String bbb;
	private MultipartFile myfile;			
	private String myfilename;
	private String myfileserver;
	public String getAaa() {
		return aaa;
	}
	public void setAaa(String aaa) {
		this.aaa = aaa;
	}
	public String getBbb() {
		return bbb;
	}
	public void setBbb(String bbb) {
		this.bbb = bbb;
	}
	public MultipartFile getMyfile() {
		return myfile;
	}
	public void setMyfile(MultipartFile myfile) {
		this.myfile = myfile;
	}
	public String getMyfilename() {
		return myfilename;
	}
	public void setMyfilename(String myfilename) {
		this.myfilename = myfilename;
	}
	public String getMyfileserver() {
		return myfileserver;
	}
	public void setMyfileserver(String myfileserver) {
		this.myfileserver = myfileserver;
	}
	@Override
	public String toString() {
		return "TestDomail [aaa=" + aaa + ", bbb=" + bbb + ", myfile=" + myfile + ", myfilename=" + myfilename
				+ ", myfileserver=" + myfileserver + "]";
	}
	
}
