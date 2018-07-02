package mydatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbservice.PersonService;
import entity.Person;

/**
 * Created by 鞠坤峰 on 2018/3/29.
 */

public class PersonManager implements PersonService {
    private MyDataBase myDataBase;

    public PersonManager(Context context) {
        myDataBase = new MyDataBase(context);
    }

    @Override
    public boolean addPerson(Object[] orgs) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "insert into person(uname,psw,regtime) values(?,?,?)";
            db = myDataBase.getWritableDatabase();
            db.execSQL(sql,orgs);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db!=null){
                db.close();
            }
        }
        return flag;
    }

    @Override
    public boolean delPerson(Object[] orgs) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "delete from person where uname=?";
            db = myDataBase.getWritableDatabase();
            db.execSQL(sql,orgs);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db!=null){
                db.close();
            }
        }
        return flag;
    }

    @Override
    public boolean upPerson(Object[] orgs) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "update person set psw = ? where uname=?";
            db = myDataBase.getWritableDatabase();
            db.execSQL(sql,orgs);
            flag = true;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db != null){
                db.close();
            }
        }
        return flag;
    }

    @Override
    public Map<String, String> findPerson(String[] orgs) {
        SQLiteDatabase db = null;
        Map<String,String> map = new HashMap<String, String>();
        try {
            String sql = "select *from person where uname=?";
            db = myDataBase.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, orgs);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                for (int i=0;i<colums;i++){
                    String cur_name = cursor.getColumnName(i);
                    String cur_val = cursor.getString(cursor.getColumnIndex(cur_name));
                    if (cur_val==null){
                        cur_val="";
                    }
                    map.put(cur_name,cur_val);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db!=null){
                db.close();
            }
        }
        return map;
    }

    @Override
    public List<Map<String, String>> selectPerson(String[] orgs) {
        SQLiteDatabase db = null;
        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        try {
            String sql ="select *from person";
            myDataBase.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql,orgs);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                Map<String,String> map = new HashMap<String, String>();
                for (int i=0;i<colums;i++){
                    String cur_name = cursor.getColumnName(i);
                    String cur_val = cursor.getString(cursor.getColumnIndex(cur_name));
                    if (cur_val==null){
                        cur_val="";
                    }
                    map.put(cur_name,cur_val);
                }
                list.add(map);
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (db!=null){
                db.close();
            }
        }
        return list;
    }
}
