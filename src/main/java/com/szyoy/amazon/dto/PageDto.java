package com.szyoy.amazon.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PageDto<T> {
	
	private List<T> content;
	private int currentPageNo;
	private long maxPageNo;
	private long maxCount;
	
	public PageDto() {}
	
	
	public PageDto(List<T> content, int currentPageNo, long maxPageNo, long maxCount) {
		this.content = content;
		this.currentPageNo = currentPageNo;
		this.maxPageNo = maxPageNo;
		this.maxCount = maxCount;
	}


	public List<T> getContent() {
		return content;
	}


	public void setContent(List<T> content) {
		this.content = content;
	}


	public int getCurrentPageNo() {
		return currentPageNo;
	}


	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}


	public long getMaxPageNo() {
		return maxPageNo;
	}


	public void setMaxPageNo(long maxPageNo) {
		this.maxPageNo = maxPageNo;
	}


	public long getMaxCount() {
		return maxCount;
	}


	public void setMaxCount(long maxCount) {
		this.maxCount = maxCount;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
