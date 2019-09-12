package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtoolbox.R;

import java.util.ArrayList;

public class MultiItemListViewActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item_list_view);
        listView=findViewById(R.id.listView);
        dataList=new ArrayList();


        for(int i = 0;i < 20;i++){
            switch ((int)(Math.random() * 2)){
                case MultiItemAdapter.TYPE_BOOK:
                    dataList.add(new Book("《第一行代码》","郭霖"));
                    break;
                case MultiItemAdapter.TYPE_APP:
                    dataList.add(new App("百度",R.drawable.iv_icon_baidu));
                    break;
            }
        }

        MultiItemAdapter adapter = new MultiItemAdapter(this,dataList);
        listView.setAdapter(adapter);

    }


    private class MultiItemAdapter extends BaseAdapter {

        private static final int TYPE_BOOK = 0;
        private static final int TYPE_APP = 1;

        private Context context;
        private ArrayList dataList;

        public MultiItemAdapter(Context context, ArrayList dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int type = getItemViewType(position);
            BookViewHolder bookViewHolder;
            AppViewHolder appViewHolder;

            if (convertView == null) {
                switch (type) {
                    case TYPE_APP:
                        appViewHolder = new AppViewHolder();
                        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
                        appViewHolder.icon=convertView.findViewById(R.id.img_icon);
                        appViewHolder.name=convertView.findViewById(R.id.txt_aname);
                        convertView.setTag(appViewHolder);
                        break;
                    case TYPE_BOOK:
                        bookViewHolder = new BookViewHolder();
                        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
                        bookViewHolder.name=convertView.findViewById(R.id.txt_bname);
                        bookViewHolder.author=convertView.findViewById(R.id.txt_bauthor);
                        convertView.setTag(bookViewHolder);
                        break;
                }
            } else {
                Object holder = convertView.getTag();
                if (holder instanceof BookViewHolder) {
                    Book book = (Book) dataList.get(position);
                    ((BookViewHolder) holder).author.setText(book.author);
                    ((BookViewHolder) holder).name.setText(book.name);

                } else {
                    App app = (App) dataList.get(position);
                    ((AppViewHolder) holder).name.setText(app.name);
                    ((AppViewHolder) holder).icon.setImageResource(app.imageId);
                }
            }
            return convertView;
        }

        @Override
        public int getItemViewType(int position) {
            if (dataList.get(position) instanceof App) {
                return TYPE_APP;
            } else if (dataList.get(position) instanceof Book) {
                return TYPE_BOOK;
            }

            return super.getItemViewType(position);
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }
    }

    private static class BookViewHolder {
        public TextView name;
        public TextView author;


    }

    private static class AppViewHolder {
        public TextView name;
        public ImageView icon;
    }

    private static class App {
        private String name;
        private int imageId;

        public App(String name, int imageId) {
            this.name = name;
            this.imageId = imageId;
        }
    }

    private static class Book {
        private String name;
        private String author;

        public Book(String name, String author) {
            this.name = name;
            this.author = author;
        }
    }
}
