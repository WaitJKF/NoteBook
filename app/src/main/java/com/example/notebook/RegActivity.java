package com.example.notebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dbservice.PersonService;
import entity.Person;
import mydatabase.PersonManager;

public class RegActivity extends AppCompatActivity {

    private EditText et3;
    private EditText et4;
    private Button bt_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        bt_ok = (Button) findViewById(R.id.bt_ok);

    }
     public void saveInfo(View view){
        String name = et3.getText().toString();
        String psw = et4.getText().toString();
         Person p = new Person(name,psw);
         p.setCreateTime();
         Object[] objects = {p.getName(),p.getPsw(),p.getCreateTime()};
         PersonService personService = new PersonManager(this);
         boolean flag = personService.addPerson(objects);
         if (flag){
             Intent it = new Intent(RegActivity.this,LoginActivity.class);
             startActivity(it);
             Toast.makeText(this,"注册成功! 请返回登录",Toast.LENGTH_SHORT).show();
         }else {
             Toast.makeText(this,"注册失败，请重新注册",Toast.LENGTH_SHORT).show();
         }
     }
}
