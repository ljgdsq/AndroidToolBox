package com.example.androidtoolbox.misc;

import android.provider.BaseColumns;

public final  class UserRecords {
   private UserRecords(){}

   public static class UserEntry implements BaseColumns {
       public static final String TABLE_NAME="p_user";
       public static final String USER_NAME="user_name";
       public static final String USER_PWD="password";
   }
}
