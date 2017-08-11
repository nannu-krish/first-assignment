package com.cg.practice;

public class HelloWorldPojo {
	private int id;
	private String msg;
	
	
	public HelloWorldPojo(int id, String msg) {
		super();
		this.id = id;
		this.msg = msg;
	}
	
	public HelloWorldPojo(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
