package com.example.cube;

import static androidx.constraintlayout.widget.ConstraintSet.GONE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    static BottomNavigationView bottomNavigationView;
    static FragmentManager fragmentManager;
    static FragmentTransaction fragmentTransaction;
    static DataBaseHelper DBHelper;
    static boolean isAdmin = false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        isAdmin = ((intent.getIntExtra("userStatus", 0) == 0) ? false : true);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.myList);
        if(!isAdmin){
            bottomNavigationView.getMenu().removeItem(R.id.create);
        }
        fragmentManager = getSupportFragmentManager();
        DBHelper = LoginActivity.DBHelper;
        Fragment fragmenTemplates = new MyCubesFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_view, fragmenTemplates);
        fragmentTransaction.commit();
//        ArrayList<Animation> closedAnims = (ArrayList<Animation>) DBHelper.getClosedCubes();
//        for(Animation anim: closedAnims){
//            Log.i("CLOSED", anim.getName()+" "+anim.getId());
//        }
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.create) {
                    Fragment fragmentCreate = new CreateCubeFragment(new Animation(-1, "", new ArrayList<>()));
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_view, fragmentCreate);
                    fragmentTransaction.commit();
                    return true;
                } else if (item.getItemId() == R.id.patterns) {
                    Fragment fragmenTemplates = new TemplatesFragment();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_view, fragmenTemplates);
                    fragmentTransaction.commit();
                    return true;

                } else if (item.getItemId() == R.id.myList) {
                    Fragment fragmentList = new MyCubesFragment();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_view, fragmentList);
                    fragmentTransaction.commit();
                    return true;

                }

                return false;
            }
        });
    }

}