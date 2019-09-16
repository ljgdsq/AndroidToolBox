package com.example.androidtoolbox.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.androidtoolbox.R;

public class ViewFlipperActivity extends AppCompatActivity {

    private ViewFlipper flipper;

    private static final int FLIPPER_TYPE = 2;//1 or 2

    private static final int MIN_MOVE=10;
    private GestureDetector gestureDetector; //直接使用touch 的话有个抖动的问题(添加个bool 值判断下可以解决) 这里直接使用手势探测

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_flipper);

        if (FLIPPER_TYPE == 1) {
            CreateStaticViewFlipper();
        } else {
            CreateDynamicViewFlipper();
        }
    }

    private void CreateStaticViewFlipper() {
        flipper = findViewById(R.id.viewFlipper1);
        flipper.startFlipping();
        flipper.setVisibility(View.VISIBLE);

    }

    private void CreateDynamicViewFlipper() {
        flipper = findViewById(R.id.viewFlipper2);
        flipper.setVisibility(View.VISIBLE);

        int[] imageIds={R.drawable.ic_help_view_1,
                R.drawable.ic_help_view_2,
                R.drawable.ic_help_view_3
        };
//        new FrameLayout.LayoutParams(this)
        for (int i=0;i<imageIds.length;i++)
        {
            ImageView imageView=new ImageView(this);
//            layoutParams.height=flipper.getHeight();
//            layoutParams.width=flipper.getWidth();

            imageView.setImageResource(imageIds[i]);
            flipper.addView(imageView);
        }


        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                if (e1.getX() - e2.getX() > MIN_MOVE) {
                    flipper.setInAnimation(ViewFlipperActivity.this, R.anim.right_page_in);
                    flipper.setOutAnimation(ViewFlipperActivity.this, R.anim.right_page_out);
                    flipper.showNext();
                } else if (e2.getX() - e1.getX() > MIN_MOVE) {
                    flipper.setInAnimation(ViewFlipperActivity.this, R.anim.left_page_in);
                    flipper.setOutAnimation(ViewFlipperActivity.this, R.anim.left_page_out);
                    flipper.showPrevious();
                }
                return true;
            }



        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return gestureDetector.onTouchEvent(event);
    }
}
