package com.example.notebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import entity.Note;
import mydatabase.NoteManager;

public class EditActivity extends AppCompatActivity {

    private EditText et_edit;
    private Button bt_add_note;
    private String rtNote;
    private String [] str;

    private Note note;
    private NoteManager noteManager;
    private String et_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        et_edit = (EditText) findViewById(R.id.et_edit);
        bt_add_note = (Button) findViewById(R.id.bt_add_note);
        noteManager = new NoteManager(this);

        et_note = et_edit.getText().toString();

        Bundle bundle = this.getIntent().getExtras();
        boolean getFlag = bundle.getBoolean("flag");
        final String userName = bundle.getString("name");
        str = new String []{userName};

        if(getFlag){
            et_edit.setText("");
        }else{
            String [] strings = {bundle.getString("title")};
            rtNote = rtNote(strings);
            et_edit.setText(rtNote);
        }

        bt_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_note.equals("")){
                    Toast.makeText(EditActivity.this,"请输入内容！",Toast.LENGTH_LONG).show();
                }else{
                    creatNote(userName,et_note);
                    Intent it = new Intent(EditActivity.this,ShowNoteActivity.class);
                    startActivity(it);
                }
            }
        });

    }

    private void creatNote(String userName,String note){
        Note newNote = new Note(userName,note);
        newNote.setCreateTime();
        newNote.setTitle();
        noteManager.addNote(new String []{newNote.getUser(),newNote.getTitle(),newNote.getCreateTime(),newNote.getNoteBook()});

    }
    private String rtNote(String[] title){
        Map<String ,String > map = new HashMap<>();
        map = noteManager.viewNote(title);
        String s = map.get("note");
        return s;
    }
}
