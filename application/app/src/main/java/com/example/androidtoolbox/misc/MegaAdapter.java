package com.example.androidtoolbox.misc;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class MegaAdapter<T> extends BaseAdapter {

    private ArrayList<T> dataList;
    private int resId;

    public MegaAdapter(ArrayList<T> dataList, int resId) {
        this.dataList = dataList;
        this.resId = resId;
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
        ViewHolder holder;
        if (convertView == null) {
            // create holder
            convertView= LayoutInflater.from(parent.getContext()).inflate(resId,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        bindView(holder, dataList.get(position));
        // set value


        return convertView;
    }

    public abstract void bindView(ViewHolder holder, T data);


    public static class ViewHolder {
        private SparseArray<View> subViews;
        private View itemView;

        public ViewHolder(View view) {
            subViews = new SparseArray<>();
            this.itemView = view;
        }

        public void setText(int viewId, String text) {
            View view = getView(viewId);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
        }

        public void setImage(int viewId, int imageId) {
            View view = getView(viewId);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(imageId);
            } else {
                view.setBackgroundResource(imageId);
            }
        }


        public View getView(int viewId) {
            if (subViews.get(viewId) == null) {
                View subView = itemView.findViewById(viewId);
                if (subView != null) {
                    subViews.put(viewId, subView);
                }
                return subView;
            } else {
                return subViews.get(viewId);
            }
        }
    }
}
