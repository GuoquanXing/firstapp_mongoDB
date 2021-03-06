package com.guoquan.bean;

public class Product {
	private String id;
	private String name;
	private String category;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String toString() {
		return ("{_ID: " + this.getId() + ",name = " + this.getName() + ",category = " + this.getCategory() + "}");
	}

}
