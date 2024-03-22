package com.lzj.admin.enums;

public enum AppletMessageEnum {
	FANS(0,"粉丝记录"),
	SODA_OBTAIN(1,"汽水收到"),
	SODA_GIFT(2,"汽水赠送"),
	SYSTEM(3,"系统通知"),
	CAMPAIGN(4,"活动推送");

	private Integer type;
	private String name;

	AppletMessageEnum(Integer type, String name){
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