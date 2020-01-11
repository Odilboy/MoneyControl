package com.example.moneycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.com.example.adapters.TabAdapter;
import com.example.services.Tab1Service;
import com.google.android.material.tabs.TabLayout;

import com.example.fragments.Tab1Fragment;
import com.example.fragments.Tab2Fragment;
import com.example.fragments.Tab3Fragment;


public class MoneyControl extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static TextView balance;


@Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_control);

        balance = findViewById(R.id.balance);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Activity");
        adapter.addFragment(new Tab2Fragment(), "Expects");
        adapter.addFragment(new Tab3Fragment(), "Debts");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);

    Intent intent = new Intent(this, Tab1Service.class);
    startService(intent);



        
}


}
