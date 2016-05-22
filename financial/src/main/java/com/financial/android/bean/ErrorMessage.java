package com.financial.android.bean;

/**
 * Created by yuxuan on 2016/2/10.
 * 返回的错误信息和编码
 */
public class ErrorMessage {
    private int errorCode;
    private String errorText;

    public ErrorMessage(int errorCode, String errorText) {
        this.errorCode = errorCode;
        this.errorText = errorText;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
