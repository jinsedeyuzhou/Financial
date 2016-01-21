package com.financial.android.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CitySQLite extends SQLiteOpenHelper {

	public CitySQLite(Context context)
	{
		this(context,"city.db",null,1);
	}
	public CitySQLite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table city(_id integer primary key autoincrement,name varchar(20));");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	//下面两个方法基本不用
	/**
	 *  当数据库打开时调用。这个实现会在升级数据库之前会检查isReadOnly()。
	 * @param db
	 */
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}
	
	/**
	 * 当数据库需要降低版本时候调用。
	 * 这个方法跟onUpgrade()很相似，但是只要当然版本比被请求的更新，它就会被调用。
	 * 尽管如此，这个方法不是抽象的，所以它并不强制用户去实现它。
	 * 如果不被重写，默认的实现将会拒绝降级并且抛出一个SQLiteException。
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 */
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		super.onDowngrade(db, oldVersion, newVersion);
	}

}
