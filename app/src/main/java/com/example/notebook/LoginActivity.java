package com.example.notebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

import mydatabase.PersonManager;

public class LoginActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
    }
    public void toReg(View view){
        Intent it = new Intent(this, RegActivity.class);
        startActivity(it);
    }
    public void toLogin(View view){
        String name = et1.getText().toString();
        String psw = et2.getText().toString();
        if (name.equals("")||psw.equals("")){
            Toast.makeText(this,"请输入用户名和密码",Toast.LENGTH_SHORT).show();
        }else {
            String [] strings = {name};
            PersonManager pm = new PersonManager(this);
            Map<String,String> map = pm.findPerson(strings);
            boolean result = psw.equals(map.get("psw"));
            if (result){
                Intent it = new Intent(this,ShowNoteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                it.putExtras(bundle);
                startActivity(it);
            }else {
                Toast.makeText(this,"用户名或密码错误!请重新输入!",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
