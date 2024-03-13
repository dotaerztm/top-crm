package com.lzj.admin.enums;

public enum FansEnum {
	BACK_FOLLOWED(1,"回关"),
	MUTUAL(2,"互相关注");

	private Integer type;
	private String name;

	FansEnum(Integer type, String name){
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