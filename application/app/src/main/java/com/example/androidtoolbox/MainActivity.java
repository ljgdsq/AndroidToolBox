package com.example.androidtoolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidtoolbox.layout.FrameLayoutActivity;
import com.example.androidtoolbox.layout.GridLayoutActivity;
import com.example.androidtoolbox.layout.LinearLayoutActivity;
import com.example.androidtoolbox.layout.RelativeLayoutActivity;
import com.example.androidtoolbox.layout.TableLayoutActivity;
import com.example.androidtoolbox.sisterrun.SisterRunActivity;
import com.example.androidtoolbox.widget.TextViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private List<Pair> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles.add(new Pair("LinearLayout", new Intent(this, LinearLayoutActivity.class)));
        titles.add(new Pair("RelativeLayout", new Intent(this, RelativeLayoutActivity.class)));
        titles.add(new Pair("TableLayout", new Intent(this, TableLayoutActivity.class)));
        titles.add(new Pair("GridLayout",new Intent(this, GridLayoutActivity.class)));
        titles.add(new Pair("FrameLayout",new Intent(this, FrameLayoutActivity.class)));
        titles.add(new Pair("TextView",new Intent(this, TextViewActivity.class)));

        titles.add(new Pair("SisterRun",new Intent(this, SisterRunActivity.class)));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(titles));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
        }


    }

    private static class Pair {
        public String title;
        public Intent intent;

        public Pair(String title, Intent intent) {
            this.title = title;
            this.intent = intent;
        }

    }

    class C1{
        private int i;
    }

    class C2{

        private C1 c1;
        public void c(){
            int i=c1.i;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Pair> dataList;

        public MyAdapter(List<Pair> data) {
            dataList = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item1, viewGroup, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
            myViewHolder.textView.setText(dataList.get(i).title);

            myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(dataList.get(i).intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
