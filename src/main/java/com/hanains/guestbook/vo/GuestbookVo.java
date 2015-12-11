package com.hanains.guestbook.vo;

public class GuestbookVo {
	private Long no;
	private String name;
	private String password;
	private String message;
	private String regDate;
	
	public GuestbookVo() {
		super();
	}
	public GuestbookVo(Long no, String name, String password, String message, String regDate) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.message = message;
		this.regDate = regDate;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GuestBookVo [no=");
		builder.append(no);
		builder.append(", name=");
		builder.append(name);
		builder.append(", password=");
		builder.append(password);
		builder.append(", message=");
		builder.append(message);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append("]");
		return builder.toString();
	}
	
}
