package com.example.androidtoolbox.widget;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.fragment.LeftFragment;
import com.example.androidtoolbox.fragment.RightFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawerLayout2Activity extends AppCompatActivity {

    Button btnTop;

    @BindView(R.id.fly_content)
    FrameLayout flyContent;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    LeftFragment fgLeftMenu;
    RightFragment fgRightMenu;
    @BindView(R.id.ll)
    LinearLayout ll;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout2);
        View view=findViewById(R.id.include_rl);
        btnTop = view.findViewById(R.id.btn_right);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();


        fgLeftMenu = (LeftFragment) fragmentManager.findFragmentById(R.id.fg_left_menu);
        fgRightMenu = (RightFragment) fragmentManager.findFragmentById(R.id.fg_right_menu);

        fgRightMenu.setDrawerLayout(drawerLayout);
        fgLeftMenu.setDrawerLayout(drawerLayout);

        //设置右面的侧滑菜单只能通过编程来打开
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                drawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }
        });

        btnTop.setOnClickListener(v -> {
            drawerLayout.openDrawer(Gravity.RIGHT);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                    Gravity.RIGHT);    //解除锁定
            Toast.makeText(DrawerLayout2Activity.this, "click " + v.getClass().getCanonicalName(), Toast.LENGTH_LONG).show();

        });
    }

    @OnClick({R.id.fg_left_menu, R.id.fg_right_menu})
    public void onViewClicked(View view) {
        Toast.makeText(DrawerLayout2Activity.this, "click " + view.getClass().getCanonicalName(), Toast.LENGTH_LONG).show();
        switch (view.getId()) {
            case R.id.btn_right:
                drawerLayout.openDrawer(Gravity.RIGHT);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                        Gravity.RIGHT);    //解除锁定
                break;
            case R.id.fg_left_menu:
                break;
            case R.id.fg_right_menu:
                break;
        }
    }


    protected void onTopRightClick(View view)
    {
        Toast.makeText(DrawerLayout2Activity.this, "click " + view.getClass().getCanonicalName(), Toast.LENGTH_LONG).show();

        drawerLayout.openDrawer(Gravity.RIGHT);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.RIGHT);    //解除锁定

    }
}
