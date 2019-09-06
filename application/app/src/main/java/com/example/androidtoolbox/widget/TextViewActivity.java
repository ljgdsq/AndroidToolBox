package com.example.androidtoolbox.widget;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.example.androidtoolbox.R;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);


        TextView textView=findViewById(R.id.tv_drawable);

        Drawable[] compoundDrawables = textView.getCompoundDrawables();

        if (compoundDrawables[0]!=null){
            compoundDrawables[0].setBounds(0,0,250,300);
        }


        TextView textViewSpannable=findViewById(R.id.tv_spannable);

        SpannableString spannableString=new SpannableString("颜色邮件斜体删除下划线图片");
        spannableString.setSpan(new ForegroundColorSpan(Color.GREEN),0,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new URLSpan("tel:1008611"),2,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textViewSpannable.setMovementMethod(LinkMovementMethod.getInstance());
        spannableString.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),4,6,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StrikethroughSpan(),6,8,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new UnderlineSpan(),8,11,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


//        spannableString.setSpan(new ImageSpan(this,R.drawable.icon_edit),11,12,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        textViewSpannable.setText(spannableString);

        TextView textViewMarquee=findViewById(R.id.tv_marquee);
        textViewMarquee.setTextIsSelectable(true);
        textViewMarquee.setSelected(true);
        setTextMarquee(textViewMarquee);
    }

    public static void setTextMarquee(TextView textView) {
        if (textView != null) {
            textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textView.setSingleLine(true);
            textView.setSelected(true);
            textView.setFocusable(true);
            textView.setFocusableInTouchMode(true);
        }
    }
}
