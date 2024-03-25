package com.lzj.admin.enums;

public enum PointStatusEnum {
	ADD(1,"增加积分"),
	SUBTRACT(2,"减少积分");

	private Integer type;
	private String name;

	PointStatusEnum(Integer type, String name){
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