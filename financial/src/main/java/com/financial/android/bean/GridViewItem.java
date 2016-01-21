package com.financial.android.bean;

/**
 * Gridview自定义布局填充信息
 */
public class GridViewItem {
	private String title;
	private int imgId;
	private String description;
	public GridViewItem(String title, int imgId) {
		super();
		this.title = title;
		this.imgId = imgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
