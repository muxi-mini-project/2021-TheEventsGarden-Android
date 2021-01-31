package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new mineFragment();//从activity_fragment.xml布局里实例化activity视图，此时fragment为crimefragment
    }
}
