package com.gewu.Notepad.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gewu.Notepad.R;
import com.gewu.Notepad.activity.twoActivity;

public class twoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int i = 0;

    public void add() {
        this.i++;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.two_item, parent, false);
        return new Items(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        ((Items) holder).action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), twoActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return i;
    }

    private class Items extends RecyclerView.ViewHolder {
        private Button action;

        public Items(View inflate) {
            super(inflate);
            action = (Button) inflate.findViewById(R.id.action);
        }
    }
}
