package com.example.androidtoolbox.misc;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtoolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class TouchActivity extends AppCompatActivity {

    private static final String TAG = TouchActivity.class.getSimpleName();
    @BindView(R.id.imageView)
    ImageView imageView;

    public static final int MODE_DEFAULT = 0;
    public static final int MODE_ZOOM = 1;
    public static final int MODE_DRAG = 2;

    private int mode = MODE_DEFAULT;

    private float dis = 1;

//    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    private PointF lastPoint =new PointF();
    private PointF middlePoint=new PointF();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        ButterKnife.bind(this);


    }

    @OnTouch(R.id.imageView)
    void onImageTouch(View view, MotionEvent event) {
        Log.d(TAG, "onImageTouch: "+event.getActionMasked());
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mode = MODE_DRAG;
            {
                savedMatrix.set(imageView.getImageMatrix());
                lastPoint.set(event.getX(),event.getY());
            }
            break;
            case MotionEvent.ACTION_POINTER_DOWN: {
                dis = distance(event);
                middlePoint=middle(event);
                mode = MODE_ZOOM;
            }
            break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: {
                mode = MODE_DEFAULT;
            }
            break;


            case MotionEvent.ACTION_MOVE: {
                if (mode==MODE_DRAG)
                {
                    savedMatrix.postTranslate(event.getX()- lastPoint.x,event.getY()- lastPoint.y);
                    lastPoint.set(event.getX(),event.getY());
                }else if(mode==MODE_ZOOM)
                {
                    float newDis=distance(event);
                    float scale=newDis/dis;
                    savedMatrix.postScale(scale,scale,middlePoint.x,middlePoint.y);
                    dis=newDis;
                }
                imageView.setImageMatrix(savedMatrix);
            }
            break;
        }

    }


    private float distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private PointF middle(MotionEvent event) {
        return new PointF((event.getX(0) + event.getX(1) / 2), (event.getY(0) + event.getY(1)) / 2);
    }


}
