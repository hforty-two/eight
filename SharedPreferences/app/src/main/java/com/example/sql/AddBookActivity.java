package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sharedpreferences.R;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddBookActivity extends AppCompatActivity {
    private MyDataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_item);
        Intent intent=getIntent();

        dbHelper=new MyDataBaseHelper(this,"library.db",null,2);
        Button addDatabase=(Button) findViewById(R.id.add_data);
        addDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //开始组装数据
                values.put("name","book_author");
                values.put("price",45);
                values.put("pages",111);
                values.put("name","book_name");
                db.insert("Book",null,values);
            }
        });
    }
}