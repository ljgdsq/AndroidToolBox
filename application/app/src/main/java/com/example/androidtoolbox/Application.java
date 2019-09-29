package com.example.androidtoolbox;

import android.content.Context;

public class Application extends android.app.Application {

   public static Application application;
   public static Application getInstance() {
       return application;
   }


   public static Context getContext(){
       return getInstance().getApplicationContext();
   }

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
}
