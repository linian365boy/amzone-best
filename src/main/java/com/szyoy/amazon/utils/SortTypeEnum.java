package com.szyoy.amazon.utils;

import java.util.ArrayList;
import java.util.List;

public enum SortTypeEnum {
	
	ID("id"),
	PRODUCTNAME("productName"),
	REVIEWS("reviews"),
	STARS("stars"),
	PRICE("price");
	
	private String name;
	
	private SortTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static List<String> getNames(){
		List<String> names = new ArrayList<>(SortTypeEnum.values().length);
		for (SortTypeEnum typeEnum : SortTypeEnum.values()) {
			names.add(typeEnum.name);
		}
		return names;
	}
	
}
