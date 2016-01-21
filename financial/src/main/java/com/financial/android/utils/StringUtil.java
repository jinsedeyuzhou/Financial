package com.financial.android.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * java判断字符串是否为数字或中文或字母
 * 
 * @author Administrator
 * 
 */
public class StringUtil {

	/**
	 * 格式化输入百分数字符 如果位数不够可采用下面的方法 DecimalFormat percentFormat = new
	 * DecimalFormat("##.00%");有两位小数显示两位小数，不填0
	 * 
	 * @param d
	 * @return
	 */
	public static String formatPercent(double d) {
		DecimalFormat df = new DecimalFormat("##.##%");
		return df.format(d);
	}
	/**
	 * 格式化输入百分数字符 如果位数不够可采用下面的方法 DecimalFormat percentFormat = new
	 * DecimalFormat("##.00%");保留两位小数，不足填0；
	 * 
	 * @param d
	 * @return
	 */
	public static String formatPercent1(double d) {
		DecimalFormat df = new DecimalFormat("##.00%");
		return df.format(d);
	}
	/**
	 * 格式化输入百分数字符 如果位数不够可采用下面的方法 DecimalFormat percentFormat = new
	 * DecimalFormat("##%");四舍五入保留整数
	 * 
	 * @param d
	 * @return
	 */
	public static String formatPercent0(double d) {
		DecimalFormat df = new DecimalFormat("##%");
		return df.format(d);
	}

	/**
	 * 格式化输出本地货币字符
	 * 
	 * @param d
	 * @return
	 */
	public static String formatLocalCurrency(double d) {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		String result = currencyFormat.format(d);
		return result.substring(1);
	}

	/**
	 * 1.判断字符串是否仅为数字:
	 * 
	 * @param str
	 * @return
	 */

	public static boolean isNumeric1(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用正则表达式 判断字符串是否仅为数字:
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric2(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 查找xml下第一个出现的关键值
	 * 
	 * @param xml
	 *            字符源
	 * @param name
	 *            key值
	 */
	public String getXmlValue(String xml, String name) {

		int beginIndex = xml.indexOf("<" + name + ">");
		int endIndex = xml.indexOf("</" + name + ">");
		if (beginIndex == -1 || endIndex == -1) {
			return null;
		}
		String retsult = xml
				.substring(beginIndex + name.length() + 2, endIndex);
		return retsult;
	}

	/**
	 * 用ascii码 判断字符串是否仅为数字:
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric3(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	/**
	 * 2.判断一个字符串的首字符是否为字母
	 * 
	 * @param s
	 * @return
	 */
	public static boolean test(String s) {
		char c = s.charAt(0);
		int i = c;
		if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean check(String fstrData) {
		char c = fstrData.charAt(0);
		if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 3 .判断是否为汉字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean vd(String str) {

		char[] chars = str.toCharArray();
		boolean isGB2312 = false;
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				int[] ints = new int[2];
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;
				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
						&& ints[1] <= 0xFE) {
					isGB2312 = true;
					break;
				}
			}
		}
		return isGB2312;
	}

	/**
	 * sun
	 * 
	 * @param cTime
	 * @param format
	 *            "yyyy/MM/dd HH:mm"
	 * @return
	 */
	public static String formatUnixTime(long cTime, String format) {
		// Timestamp time = new Timestamp(cTime);
		SimpleDateFormat formatString = new SimpleDateFormat(format);
		return formatString.format(new Date(cTime));
	}

	/**
	 * 获取随机串
	 * 
	 * @return
	 */
	public static String getRandomString() {
		StringBuffer result = new StringBuffer();
		result.append(String.valueOf((int) (Math.random() * 10))).append("_");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		result.append(str);

		return result.toString();
	}
}
