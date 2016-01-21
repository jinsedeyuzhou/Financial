package com.financial.android.bean;

public class ProductInfo {
	private String name;
	private String description;
	
	public ProductInfo()
	{
		
	}
	
	public ProductInfo(String name, String description) {
		this();
		this.name = name;
		this.description = description;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductInfo [name=" + name + ", description=" + description
				+ "]";
	}
	
	
}
