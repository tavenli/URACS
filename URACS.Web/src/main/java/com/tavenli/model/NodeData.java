package com.tavenli.model;

import java.util.HashMap;
import java.util.Map;

public class NodeData {

	private String title="";
	private Map<String, Object> attr = new HashMap<String, Object>();
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Map<String, Object> getAttr() {
		return attr;
	}
	public void setAttr(Map<String, Object> attr) {
		this.attr = attr;
	}

	
}
