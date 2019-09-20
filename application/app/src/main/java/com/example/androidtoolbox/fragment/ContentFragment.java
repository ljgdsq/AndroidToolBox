package com.example.androidtoolbox.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.androidtoolbox.R;

public class ContentFragment extends Fragment {

    private TextView textView;

    private int color;

    private String text;


    public ContentFragment(String text, int color) {
        this.color = color;
        this.text = text;
    }

    public ContentFragment() {
        color = -1;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        textView = view.findViewById(R.id.tv_content);
        if (text != null) {
            textView.setText(text);

        } else {
            String text = getArguments().getString("text");
            textView.setText(text);

        }


        if (color != -1) {
            textView.setTextColor(color);
        }

        return view;
    }

}
