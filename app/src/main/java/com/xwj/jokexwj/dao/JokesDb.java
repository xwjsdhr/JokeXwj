package com.xwj.jokexwj.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.xwj.jokexwj.model.joke.Joke;
import com.xwj.jokexwj.utils.Globals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwjsd on 2016-02-13.
 */
public class JokesDb extends SQLiteOpenHelper {
    public JokesDb(Context context, int version) {
        super(context, Globals.DB_JOKE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "create table " + Globals.JOKE_TABLE_NAME + "(" +
                "content text not null," +
                "hashid text not null primary key ," +
                "unixtime integer not null," +
                "updatetime text not null" +
                ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertJokesIntoDB(List<Joke> list) {
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteStatement sqLiteStatement = db.compileStatement("insert into " +
                Globals.JOKE_TABLE_NAME +
                "(content,hashid,unixtime,updatetime) values(?,?,?,?)");
        db.beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            if (!isJokeExist(list.get(i))) {
                sqLiteStatement.bindString(1, list.get(i).content);
                sqLiteStatement.bindString(2, list.get(i).hashId);
                sqLiteStatement.bindLong(3, list.get(i).unixtime);
                sqLiteStatement.bindString(4, list.get(i).updatetime);
                sqLiteStatement.execute();
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public List<Joke> getAllJokes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAllSql = "select * from " + Globals.JOKE_TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllSql, null);
        List<Joke> list = new ArrayList<>(cursor.getColumnCount());
        while (cursor.moveToNext()) {
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String hashId = cursor.getString(cursor.getColumnIndex("hashid"));
            int unixtime = cursor.getInt(cursor.getColumnIndex("unixtime"));
            String updatetime = cursor.getString(cursor.getColumnIndex("updatetime"));
            Joke joke = new Joke(content, hashId, unixtime, updatetime);
            list.add(joke);
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean isJokeExist(Joke joke) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = new String[]{joke.hashId};
        Cursor cursor = db.rawQuery("select * from " + Globals.JOKE_TABLE_NAME + " where hashid = ?", selectionArgs);
        if (cursor.moveToNext()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }
}
