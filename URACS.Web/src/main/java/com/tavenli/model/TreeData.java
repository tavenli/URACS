package com.tavenli.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeData {

	private List<NodeData> data = new ArrayList<NodeData>();
	private List<TreeData> children = new ArrayList<TreeData>();

	public List<NodeData> getData() {
		return data;
	}

	public void setData(List<NodeData> data) {
		this.data = data;
	}
	
	public List<TreeData> getChildren() {
		return children;
	}
	public void setChildren(List<TreeData> children) {
		this.children = children;
	}
	
	
}
