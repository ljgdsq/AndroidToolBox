package com.example.androidtoolbox.widget;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AutoCompleteActivity extends AppCompatActivity {


    @BindView(R.id.auto_complete_text)
    AutoCompleteTextView autoCompleteText;
    @BindView(R.id.multi_auto_complete_text)
    MultiAutoCompleteTextView multiAutoCompleteText;

    private static final String[] data = new String[]{
            "小猪猪", "小狗狗", "小鸡鸡", "小猫猫", "小咪咪"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);
        ButterKnife.bind(this);

        autoCompleteText.setCompletionHint("请输入:");
        autoCompleteText.setThreshold(1);
        autoCompleteText.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,android.R.id.text1,data));


        multiAutoCompleteText.setThreshold(1);
        multiAutoCompleteText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        multiAutoCompleteText.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,data));
    }
}
