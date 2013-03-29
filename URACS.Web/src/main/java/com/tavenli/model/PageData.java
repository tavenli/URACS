package com.tavenli.model;

import java.util.List;

public class PageData<T> {

	private int pageIndex;
	private int pageSize;
	private int totalCount;
	private List<T> pageData;
	private int startRow;
	private int totalPage;
	
	public PageData(int pageIndex, int pageSize){
		this.pageIndex = pageIndex <= 0 ? 1 : pageIndex;
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public int getStartRow() {
		startRow = (pageIndex-1) * pageSize;
		return startRow;
	}

	public int getTotalPage() {
		totalPage = (int) Math.ceil(totalCount/Double.parseDouble(String.valueOf(pageSize)));
		return totalPage;
	}
	

	
	
}
