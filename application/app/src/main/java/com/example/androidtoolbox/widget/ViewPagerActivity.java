package com.example.androidtoolbox.widget;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.androidtoolbox.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;


    private int[] pages = new int[]{R.layout.page_one, R.layout.page_two, R.layout.page_three};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);


        ArrayList<View> views=new ArrayList<>();
        LayoutInflater inflater=getLayoutInflater();
        for (int i=0;i<pages.length;i++)
        {
            View view = inflater.inflate(pages[i], null);
            views.add(view);
        }

        viewPager.setAdapter(new MyPagerAdapter(views));

    }


    private class MyPagerAdapter extends PagerAdapter {
        private ArrayList<View> pageList;

        public MyPagerAdapter(ArrayList<View> pageList) {
            this.pageList = pageList;
        }

        @Override
        public int getCount() {
            return pages.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = pageList.get(position);
            container.addView(view);
            return view;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = pageList.get(position);
            container.removeView(view);
        }
    }

}
