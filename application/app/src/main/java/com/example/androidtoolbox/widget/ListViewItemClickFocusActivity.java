package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidtoolbox.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewItemClickFocusActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_item_click_focus);
        listView=findViewById(R.id.listView);

        List<String> data=new ArrayList<>();
        for (int i=0;i<40;i++)
        {
            data.add("btn"+i);
        }
        listView.setAdapter(new ArrayAdapter<String>(this,R.layout.list_btn_item,R.id.text,data));



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewItemClickFocusActivity.this,"click:"+position,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
