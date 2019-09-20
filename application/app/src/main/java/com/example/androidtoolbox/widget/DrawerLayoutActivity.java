package com.example.androidtoolbox.widget;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.entity.Anim;
import com.example.androidtoolbox.fragment.ContentFragment;
import com.example.androidtoolbox.misc.MegaAdapter;
import com.example.androidtoolbox.utils.DisplayUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerLayoutActivity extends AppCompatActivity {


    private static final String TAG = "DrawerLayoutActivity";
    private GestureDetector gestureDetector;
    private ArrayList<Anim> menuLists;

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.lv_content)
    ListView lvContent;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        ButterKnife.bind(this);

        menuLists=new ArrayList<>();
        menuLists.add(new Anim("Dog","",R.drawable.dog));
        menuLists.add(new Anim("Tiger","",R.drawable.tiger));
        menuLists.add(new Anim("Mouse","",R.drawable.mouse));
        menuLists.add(new Anim("Monkey","",R.drawable.monkey));

        MegaAdapter adapter=new MegaAdapter<Anim>(menuLists,R.layout.item_app){
            @Override
            public void bindView(ViewHolder holder, Anim data) {
                holder.setText(R.id.txt_aname,data.getName());
                holder.setImage(R.id.img_icon,data.getIconId(),32);
                ViewGroup.LayoutParams layoutParams = holder.getView(R.id.img_icon).getLayoutParams();
                layoutParams.width= DisplayUtils.dp2px(DrawerLayoutActivity.this,32);
                layoutParams.height= DisplayUtils.dp2px(DrawerLayoutActivity.this,32);
                holder.getView(R.id.img_icon).setLayoutParams(layoutParams);
            }
        };



        lvContent.setAdapter(adapter);

        lvContent.setOnItemClickListener((parent, view, position, id) -> {
            ContentFragment contentFragment = new ContentFragment();
            Bundle args = new Bundle();
            args.putString("text", menuLists.get(position).getName());
            contentFragment.setArguments(args);
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.frame,contentFragment).commit();
            drawerLayout.closeDrawer(lvContent);
        });

        int minFlingDistance=DisplayUtils.dp2px(this,50);
        gestureDetector=new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float xDis=e2.getX()-e1.getX();
                float yDis=Math.abs(e2.getY()-e1.getY());
                if (xDis>minFlingDistance && xDis>yDis)
                {
                    Log.d(TAG, "onFling: open!");
                    drawerLayout.openDrawer(Gravity.LEFT);
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (gestureDetector.onTouchEvent(ev))
        {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
