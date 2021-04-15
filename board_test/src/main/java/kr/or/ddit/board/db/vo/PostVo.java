package kr.or.ddit.board.db.vo;

import java.util.Date;

public class PostVo {

	private int bd_no     ;
	private int post_no   ;
	private String writer    ;
	private String title     ;
	private String cont      ;
	private Date reg_dt    ;
	private int post_del  ;
	private int pr_post_no;
	private int pr_bd_no  ;
	private String pr_post_wr;
	
	
	public PostVo(){}
	public PostVo(int bd_no, int post_no, String writer, String title, String cont, Date reg_dt, int post_del, int pr_post_no, int pr_bd_no, String pr_post_wr){
		
		this.bd_no = bd_no;
		this.post_no = post_no;
		this.writer = writer;
		this.title = title;
		this.cont = cont;
		this.reg_dt = reg_dt;
		this.post_del = post_del;
		this.pr_post_no = pr_post_no;
		this.pr_bd_no = pr_bd_no;
		this.pr_post_no = pr_post_no;
		
	}
	
	
	public int getBd_no() {
		return bd_no;
	}
	public void setBd_no(int bd_no) {
		this.bd_no = bd_no;
	}
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	public int getPost_del() {
		return post_del;
	}
	public void setPost_del(int post_del) {
		this.post_del = post_del;
	}
	public int getPr_post_no() {
		return pr_post_no;
	}
	public void setPr_post_no(int pr_post_no) {
		this.pr_post_no = pr_post_no;
	}
	public int getPr_bd_no() {
		return pr_bd_no;
	}
	public void setPr_bd_no(int pr_bd_no) {
		this.pr_bd_no = pr_bd_no;
	}
	public String getPr_post_wr() {
		return pr_post_wr;
	}
	public void setPr_post_wr(String pr_post_wr) {
		this.pr_post_wr = pr_post_wr;
	}
	
	@Override
	public String toString() {
		return "PostVo [bd_no=" + bd_no + ", post_no=" + post_no + ", writer=" + writer + ", title=" + title + ", cont="
				+ cont + ", reg_dt=" + reg_dt + ", post_del=" + post_del + ", pr_post_no=" + pr_post_no + ", pr_bd_no="
				+ pr_bd_no + ", pr_post_wr=" + pr_post_wr + "]";
	}
	
	
	
	
}
