package com.financial.android;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.financial.android.dao.CityDao;
import com.financial.android.db.CitySQLite;
import com.financial.android.domain.City;

import java.util.List;

/**
 * Created by wyy on 2016/1/14.
 */
public class AndroidTest extends AndroidTestCase {

    public void test() throws Exception {
        CitySQLite helper = new CitySQLite(getContext());
        helper.getWritableDatabase();
    }

    public void testInsert() {
        CityDao city = new CityDao(getContext());
        city.insert("上海");
    }

    public void testQuery() {
        CityDao city = new CityDao(getContext());
//        City query = city.query("上海");
    }

    public void testQueryAll() {
        CityDao city = new CityDao(getContext());
        List<City> cities = city.queryAll();
    }

    public void testTranslation() {
        CitySQLite helper = new CitySQLite(getContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        db.beginTransaction();
        long start=System.currentTimeMillis();
        try {

            for (int i = 0; i < 100; i++) {
                db.execSQL("insert into city(name) values('wang"+i+"')");


            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        long dutation=System.currentTimeMillis()-start;
        System.out.println("::::::::"+dutation);
    }

}
