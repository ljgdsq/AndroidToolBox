package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.entity.Anim;
import com.example.androidtoolbox.misc.EasyAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewAdaptersActivity extends AppCompatActivity {

    private ListView listView1;
    private ListView listView2;
    private ListView listView3;

    private String[]names={"王大锤","李二狗","小明"};
    private String[]descs={"俺是王大锤!","我叫李二狗","我是小明!"};
    private int[]iconIds={R.drawable.man_icon,R.drawable.man_icon2,R.drawable.woman_icon};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_adapters);

        listView1=findViewById(R.id.listView1);
        listView1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
        , Arrays.asList("A","B","C","D")));



        listView2=findViewById(R.id.listView2);
        ArrayAdapter<CharSequence> fromResource = ArrayAdapter.createFromResource(this, R.array.ArrayAdapterData, android.R.layout.simple_list_item_1
        );

        listView2.setAdapter(fromResource);


        listView3=findViewById(R.id.listView3);
        List<Map<String,Object>> datas=new ArrayList<>();

        String[]keys={"name","desc","icon"};

        int[]ids={R.id.title,R.id.desc,R.id.icon};
        for (int i=0;i<names.length;i++)
        {
            Map map=new HashMap();
            map.put(keys[0],names[i]);
            map.put(keys[1],descs[i]);
            map.put(keys[2],iconIds[i]);
            datas.add(map);
        }

        SimpleAdapter simpleAdapter=new SimpleAdapter(this,datas,R.layout.simple_list_item,keys,ids);
        listView3.setAdapter(simpleAdapter);



    }
}
