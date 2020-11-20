package com.example.myapplication;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public Toolbar actionBarMain;
    private ViewPager mainViewPager;
    private TabLayout tabLayout;
    private TabsAdapter tabsAdapter;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public void init() {
        actionBarMain = (Toolbar) findViewById(R.id.action_barMain);
        setSupportActionBar(actionBarMain);
        getSupportActionBar().setTitle(R.string.app_name);

        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        mainViewPager.setAdapter(tabsAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutMain);
        tabLayout.setupWithViewPager(mainViewPager);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onStart() {

        if (currentUser == null) {
            Intent welcomeIntent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(welcomeIntent);
            finish();
        }

        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.mainLogOut){
            auth.signOut();
            Intent loginIntent=new Intent(MainActivity.this,WelcomeActivity.class);
            startActivity(loginIntent);
            finish();
        }

        return true;
    }
}