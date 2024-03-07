package com.lzj.admin.enums;

public enum TypeFlagEnum {
	image(0,"图片"),
	video(1,"视频");

	private Integer type;
	private String name;

	TypeFlagEnum(Integer type, String name){
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