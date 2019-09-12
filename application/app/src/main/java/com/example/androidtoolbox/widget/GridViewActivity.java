package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.misc.MegaAdapter;

import java.util.ArrayList;
import java.util.LinkedList;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Icon> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gridView=findViewById(R.id.gridView);

        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.drawable.iv_icon_1, "图标1"));
        mData.add(new Icon(R.drawable.iv_icon_2, "图标2"));
        mData.add(new Icon(R.drawable.iv_icon_3, "图标3"));
        mData.add(new Icon(R.drawable.iv_icon_4, "图标4"));
        mData.add(new Icon(R.drawable.iv_icon_5, "图标5"));
        mData.add(new Icon(R.drawable.iv_icon_6, "图标6"));
        mData.add(new Icon(R.drawable.iv_icon_7, "图标7"));

        gridView.setAdapter(new MegaAdapter<Icon>(new LinkedList<Icon>(mData),R.layout.grid_item) {
            @Override
            public void bindView(ViewHolder holder, Icon data) {
                holder.setImage(R.id.icon,data.getiId());
                holder.setText(R.id.text,data.getiName());

            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity.this,"click "+mData.get(position).getiName(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    public static class Icon {
        private int iId;
        private String iName;

        public Icon() {
        }

        public Icon(int iId, String iName) {
            this.iId = iId;
            this.iName = iName;
        }

        public int getiId() {
            return iId;
        }

        public String getiName() {
            return iName;
        }

        public void setiId(int iId) {
            this.iId = iId;
        }

        public void setiName(String iName) {
            this.iName = iName;
        }
    }
}
