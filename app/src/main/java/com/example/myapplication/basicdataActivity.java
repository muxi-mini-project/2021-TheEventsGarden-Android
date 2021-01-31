package com.example.myapplication;

import androidx.fragment.app.Fragment;

public class basicdataActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new basicdataFragment();//从activity_fragment.xml布局里实例化activity视图，此时fragment为crimefragment
    }
}
