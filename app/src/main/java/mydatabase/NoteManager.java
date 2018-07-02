package mydatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbservice.NoteService;

/**
 * Created by 鞠坤峰 on 2018/3/29.
 */

public class NoteManager implements NoteService {

    private MyDataBase myDataBase;
    public NoteManager (Context context){
        myDataBase = new MyDataBase(context);
    }

    @Override
    public boolean addNote(Object[] orgs) {
        boolean flag = false;
        SQLiteDatabase db = null;
        String sql = "insert into note(user,title,createtime,notebook) values(?,?,?,?)";
        db = myDataBase.getWritableDatabase();
        db.execSQL(sql,orgs);
        flag = true;
/*        try {
            String sql = "insert into note(user,title,createtime,notebook) values(?,?,?,?)";
            db = myDataBase.getWritableDatabase();
            db.execSQL(sql,orgs);
            flag = true;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db!=null){
                db.close();
            }
        }*/
        return flag;
    }

    @Override
    public boolean delNote(Object[] orgs) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "delete from note where title = ?";
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
    public boolean upNote(Object[] orgs) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "update note set notebook=? where title=?";
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
    public Map<String, String> viewNote(String[] str) {
        Map<String,String> map = new HashMap<String, String>();
        SQLiteDatabase db = null;
        try {
            String sql = "select *from note where username = ?";
            db = myDataBase.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql,str);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                for (int i=0; i<colums; i++){
                    String cur_name = cursor.getColumnName(i);
                    String cur_val = cursor.getString(cursor.getColumnIndex(cur_name));
                    if (cur_val==null){
                        cur_val = "";
                    }
                    map.put(cur_name,cur_val);
                }
            }
        }catch (Exception e){

        }finally {
            if (db!=null)
                db.close();
        }
        return map;
    }

    @Override
    public List<Map<String, String>> listNote(String[] str) {
        SQLiteDatabase db = null;
        List<Map<String,String>> list = new ArrayList<Map<String, String>>();
        try {
            String sql = "select *from note where user = ?";
            db = myDataBase.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql,str);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()){
                Map<String,String> map = new HashMap<String, String>();
                for (int i=0; i<colums; i++){
                    String cur_name = cursor.getColumnName(i);
                    String cur_val = cursor.getString(cursor.getColumnIndex(cur_name));
                    if (cur_val==null){
                        cur_val = "";
                    }
                    map.put(cur_name,cur_val);
                }
                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (db!=null)
                db.close();
        }
        return list;
    }
}
