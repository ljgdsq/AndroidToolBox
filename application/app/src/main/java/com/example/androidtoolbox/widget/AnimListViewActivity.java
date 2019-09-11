package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.entity.Anim;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class AnimListViewActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_list_view);

        listView = findViewById(R.id.listView);
        final LinkedList<Anim> data = new LinkedList<>();
        data.add(new Anim("Dog", "This is a Dog!", R.drawable.dog));
        data.add(new Anim("Tiger", "This is a Tiger!", R.drawable.tiger));
        data.add(new Anim("Sheep", "This is a Sheep!", R.drawable.sheep));
        data.add(new Anim("Pig", "This is a Pig!", R.drawable.pig));
        data.add(new Anim("Dog", "This is a Dog!", R.drawable.dog));
        data.add(new Anim("Tiger", "This is a Tiger!", R.drawable.tiger));
        data.add(new Anim("Sheep", "This is a Sheep!", R.drawable.sheep));
        data.add(new Anim("Pig", "This is a Pig!", R.drawable.pig));
        data.add(new Anim("Dog", "This is a Dog!", R.drawable.dog));
        data.add(new Anim("Tiger", "This is a Tiger!", R.drawable.tiger));
        data.add(new Anim("Sheep", "This is a Sheep!", R.drawable.sheep));
        data.add(new Anim("Pig", "This is a Pig!", R.drawable.pig));

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View headerView = layoutInflater.inflate(R.layout.view_list_header, listView, false);
        View footerView = layoutInflater.inflate(R.layout.view_list_footer, listView, false);

        listView.addHeaderView(headerView);
        listView.addFooterView(footerView);

        listView.setAdapter(new AnimAdapter(this, data));

        listView.setHeaderDividersEnabled(true);
        listView.setFooterDividersEnabled(true);
//        listView.setCacheColorHint(Color.GREEN);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 | position == data.size() + 1) return;
                Toast.makeText(AnimListViewActivity.this, data.get(position - 1).getName(), Toast.LENGTH_SHORT).show();
            }
        });

//        listView.setStackFromBottom(true);
    }

    private static class AnimAdapter extends BaseAdapter {
        private Context context;
        private LinkedList<Anim> datas;

        public AnimAdapter(Context context, LinkedList<Anim> datas) {
            this.context = context;
            this.datas = datas;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.simple_list_item, parent, false);
                viewHolder.icon = convertView.findViewById(R.id.icon);
                viewHolder.name = convertView.findViewById(R.id.title);
                viewHolder.desc = convertView.findViewById(R.id.desc);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.icon.setBackgroundResource(datas.get(position).getIconId());
            viewHolder.name.setText(datas.get(position).getName());
            viewHolder.desc.setText(datas.get(position).getDesc());

            return convertView;
        }


    }

    private static class ViewHolder {
        TextView name;
        TextView desc;
        ImageView icon;
    }
}
