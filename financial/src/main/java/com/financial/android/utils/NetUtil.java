package com.financial.android.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
	
	private static final String TAG = "NetUtils";

	/**
	 * 判断是否有网
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectionAvailable(Context context) {
		ConnectivityManager con = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo workinfo = con.getActiveNetworkInfo();
		if (workinfo == null || !workinfo.isAvailable()) {
			return false;
		}
		return true;
	}
	
    /**
     * 判断WiFi是否连接
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context)  
    {  
        ConnectivityManager mConnMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        NetworkInfo mWifi = mConnMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
        NetworkInfo mMobile = mConnMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  
        boolean flag = false;  
        if((mWifi != null)  && ((mWifi.isAvailable()) || (mMobile.isAvailable())))  
        {  
            if((mWifi.isConnected()) || (mMobile.isConnected()))  
            {  
                flag = true;  
            }  
        }  
        return flag;  
        
    }  
    
	
}
