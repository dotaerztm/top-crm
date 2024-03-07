package com.lzj.admin.enums;

public enum TypeEnum {
	FOUNDER(1,"集团创始人"),
	CORE_TEAM(2,"核心团队"),
	BUSINESS_DIRECTION(3,"商业方向"),
	BRAND_DIRECTION(4,"品牌方向"),
	GRADE_ONE(5,"士气一年级"),
	GRADE_TWO(6,"士气二年级"),
	PUBLIC_WELFARE(7,"士气公益课"),
	WORKS(8,"士气作品"),
	INFORMATION(9,"士气资讯");

	private Integer type;
	private String name;

	TypeEnum(Integer type, String name){
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