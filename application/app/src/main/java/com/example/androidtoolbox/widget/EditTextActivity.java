package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import com.example.androidtoolbox.R;

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        EditText editText=findViewById(R.id.et_name);
        editText.setRawInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        editText.requestFocus();

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
//            InputMethodManager inputMethodManager=getSystemService(InputMethodManager.class);
//            inputMethodManager.toggleSoftInput(InputMethodManager.RESULT_UNCHANGED_SHOWN,InputMethodManager.HIDE_NOT_ALWAYS);
//        }

//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(editText,InputMethodManager.SHOW_FORCED);
//        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0); //强制隐藏键盘
    }
}
