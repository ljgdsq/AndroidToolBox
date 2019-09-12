package com.example.androidtoolbox.misc;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.LinkedList;

public abstract class EasyAdapter<T> extends BaseAdapter {
//    private Context context;
    private LinkedList<T> dataList;
    private int layoutRes;

    public EasyAdapter(int layoutRes, LinkedList<T> dataList) {
        this.layoutRes = layoutRes;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=ViewHolder.bind(parent.getContext(),convertView,parent,layoutRes,position);
        bindView(viewHolder,getItem(position));
        return viewHolder.getItemView();
    }

    public abstract void bindView(ViewHolder holder, T obj);


    public static class ViewHolder {
        private SparseArray<View> views;
        private View item;
        private int position;
        private Context context;

        public ViewHolder(Context context, ViewGroup parent, int layoutId) {
            views = new SparseArray<>();
            this.context = context;
            View convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            convertView.setTag(this);
            item = convertView;
        }

        public static ViewHolder bind(Context context, View convertView, ViewGroup parent,
                                      int layoutRes, int position) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder(context, parent, layoutRes);

            } else {
                holder = (ViewHolder) convertView.getTag();
                holder.item = convertView;
            }

            holder.position = position;
            return holder;
        }

        public View getView(int id)
        {
            View view = views.get(id);
            if (view==null)
            {
                view=item.findViewById(id);
            }
            return view;
        }


        /**
         * 获取当前条目
         */
        public View getItemView() {
            return item;
        }

        /**
         * 获取条目位置
         */
        public int getItemPosition() {
            return position;
        }

        /**
         * 设置文字
         */
        public ViewHolder setText(int id, CharSequence text) {
            View view = getView(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }

        /**
         * 设置图片
         */
        public ViewHolder setImageResource(int id, int drawableRes) {
            View view = getView(id);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }

        /**
         * 设置点击监听
         */
        public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }

        /**
         * 设置可见
         */
        public ViewHolder setVisibility(int id, int visible) {
            getView(id).setVisibility(visible);
            return this;
        }

        /**
         * 设置标签
         */
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }
    }

}
