package mydatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 鞠坤峰 on 2018/3/22.
 */

public class MyDataBase extends SQLiteOpenHelper {

    public static final String BASE_NAME = "MyDatabase.db";
    public static final String TABLE_INFO = "CREATE TABLE person(" +
            "id integer primary key autoincrement, " +
            "uname vachar(255), " +
            "psw varchar(255)," +
            "regtime varchar(255)" +
            ");";
    public static final String TABLE_NOTE = "CREATE TABLE note(" +
            "id integer primary key autoincrement, " +
            "user varchar(255), " +
            "title varchar(255),"+
            "createtime varchar(255), " +
            "notebook text" +
            ");";

    public MyDataBase(Context context){
        super(context, BASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_INFO);
        sqLiteDatabase.execSQL(TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
