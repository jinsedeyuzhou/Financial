package com.financial.android.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUtils {

	private List<TimerProcessor> processList;
	private long mDelayMs;
	private long mPeriodMs;
	private boolean switchFlag;

	private Timer mTimer;
	private TimerTask mTimerTask;

	/**
	 * 构造函数
	 * 
	 * @param delayMs
	 *            延时
	 * @param periodMs
	 *            定时器的启动间距时间
	 * @param processor
	 *            定时处理器，由调用者定制实现
	 */
	public TimerUtils(long delayMs, long periodMs, TimerProcessor processor) {
		processList = new ArrayList<TimerProcessor>();
		processList.add(processor);
		mDelayMs = delayMs;
		mPeriodMs = periodMs;
	}

	public TimerUtils(long delayMs, long periodMs) {
		processList = new ArrayList<TimerProcessor>();
		mDelayMs = delayMs;
		mPeriodMs = periodMs;
	}

	// 添加定时器处理方法
	public void addTimerProcessor(TimerProcessor processor) {
		int i = 0;
		for (; i < processList.size(); i++) {
			TimerProcessor object = processList.get(i);
			if (object.getSerialNumber() == processor.getSerialNumber()) {
				boolean result = processList.remove(object);
				break;
			}
		}

		processList.add(processor);
		if (processList.size() == 1 && switchFlag == false) {
			startTimer();
			switchFlag = true;
		}
	}

	// 获取特定的处理器
	public TimerProcessor getTimerProcessor(int serialNumber) {
		if (processList == null) {
			return null;
		}
		for (int i = 0; i < processList.size(); i++) {
			TimerProcessor processor = processList.get(i);
			if (processor.getSerialNumber() == serialNumber) {
				return processor;
			}
		}
		return null;
	}

	// 移除定时器处理方法
	public void removeTimerProcessor(TimerProcessor processor) {
		processList.remove(processor);
		if (processList.size() == 0 && switchFlag == true) {
			stopTimer();
			switchFlag = false;
		}
	}

	// 移除所有处理器
	public void removeAllTimerProcessor() {
		processList.clear();
		stopTimer();
		switchFlag = false;
	}

	// 返回定时器引用计数
	public int count() {
		if (processList != null) {
			return processList.size();
		}
		return 0;
	}

	/**
	 * 启动定时器
	 */
	public void startTimer() {

		mTimer = new Timer(true);
		mTimerTask = new TimerTask() {
			@Override
			public void run() {
				if (processList != null) {
					for (int i = 0; i < processList.size(); i++) {
						TimerProcessor timerProcessor = processList.get(i);
						if (timerProcessor != null) {
							timerProcessor.process();
						}
					}
				}
			}
		};
		mTimer.schedule(mTimerTask, mDelayMs, mPeriodMs);
	}

	/**
	 * 停止定时器
	 */
	public void stopTimer() {
		if (mTimer != null) {
			mTimer.cancel();
			mTimer = null;
		}
		if (mTimerTask != null) {
			mTimerTask.cancel();
			mTimerTask = null;
		}
	}
}
