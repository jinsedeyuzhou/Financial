package com.financial.android.domain;

public class City {
	//自增字段
	private int _id;
	// 城市名称
	private String name;

	public City(int _id, String name) {
		super();
		this._id = _id;
		this.name = name;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [_id=" + _id + ", name=" + name + "]";
	}

	
}
