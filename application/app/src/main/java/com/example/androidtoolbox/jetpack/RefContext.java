package com.example.androidtoolbox.jetpack;

import android.content.Context;

public class RefContext {
    private Context context;

    public RefContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
