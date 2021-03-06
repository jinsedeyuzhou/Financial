package com.financial.android.view;

import com.financial.android.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;



/**
 * 自定义圆形进度条
 * @author wyy
 *
 */
public class CustomProgressDialog extends ProgressDialog {

	private String content;
	private TextView progress_dialog_content;



	public CustomProgressDialog(Context context, String content) {
		this(context,content, R.style.Dialog);
	}
	public CustomProgressDialog(Context context, String content, int theme) {
		super(context, theme);
		this.content = content;
		setCanceledOnTouchOutside(false);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}

	private void initData() {
		progress_dialog_content.setText(content);
	}

	public void setContent(String str) {
		progress_dialog_content.setText(str);
	}

	private void initView() {
		setContentView(R.layout.custom_progress_dialog);
		progress_dialog_content = (TextView) findViewById(R.id.progress_dialog_content);
	}

}
