package com.miguelcr.a01_bottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment f = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    f = new HomeFragment();
                    break;
                case R.id.navigation_dashboard:
                    f = new DashboardFragment();
                    break;
                case R.id.navigation_notifications:
                    f = new NotificationsFragment();
                    break;
            }

            if(f != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerFragment, f)
                        .commit();
                return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Default Fragment to load into the FrameLayout (R.id.containerFragment)
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.containerFragment, new HomeFragment())
                .commit();
    }

}
