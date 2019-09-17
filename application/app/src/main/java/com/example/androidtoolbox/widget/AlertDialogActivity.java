package com.example.androidtoolbox.widget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertDialogActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                createDefaultDialog();
                break;
            case R.id.button2:
                createItemListDialog();
                break;
            case R.id.button3:
                createMultichoiceDialog();
                break;
            case R.id.button4:
                break;
        }
    }



    private void createDefaultDialog()
    {
        AlertDialog dialog;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.man_icon)
                .setTitle("默认Dialog")
                .setMessage("这是系统默认的Dialog!")
                .setPositiveButton("确定", (DialogInterface showDialog,int witch)->
                    showDialog.dismiss()
                );

        dialog=builder.create();
        dialog.show();

    }

    private void createItemListDialog()
    {
        final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.man_icon)
                .setTitle("选择学科")
//                .setItems(lesson, (dialog1, which) -> Toast.makeText(AlertDialogActivity.this,lesson[which],Toast.LENGTH_LONG).show())
                .setPositiveButton("确定", (dialog12, which) -> dialog12.dismiss());
        builder.setSingleChoiceItems(lesson, 0, (dialog, which) -> Toast.makeText(AlertDialogActivity.this,lesson[which],Toast.LENGTH_LONG).show());
        AlertDialog dialog=builder.create();
        dialog.setCancelable(false);
                dialog.show();
    }



    private void createMultichoiceDialog(){
        final String[] menu = new String[]{"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
        boolean[]selectedState=new boolean[]{false,false,false,false};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.man_icon)
                .setTitle("选择食物")
                .setPositiveButton("确定", (dialog12, which) -> {
                    dialog12.dismiss();
                });
        builder.setMultiChoiceItems(menu, selectedState, (dialog, which, isChecked) -> selectedState[which]=isChecked);
        AlertDialog dialog=builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
}
