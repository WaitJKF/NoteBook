package entity;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 鞠坤峰 on 2018/3/25.
 */

public class Person {
    private String name;
    private String psw;
    private String createTime;

    public Person(String name, String psw){
        this.name = name;
        this.psw = psw;
    }

    public void setCreateTime() {
        Date day = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createTime = simpleDateFormat.format(day);
    }


    public String getName(){
        return name;
    }

    public String getPsw() {
        return psw;
    }

    public String getCreateTime() {
        return createTime;
    }


}
