package com.example.androidtoolbox.widget;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {
    //1.定义不同颜色的菜单项的标识:
    final private int RED = 110;
    final private int GREEN = 111;
    final private int BLUE = 112;
    final private int YELLOW = 113;
    final private int GRAY = 114;
    final private int CYAN = 115;
    final private int BLACK = 116;
    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);


//        registerForContextMenu(textView);

        textView.setOnClickListener(v->{
            if (actionMode != null) { //avoid duplicated create
                return;
            }

            // Start the CAB using the ActionMode.Callback defined above
            actionMode = this.startActionMode(actionModeCallback);
            textView.setSelected(true);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//
//        menu.add(1, RED, 4, "红色");
//        menu.add(1, GREEN, 2, "绿色");
//        menu.add(1, BLUE, 3, "蓝色");
//        menu.add(1, YELLOW, 1, "黄色");
//        menu.add(1, GRAY, 5, "灰色");
//        menu.add(1, CYAN, 6, "蓝绿色");
//        menu.add(1, BLACK, 7, "黑色");

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case RED:
                textView.setTextColor(Color.RED);
                break;
            case GREEN:
                textView.setTextColor(Color.GREEN);
                break;
            case BLUE:
                textView.setTextColor(Color.BLUE);
                break;
            case YELLOW:
                textView.setTextColor(Color.YELLOW);
                break;
            case GRAY:
                textView.setTextColor(Color.GRAY);
                break;
            case CYAN:
                textView.setTextColor(Color.CYAN);
                break;
            case BLACK:
                textView.setTextColor(Color.BLACK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.menu_color,menu);
//        new MenuInflater(this).inflate(R.menu.menu_color,menu);
        new MenuInflater(this).inflate(R.menu.menu_sub, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.blue:
                textView.setTextColor(Color.BLUE);
                break;
            case R.id.yellow:
                textView.setTextColor(Color.YELLOW);
                break;
            case R.id.red:
                textView.setTextColor(Color.RED);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
//        PopupMenu popupMenu=new PopupMenu(this,button);
//        Menu menu = popupMenu.getMenu();
//        popupMenu.getMenuInflater().inflate(R.menu.menu_pop,menu);
//        popupMenu.show();



    }

    public void onSmallPigClick(MenuItem menuItem)
    {
        Toast.makeText(MenuActivity.this,"small pig",Toast.LENGTH_LONG).show();
    }

    private ActionMode actionMode;
    private ActionMode.Callback actionModeCallback=new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_color,menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;  // Return false if nothing is done
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.red:
//                    shareCurrentItem();
                    textView.setTextColor(Color.RED);
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
}
