package com.gewu.Notepad.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gewu.Notepad.R;


public class oneFragment extends Fragment {


    private RelativeLayout viewById;
    private TextView mouth;
    private TextView years;
    private TextView showCleardr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_one_fragment, container, false);
        initView(inflate);
        return inflate;
    }


    @SuppressLint("ResourceAsColor")
    private void initView(View inflate) {
//         viewById = inflate.findViewById(R.id.tllibar);
//        viewById.setBackgroundResource(R.color.toolabr);
    }
}