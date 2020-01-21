package com.example.androidtoolbox;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.androidtoolbox.events.EventActivity;
import com.example.androidtoolbox.jetpack.DataBindingActivity;
import com.example.androidtoolbox.jetpack.LiveDataActivity;
import com.example.androidtoolbox.jetpack.room.RoomDataBaseActivity;
import com.example.androidtoolbox.layout.FrameLayoutActivity;
import com.example.androidtoolbox.layout.GridLayoutActivity;
import com.example.androidtoolbox.layout.LinearLayoutActivity;
import com.example.androidtoolbox.layout.RelativeLayoutActivity;
import com.example.androidtoolbox.layout.TableLayoutActivity;
import com.example.androidtoolbox.misc.AlarmManagerActivity;
import com.example.androidtoolbox.misc.AndroidPathActivity;
import com.example.androidtoolbox.misc.AsyncTaskActivity;
import com.example.androidtoolbox.misc.BitmapActivity;
import com.example.androidtoolbox.misc.ConfigurationActivity;
import com.example.androidtoolbox.misc.DownloadActivity;
import com.example.androidtoolbox.misc.DrawableActivity;
import com.example.androidtoolbox.misc.FragmentTestActivity;
import com.example.androidtoolbox.misc.GestureActivity;
import com.example.androidtoolbox.misc.LooperActivity;
import com.example.androidtoolbox.misc.NetActivity;
import com.example.androidtoolbox.misc.OrderedBroadCastActivity;
import com.example.androidtoolbox.misc.SQLiteActivity;
import com.example.androidtoolbox.misc.ServicesDemoActivity;
import com.example.androidtoolbox.misc.TestAidlActivity;
import com.example.androidtoolbox.misc.TouchActivity;
import com.example.androidtoolbox.misc.TransactionAActivity;
import com.example.androidtoolbox.misc.TransactionBActivity;
import com.example.androidtoolbox.misc.WorkerTestActivity;
import com.example.androidtoolbox.misc.XmlActivity;
import com.example.androidtoolbox.sisterrun.SisterRunActivity;
import com.example.androidtoolbox.test.TestActivity;
import com.example.androidtoolbox.utils.ApplicationUtil;
import com.example.androidtoolbox.utils.LogUtil;
import com.example.androidtoolbox.utils.PermissionUtil;
import com.example.androidtoolbox.widget.AlertDialogActivity;
import com.example.androidtoolbox.widget.AnimListViewActivity;
import com.example.androidtoolbox.widget.ButtonActivity;
import com.example.androidtoolbox.widget.DrawerLayout2Activity;
import com.example.androidtoolbox.widget.DrawerLayoutActivity;
import com.example.androidtoolbox.widget.EditTextActivity;
import com.example.androidtoolbox.widget.GridViewActivity;
import com.example.androidtoolbox.widget.ImageViewActivity;
import com.example.androidtoolbox.widget.ListViewAdaptersActivity;
import com.example.androidtoolbox.widget.ListViewItemClickFocusActivity;
import com.example.androidtoolbox.widget.MenuActivity;
import com.example.androidtoolbox.widget.MultiItemListViewActivity;
import com.example.androidtoolbox.widget.NotificationActivity;
import com.example.androidtoolbox.widget.PainterActivity;
import com.example.androidtoolbox.widget.PopupWindowActivity;
import com.example.androidtoolbox.widget.ProgressBarActivity;
import com.example.androidtoolbox.widget.ScrollViewActivity;
import com.example.androidtoolbox.widget.SeekBarActivity;
import com.example.androidtoolbox.widget.TextViewActivity;
import com.example.androidtoolbox.widget.ToastActivity;
import com.example.androidtoolbox.widget.ViewFlipperActivity;
import com.example.androidtoolbox.widget.ViewPagerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private List<Pair> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtil.requestPermission(this,0, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        PermissionUtil.requestPermission(this,0, Manifest.permission.INTERNET);


        boolean canUseDownloadManager=ApplicationUtil.canUseDownloadManager();

        LogUtil.w("canUseDownloadManager"+canUseDownloadManager);
//        ApplicationUtil.installApk("/sdcard/a.apk");
        /*
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.O)
        {
            startService(new Intent(getApplicationContext(), PersonAidlService.class));

        }else {
            startForegroundService(new Intent(getApplicationContext(), PersonAidlService.class));

        }


        bindService(new Intent(getApplicationContext(), PersonAidlService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    String s=((IPerson)service).queryName(1);
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Service.BIND_AUTO_CREATE);
*/
        //--------------------layout--------------------
        titles.add(new Pair("LinearLayout", new Intent(this, LinearLayoutActivity.class)));
        titles.add(new Pair("RelativeLayout", new Intent(this, RelativeLayoutActivity.class)));
        titles.add(new Pair("TableLayout", new Intent(this, TableLayoutActivity.class)));
        titles.add(new Pair("GridLayout",new Intent(this, GridLayoutActivity.class)));
        titles.add(new Pair("FrameLayout",new Intent(this, FrameLayoutActivity.class)));


        //----------------------widget----------------------
        titles.add(new Pair("TextView",new Intent(this, TextViewActivity.class)));
        titles.add(new Pair("EditText",new Intent(this, EditTextActivity.class)));
        titles.add(new Pair("Button",new Intent(this, ButtonActivity.class)));
        titles.add(new Pair("ImageView",new Intent(this, ImageViewActivity.class)));
        titles.add(new Pair("ProgressBar",new Intent(this, ProgressBarActivity.class)));
        titles.add(new Pair("SeekBar",new Intent(this, SeekBarActivity.class)));
        titles.add(new Pair("ScrollView",new Intent(this, ScrollViewActivity.class)));
        titles.add(new Pair("ListViewAdapter",new Intent(this, ListViewAdaptersActivity.class)));
        titles.add(new Pair("AnimListView",new Intent(this, AnimListViewActivity.class)));
        titles.add(new Pair("ListViewChildClickFocus",new Intent(this, ListViewItemClickFocusActivity.class)));
        titles.add(new Pair("MultiTypeItemList",new Intent(this, MultiItemListViewActivity.class)));
        titles.add(new Pair("GridView",new Intent(this, GridViewActivity.class)));
        titles.add(new Pair("ViewFlipper",new Intent(this, ViewFlipperActivity.class)));
        titles.add(new Pair("Toast",new Intent(this, ToastActivity.class)));
        titles.add(new Pair("Notification",new Intent(this, NotificationActivity.class)));
        titles.add(new Pair("AlertDialog",new Intent(this, AlertDialogActivity.class)));
        titles.add(new Pair("PopupWindow",new Intent(this, PopupWindowActivity.class)));
        titles.add(new Pair("Menu",new Intent(this, MenuActivity.class)));
        titles.add(new Pair("ViewPager",new Intent(this, ViewPagerActivity.class)));
        titles.add(new Pair("EventActivity",new Intent(this, EventActivity.class)));



        titles.add(new Pair("DrawerLayout",new Intent(this, DrawerLayoutActivity.class)));
        titles.add(new Pair("DrawerLayout2",new Intent(this, DrawerLayout2Activity.class)));
        titles.add(new Pair("Test",new Intent(this, TestActivity.class)));


        //----------------------misc----------------------
        titles.add(new Pair("Looper",new Intent(this, LooperActivity.class)));
        titles.add(new Pair("Touch",new Intent(this, TouchActivity.class)));
        titles.add(new Pair("Configuration",new Intent(this, ConfigurationActivity.class)));
        titles.add(new Pair("AsyncTask",new Intent(this, AsyncTaskActivity.class)));
        titles.add(new Pair("Gesture",new Intent(this, GestureActivity.class)));
        titles.add(new Pair("TransactionA",new Intent(this, TransactionAActivity.class)));
        titles.add(new Pair("TransactionB",new Intent(this, TransactionBActivity.class)));
        titles.add(new Pair("ServiceTest",new Intent(this, ServicesDemoActivity.class)));
        titles.add(new Pair("WorkerTest",new Intent(this, WorkerTestActivity.class)));
        titles.add(new Pair("AlarmManager",new Intent(this, AlarmManagerActivity.class)));
        titles.add(new Pair("AIDLTest",new Intent(this, TestAidlActivity.class)));
        titles.add(new Pair("OrderedBroadCast",new Intent(this, OrderedBroadCastActivity.class)));
        titles.add(new Pair("FragmentTest",new Intent(this, FragmentTestActivity.class)));
        titles.add(new Pair("SQLiteTest",new Intent(this, SQLiteActivity.class)));
        titles.add(new Pair("SimpleNetTest",new Intent(this, NetActivity.class)));
        titles.add(new Pair("XmlTest",new Intent(this, XmlActivity.class)));
        titles.add(new Pair("DownLoadManager",new Intent(this, DownloadActivity.class)));
        titles.add(new Pair("AndroidPath",new Intent(this, AndroidPathActivity.class)));


        ///////////////////////////////////    android jetpack ////////////////////////

        addActivity(LiveDataActivity.class);
        addActivity(DataBindingActivity.class);
        addActivity(RoomDataBaseActivity.class);
        ///////////////////////////////////////////////////////////////////////////////

        addActivity(DrawableActivity.class);
        addActivity(BitmapActivity.class);
        addActivity(PainterActivity.class);
        titles.add(new Pair("SisterRun",new Intent(this, SisterRunActivity.class)));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(titles));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void addActivity(Class clazz){
        addActivity(clazz,clazz.getSimpleName());
    }

    private void addActivity(Class clazz,String str)
    {
        titles.add(new Pair(str,new Intent(this,clazz)));

    }
    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
        }


    }

    private static class Pair {
        public String title;
        public Intent intent;

        public Pair(String title, Intent intent) {
            this.title = title;
            this.intent = intent;
        }

    }

    class C1{
        private int i;
    }

    class C2{

        private C1 c1;
        public void c(){
            int i=c1.i;
        }
    }

    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }


    class MySimpleAdapter extends SimpleAdapter{

        public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }
    }


    class MyArrayAdapter extends ArrayAdapter<Pair>{

        public MyArrayAdapter(@NonNull Context context, int resource, @NonNull List<Pair> objects) {
            super(context, resource, objects);
        }
    }
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Pair> dataList;

        public MyAdapter(List<Pair> data) {
            dataList = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item1, viewGroup, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
            myViewHolder.textView.setText(dataList.get(i).title);

            myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(dataList.get(i).intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
