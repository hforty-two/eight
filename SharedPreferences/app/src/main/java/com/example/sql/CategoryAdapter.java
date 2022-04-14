package com.example.sql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
import com.example.sharedpreferences.R;
public class CategoryAdapter extends ArrayAdapter<Category> {

    private int resourceId;
    public CategoryAdapter(Context context, int textViewResourceId, List<Category> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Category category=getItem(position);//获取当前项的Laptop实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView Name=(TextView) view.findViewById(R.id.category_name);
        TextView Code=(TextView) view.findViewById(R.id.category_code);
        Name.setText(category.getCategory_name()+"");
        Code.setText(category.getCategory_code()+"");
        return view;
    }
}
