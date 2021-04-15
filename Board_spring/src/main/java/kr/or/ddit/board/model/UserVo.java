package kr.or.ddit.board.model;

import java.util.Date;

public class UserVo {
	
	
	private String userid;
	private String usernm;
	private String pass;
	private Date reg_dt; //java.util.Date; 이거해야지 sql거 하면 안돼
	private String alias;
	private String addr1;
	private String addr2;
	private String zipcode;
	private String filename;
	private String realfilename;
	
	//getter, setter, toString
	//이 3개는 기본적으로 만들어놔야지 나중에 작업하기 수월하지


	// 대다수의 FRAMEWORK는 기본생성자를 필요로 한다.
	public UserVo() {}
	
	//이건 생성자. 파라미터가 있는 생성자를 만든거.
	//UserVo userVo = new UserVo("ddit", "대덕인재", "dditpass", new Date(), "개발원_m", "대전시 중구 중앙로 76", "4층 대덕인재개발원", "34940");
	//new해서 이렇게 한건 객체 생성. 이제 위에거가 메모리에 올라가는거지
	public UserVo(String userid, String usernm, String pass, Date reg_dt, String alias, String addr1,
			String addr2, String zipcode, String filename, String realfilename) {
		this.userid = userid;
		this.usernm = usernm;
		this.pass = pass;
		this.reg_dt = reg_dt;
		this.alias = alias;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipcode = zipcode;
		this.filename = filename;
		this.realfilename = realfilename;
	}


	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddr1() {
		return addr1 == null ? "" : addr1;
	}


	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}


	public String getAddr2() {
		return addr2 == null ? "" : addr2;
	}


	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}


	public String getZipcode() {
		return zipcode == null ? "" : zipcode;
	}


	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getRealfilename() {
		return realfilename;
	}


	public void setRealfilename(String realfilename) {
		this.realfilename = realfilename;
	}

	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", usernm=" + usernm + ", pass=" + pass + ", reg_dt=" + reg_dt + ", alias="
				+ alias + ", addr1=" + addr1 + ", addr2=" + addr2 + ", zipcode=" + zipcode + ", filename=" + filename
				+ ", realfilename=" + realfilename + "]";
	}
	
	
	
	
}
