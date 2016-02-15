package com.xwj.jokexwj.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.xwj.jokexwj.model.funnypic.FunnyPic;
import com.xwj.jokexwj.utils.Globals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwjsd on 2016-02-15.
 */
public class FunnyPicDb extends SQLiteOpenHelper {
    public FunnyPicDb(Context context, int version) {
        super(context, Globals.DB_PICS_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * public String content;
         public String hashId;
         public int unixtime;
         public String updatetime;
         public String url;
         */
        String createTableSql = "create table " + Globals.PICS_TABLE_NAME + "(" +
                "content text not null," +
                "hashId text not null primary key," +
                "unixtime integer not null," +
                "updatetime text not null," +
                "url text not null" +
                ");";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertPicsIntoDB(List<FunnyPic> list) {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement sqLiteStatement = db.compileStatement("insert into " +
                Globals.PICS_TABLE_NAME +
                "(content,hashId,unixtime,updatetime,url) values(?,?,?,?,?)");
        db.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            if (!isFunnyPicExist(list.get(i))) {
                sqLiteStatement.bindString(1, list.get(i).content);
                sqLiteStatement.bindString(2, list.get(i).hashId);
                sqLiteStatement.bindLong(3, list.get(i).unixtime);
                sqLiteStatement.bindString(4, list.get(i).updatetime);
                sqLiteStatement.bindString(5, list.get(i).url);
                sqLiteStatement.execute();
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public List<FunnyPic> getAllFunnyPics() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllSql = "select * from " + Globals.PICS_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllSql, null);
        List<FunnyPic> list = new ArrayList<>(cursor.getColumnCount());
        while (cursor.moveToNext()) {
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String hashId = cursor.getString(cursor.getColumnIndex("hashId"));
            int unixtime = cursor.getInt(cursor.getColumnIndex("unixtime"));
            String updatetime = cursor.getString(cursor.getColumnIndex("updatetime"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            FunnyPic funnyPic = new FunnyPic(content, hashId, unixtime, updatetime, url);
            list.add(funnyPic);
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean isFunnyPicExist(FunnyPic funnyPic) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = new String[]{funnyPic.hashId};
        Cursor cursor = db.rawQuery("select * from " + Globals.PICS_TABLE_NAME + " where hashId = ?", selectionArgs);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
}
