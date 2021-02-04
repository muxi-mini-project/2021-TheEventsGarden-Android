package gardenpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import gardenpage.bagActivity;
import gardenpage.shopActivity;

public class gardenFragment extends Fragment {
    private ImageView shop,bag,flower,pick;//商店，背包，花朵，采摘
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_garden, container, false);
        shop = (ImageView) v.findViewById(R.id.iv_garden_shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), shopActivity.class);
               // startActivity(intent);//跳转到商店
            }
        });

        bag= (ImageView) v.findViewById(R.id.iv_garden_bag);
        bag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent intent = new Intent(getActivity(), bagActivity.class);
                    //startActivity(intent);//跳转到商店
                }
            });

        return v;

    }
}
