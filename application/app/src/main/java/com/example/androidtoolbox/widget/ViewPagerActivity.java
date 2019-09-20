package com.example.androidtoolbox.widget;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.androidtoolbox.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_tweo)
    TextView tvTweo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.pager_tab)
    LinearLayout pagerTab;
    @BindView(R.id.iv_line)
    ImageView ivLine;

//    @Nullable
//    @BindView(R.id.pageTitleStrip)
//    PagerTitleStrip pageTitleStrip;

//    @Nullable
//    @BindView(R.id.pageTabStrip)
//    PagerTabStrip pageTabStrip;

    private int onePageOffset;
    private int currentIndex=0;
    private int[] pages = new int[]{R.layout.page_one, R.layout.page_two, R.layout.page_three};
    private String[] titles = new String[]{"Page1", "Page2", "Page3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);


        ArrayList<View> views = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < pages.length; i++) {
            View view = inflater.inflate(pages[i], null);
            views.add(view);
        }

        MyPagerAdapter adapter = new MyPagerAdapter(views);

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        tvOne.setClickable(true);
        tvOne.setOnClickListener(v->{
            viewPager.setCurrentItem(0,true);
        });
        tvTweo.setClickable(true);

        tvTweo.setOnClickListener(v->{
            viewPager.setCurrentItem(1,true);
        });
        tvThree.setOnClickListener(v->{
            viewPager.setCurrentItem(2,true);
        });
        tvThree.setClickable(true);

        int decoLineWidth= BitmapFactory.decodeResource(getResources(),R.drawable.line).getWidth();
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth=dm.widthPixels;

        int offset=(screenWidth/3-decoLineWidth)/2;
        Matrix matrix=new Matrix();
        matrix.postTranslate(offset,0);
        onePageOffset=offset*2+decoLineWidth;

        ivLine.setImageMatrix(matrix);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Animation animation;
                animation=new TranslateAnimation(currentIndex*onePageOffset,position*onePageOffset,0,0);
                currentIndex=position;
                animation.setFillAfter(true);
                animation.setDuration(500);
                ivLine.startAnimation(animation);
                Toast.makeText(ViewPagerActivity.this,"Selected "+position+1,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
