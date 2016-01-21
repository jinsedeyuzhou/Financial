package com.financial.android.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.financial.android.db.CitySQLite;
import com.financial.android.domain.City;

public class CityDao {

	private  CitySQLite helper;

	public CityDao(Context context)
	{
		helper=new CitySQLite(context);
	}

	public void insert(String name)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into city(name) values(?);", new Object[]{name});
		db.close();
	}

	public void update(String rename,String name)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update city set name=? where name=? ;", new Object[]{rename,name});
		db.close();
	}

	public void delete(String name)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from city where name=?;", new Object[]{name});
		db.close();
	}

	public City query(String name) {

		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select *  from city where name=?;", new String[]{name});
		City city=null;
        if (cursor!=null&&cursor.moveToFirst())
        {
			int _id = cursor.getInt(0);
			String cityname = cursor.getString(cursor.getColumnIndex("name"));
			city=new City(_id,cityname);
		}

		db.close();
		return city;
	}

	public List<City> queryAll()
	{
        List<City> citys=new ArrayList<City>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor query = db.rawQuery("select * from city;", null);
        City city=null;
        if (query!=null&& query.getCount()>0)
        {

			while(query.moveToNext()) {
                int _id = query.getInt(0);
                String name = query.getString(1);
                city = new City(_id, name);
                citys.add(city);
            }
        }
		db.close();
		return citys;
	}
}
