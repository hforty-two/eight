package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sharedpreferences.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyDataBaseHelper dbHelper;
    private List<Category> categoryList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dbHelper=new MyDataBaseHelper(this,"library.db",null,2);
        Button createDatabase=(Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        Button addDatabase=(Button) findViewById(R.id.add_data);
        addDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //开始组装数据
                values.put("author","book_author");
                values.put("price","45");
                values.put("pages","111");
                values.put("name","book_name");
                db.insert("Book",null,values);
                /*Intent intent=new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);*/
            }
        });


        Button queryDatabase=(Button) findViewById(R.id.query_data);
        queryDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToFirst()) {
                    do {
                        //遍历cursor对象，取出数据并打印
                        @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                        @SuppressLint("Range") Double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        @SuppressLint("Range") String pages = cursor.getString(cursor.getColumnIndex("pages"));
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book price is " + price);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book name is " + name);
                    }
                    while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        initCategory();
        CategoryAdapter adapter=new CategoryAdapter(MainActivity.this,R.layout.book_item,categoryList);
        ListView listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
    private void initCategory(){
        Category book1=new Category("经济类",1);
        categoryList.add(book1);
        Category book2=new Category("美术类",2);
        categoryList.add(book2);
    }
}