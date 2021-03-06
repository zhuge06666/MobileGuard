package cn.edu.gdmec.android.mobileguard.m5virusscan.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Asus on 2017/11/16.
 */

public class AntiVirusDao {
    private static Context context;
    private static String dbname;
    public AntiVirusDao(Context context){
        this.context = context;
        dbname = "/data/data/"+context.getPackageName()+"/files/antivirus.db";
    }
    public String checkVirus(String md5){
        String desc = null;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                dbname,null,
                SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select desc from datable where md5=?",new String[]{ md5});
        if(cursor.moveToNext()){
            desc = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return desc;
    }
    public String getVersion(){
        String desc=null;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(dbname,null,SQLiteDatabase.OPEN_READONLY);
       Cursor cursor = db.rawQuery("select * from version",null);
        if (cursor.moveToNext()){
            desc=cursor.getString(0)+"."+cursor.getString(1)+"."+cursor.getString(2);
        }
        cursor.close();
        db.close();
        return desc;
    }
}
