package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.cloudpage.homeworkFragment;
import com.example.myapplication.listpage.listFragment;
import com.example.myapplication.minepage.mineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private homeworkFragment oneFragment = new homeworkFragment();//<-----作业界面
    private listFragment twoFragment = new listFragment();
    private mineFragment thrFragemnt = new mineFragment();
    private BottomNavigationView mainNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_activity_fragment);
        initView();
        initFragment();
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // moveTaskToBack(false);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.mainContent, oneFragment).add(R.id.mainContent, twoFragment).add(R.id.mainContent, thrFragemnt)
                .hide(oneFragment).hide(twoFragment).hide(thrFragemnt)
                .show(twoFragment).commit();
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
                mainNavigation.setBackgroundResource(R.color.nagvigation);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}


