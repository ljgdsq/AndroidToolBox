package com.example.androidtoolbox.widget;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;
import com.example.androidtoolbox.misc.MegaAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpinnerActivity extends AppCompatActivity {

    @BindView(R.id.spinner_level)
    Spinner spinnerLevel;
    private ArrayList<Hero> heroes;

    @BindView(R.id.spinner_hero)
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.bind(this);

        heroes = new ArrayList<>();
        heroes.add(new Hero(R.drawable.iv_lol_icon1, "迅捷斥候：提莫（Teemo）"));
        heroes.add(new Hero(R.drawable.iv_lol_icon2, "诺克萨斯之手：德莱厄斯（Darius）"));
        heroes.add(new Hero(R.drawable.iv_lol_icon3, "无极剑圣：易（Yi）"));
        heroes.add(new Hero(R.drawable.iv_lol_icon4, "德莱厄斯：德莱文（Draven）"));
        heroes.add(new Hero(R.drawable.iv_lol_icon5, "德邦总管：赵信（XinZhao）"));
        heroes.add(new Hero(R.drawable.iv_lol_icon6, "狂战士：奥拉夫（Olaf）"));

        MegaAdapter<Hero> heroAdapter = new MegaAdapter<Hero>(heroes, R.layout.spinner_hero_item) {
            @Override
            public void bindView(ViewHolder holder, Hero data) {
                holder.setText(R.id.text, data.gethName());
                holder.setImage(R.id.icon, data.gethIcon());
            }
        };

        spinner.setAdapter(heroAdapter);




    }

    private static class Hero {

        private int hIcon;
        private String hName;

        public Hero() {
        }

        public Hero(int hIcon, String hName) {
            this.hIcon = hIcon;
            this.hName = hName;
        }

        public int gethIcon() {
            return hIcon;
        }

        public String gethName() {
            return hName;
        }

        public void sethIcon(int hIcon) {
            this.hIcon = hIcon;
        }

        public void sethName(String hName) {
            this.hName = hName;
        }
    }
}
