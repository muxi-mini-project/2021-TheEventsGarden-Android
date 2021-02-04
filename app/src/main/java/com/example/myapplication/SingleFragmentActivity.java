package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

//添加一个通用类，用来创建fragment
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment createFragment();//因为是抽象类，使用范围更广，不像之前特指一个fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container_empty);
        //设置从activity_fragment.xml布局里实例化activity视图。然后在容器中查找FragmentManager里的fragment
        if (fragment == null) {
            fragment = createFragment();//不再是直接new一个fragment了
            fm.beginTransaction()
                    .add(R.id.fragment_container_empty, fragment)
                    .commit();
        }
    }
}

