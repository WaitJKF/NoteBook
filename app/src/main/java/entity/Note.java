package entity;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 鞠坤峰 on 2018/3/25.
 */

public class Note  {
    private String  user;
    private String title;
    private String createTime;
    private String noteBook;

    public Note (String user, String noteBook){
        this.user = user;
        this.noteBook = noteBook;
    }
    public void setTitle(){
        if (noteBook.length()<9){
            title = noteBook;
        }else {
            title = noteBook.substring(0,9);
        }
    }

    public void setCreateTime() {
        Date day = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createTime = simpleDateFormat.format(day);
    }

    public String getUser() {
        return user;
    }

    public String getNoteBook() {
        return noteBook;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getTitle(){
        return title;
    }
}

