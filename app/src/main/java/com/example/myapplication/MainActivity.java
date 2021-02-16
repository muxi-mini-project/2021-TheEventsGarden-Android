package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import listpage.listFragment;
import gardenpage.gardenFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    int index = 2;
    private Fragment mFragment;
    private gardenFragment oneFragment = new gardenFragment();
    private listFragment twoFragment = new listFragment();
    private mineFragment thrFragemnt = new mineFragment();
    private BottomNavigationView mainNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_activity_fragment);

        index = getIntent().getIntExtra("choose",2);
        if(index == 2){
           mFragment = twoFragment;
        } else if(index == 3){
            mFragment = thrFragemnt;
        }

        initView();
        initFragment(mFragment);
    }

    /**
     * 初始化fragment
     */
    private void initFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainContent, oneFragment).add(R.id.mainContent, twoFragment).add(R.id.mainContent, thrFragemnt)
                .hide(oneFragment).hide(twoFragment).hide(thrFragemnt)
                .show(fragment).commit();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        FrameLayout mainContent = (FrameLayout) findViewById(R.id.mainContent);
        mainNavigation = (BottomNavigationView) findViewById(R.id.mainNavigation);
        mainNavigation.setOnNavigationItemSelectedListener(this);
        mainNavigation.setSelectedItemId(mainNavigation.getMenu().getItem(1).getItemId());
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.one:
                showFragment(oneFragment);
                mainNavigation.setBackgroundResource(R.color.toolabr);
                break;
            case R.id.two:
                showFragment(twoFragment);
                mainNavigation.setBackgroundResource(R.color.nagvigation);
                break;
            case R.id.thr:
                showFragment(thrFragemnt);
                mainNavigation.setBackgroundResource(R.color.nagvigation);
                break;
        }
        return true;
    }

    /**
     * 显示fragment
     *
     * @param fragment
     */
    private void showFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        List<Fragment> fragments = supportFragmentManager.getFragments();
        for (Fragment f : fragments) {
            fragmentTransaction.hide(f);
        }
        fragmentTransaction.show(fragment).commit();
    }

}


