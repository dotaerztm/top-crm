package com.lzj.admin.enums;

public enum FollowEnum {
	FOLLOW(0,"关注"),
	FOLLOWED(1,"已关注"),
	MUTUAL(2,"互相关注");

	private Integer type;
	private String name;

	FollowEnum(Integer type, String name){
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