package com.lzj.admin.enums;

public enum AppletUserEnum {
	TOURIST(0,"游客"),
	STUDENT(1,"学员用户"),
	TEACHER(2,"讲师");

	private Integer type;
	private String name;

	AppletUserEnum(Integer type, String name){
		this.type = type;
		this.name = name;
	}

	public Integer getType(){
		return type;
	}

	public String getName(){
		return name;
	}

}