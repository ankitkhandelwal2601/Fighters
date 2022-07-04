package com.example.freedomfighter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.freedomfighter.R;
import com.example.freedomfighter.fragment.AppInfoFragment;
import com.example.freedomfighter.fragment.DashboardFragment;
import com.example.freedomfighter.fragment.MailUsFragment;
import com.example.freedomfighter.fragment.ProfileFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public CoordinatorLayout coordinatorLayout;
    public Toolbar toolbar;
    public FrameLayout frameLayout;
    public NavigationView navigationView;

    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frame);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        openDashboard();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.isChecked())
                {
                    item.setChecked(true);
                }

                switch (item.getItemId()){
                    case R.id.dashboard:
                        //Toast.makeText(MainActivity.this, "Dashboard clicked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new DashboardFragment()).commit();
                        getSupportActionBar().setTitle("Dashboard");
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.profile:
                        //Toast.makeText(MainActivity.this, "Profile Clicked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ProfileFragment()).commit();
                        getSupportActionBar().setTitle("Profile");
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.appInfo:
                        //Toast.makeText(MainActivity.this, "About Us Clicked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new AppInfoFragment()).commit();
                        getSupportActionBar().setTitle("App Info");
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mailUs:
                        //Toast.makeText(MainActivity.this, "Mail Us Clicked", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MailUsFragment()).commit();
                        getSupportActionBar().setTitle("Mail Us");
                        drawerLayout.closeDrawers();
                        break;
                }

                return true;
            }
        });
    }

    private void openDashboard() {
        Fragment f;
        f = new DashboardFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,f);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Dashboard");
        navigationView.setCheckedItem(R.id.dashboard);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}