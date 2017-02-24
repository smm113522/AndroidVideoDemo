package com.demo.smm.bottomnavigationview;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/2/3.
 */

public class LocationFragment extends Fragment {

    @BindView(R.id.tv)
    TextView tv;
    Unbinder unbinder;
    public String txt;

    public static LocationFragment newInstance(String param1) {
        LocationFragment fragment = new LocationFragment();
        fragment.txt = param1;
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public LocationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");

        tv.setText(txt);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
