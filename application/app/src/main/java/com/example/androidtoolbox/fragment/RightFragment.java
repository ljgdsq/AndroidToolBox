package com.example.androidtoolbox.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.androidtoolbox.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RightFragment extends Fragment {


    private Unbinder unbinder;

    private DrawerLayout drawerLayout;

    private FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_right, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentManager=getActivity().getSupportFragmentManager();

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three})
    public void onViewClicked(View view) {
        Toast.makeText(getActivity(),"show",Toast.LENGTH_LONG).show();
        switch (view.getId()) {
            case R.id.btn_one:
                fragmentManager.beginTransaction().replace(R.id.fly_content,new ContentFragment("item1", Color.BLUE)).commit();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.btn_two:
                fragmentManager.beginTransaction().replace(R.id.fly_content,new ContentFragment("item2", Color.YELLOW)).commit();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.btn_three:
                fragmentManager.beginTransaction().replace(R.id.fly_content,new ContentFragment("item3", Color.GRAY)).commit();
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }
}
