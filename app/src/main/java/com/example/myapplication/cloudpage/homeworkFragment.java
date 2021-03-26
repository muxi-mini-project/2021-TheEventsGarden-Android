package com.example.myapplication.cloudpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class homeworkFragment extends Fragment {
    Homework mHomework;
    RecyclerView mRecyclerView;
    HomeworkAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cloud, container, false);
        mHomework = new Homework(getActivity());
        mAdapter = new HomeworkAdapter(getActivity());
        mRecyclerView = view.findViewById(R.id.homework_recycleView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(mHomework.getHomeworkList());
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnitemClickLintener(new HomeworkAdapter.OnitemClick() {
            @Override
            public void onItemClick(int position) {
                mAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }
}
