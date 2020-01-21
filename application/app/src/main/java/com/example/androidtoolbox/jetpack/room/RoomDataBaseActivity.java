package com.example.androidtoolbox.jetpack.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import android.os.Build;
import android.os.Bundle;

import com.example.androidtoolbox.R;

import java.util.List;
public class RoomDataBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_data_base);

        UserDataBase dataBase= Room.databaseBuilder(this, UserDataBase.class,"UserDb").allowMainThreadQueries().build();

        UserDao userDao = dataBase.getUserDao();
        userDao.insertUser(new User("老王",18),new User("tom",20));

        List<User> userList = userDao.getAll();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            userList.forEach((it)-> System.out.println(it));
        }

        User user = userList.get(0);
        user.setAge(99);

        int n=userDao.updateUser(user);
        System.out.println(n);
        System.out.println(user);


        int i = userDao.deleteUser(user);
        System.out.println(i);


        n = userDao.deleteAll();
        System.out.println(n);

        userList = userDao.getAll();
        System.out.println(userList.size());
    }



}
