package com.financial.android.utils;

import android.widget.TextView;

public abstract class TimerProcessor {
	private int serialNumber;
	public TextView view;
	public abstract void process();
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
}