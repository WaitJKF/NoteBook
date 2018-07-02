package com.example.notebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mydatabase.NoteManager;

public class ShowNoteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener   {

    private ListView listView;
    private ArrayAdapter adapter;
    private NoteManager noteManager;
    private Button bt_add;
    private List<Map<String ,String >> list;
    private List<String > getNote;
    private String[] strings;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownote);

        listView = (ListView) findViewById(R.id.listView);
        bt_add = (Button) findViewById(R.id.bt_add);

        bundle = this.getIntent().getExtras();
        String uname = bundle.get("name").toString();               // 得到传递过来的用户名
        strings = new String[]{uname};
        getNote = getData(strings);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,getData(strings));
        refreshNotesList();


        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                    //点击添加按钮，跳转到EidtActivity
                boolean bt_flag = true;
                Intent it = new Intent(ShowNoteActivity.this, EditActivity.class);
                bundle.putBoolean("flag",bt_flag);                                //标志着点击添加按钮而跳转
                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }
    private void refreshNotesList(){                                             //刷新列表显示
        int size = getNote.size();
        if (size>0){
            getNote.removeAll(getNote);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
        }
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,getData(strings));
        listView.setAdapter(adapter);

    }
    private List<String> getData(String[] strings){                               //根据用户名得到数据库中的信息
        list = new ArrayList<Map<String,String>>();
        getNote = new ArrayList<String>();
        noteManager = new NoteManager(this);
        list = noteManager.listNote(strings);
        for(int i=0;i<list.size();i++)
        {
            String title= list.get(i).get("title");
            getNote.add(title);
        }
        return getNote;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        boolean flag = false;
        String clickInfo = this.adapter.getItem(i).toString();
        Intent it = new Intent(this,EditActivity.class);
        bundle.putString("title",clickInfo);
        bundle.putBoolean("flag",false);                                                    //标志着是点击某个日记而跳转
        it.putExtras(bundle);
        startActivity(it);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {                              //长按某个日记提示是否删除
        String click = this.adapter.getItem(i).toString();
        final String[] str = {click};
        noteManager = new NoteManager(ShowNoteActivity.this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(ShowNoteActivity.this);
        builder.setTitle("删除").setMessage("是否删除此项");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean re = noteManager.delNote(str);
                if (re){
                    Toast.makeText(ShowNoteActivity.this,"删除成功",Toast.LENGTH_SHORT).show();             //当删除某个日记时，刷新列表显示
                    dialogInterface.dismiss();
                    refreshNotesList();
                }else {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return true;
    }
}
