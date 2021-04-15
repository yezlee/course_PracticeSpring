package kr.or.ddit.board.db.vo;

public class BoardVo {

	private int bd_no;
	private int bd_act;
	private String bd_nm;
	
	public int getBd_no() {
		return bd_no;
	}
	public void setBd_no(int bd_no) {
		this.bd_no = bd_no;
	}
	public int getBd_act() {
		return bd_act;
	}
	public void setBd_act(int bd_act) {
		this.bd_act = bd_act;
	}
	public String getBd_nm() {
		return bd_nm;
	}
	public void setBd_nm(String bd_nm) {
		this.bd_nm = bd_nm;
	}
	
	

	public BoardVo(int bd_no, int bd_act, String bd_nm) {
		super();
		this.bd_no = bd_no;
		this.bd_act = bd_act;
		this.bd_nm = bd_nm;
	}
	//보드 넘버는 시퀀스로 받아주기때문에 위에서 굳이 파라미터로 받아주지 않아도 된다. 만약 저렇게 받아줄거면
	//BoardVo를 사용하는 곳에서 아무값이나 넣어주어도 된다. 
	
	/*
	  위에 생성자를 다시 해주는 
	BoardVo board = new BoardVo();
	board.setBd_act(변수명);
	board.getBd_act(변수명);
	
	 이런식으로 안해주려고
	 
	 
	 파라미터 값을 가져와서 this 해서 위에 변수한테 넣어주니까
	 
	 */
	
	
	
	
	
	@Override
	public String toString() {
		return "boardVo [bd_no=" + bd_no + ", bd_act=" + bd_act + ", bd_nm=" + bd_nm + "]";
	}
	
	
}
