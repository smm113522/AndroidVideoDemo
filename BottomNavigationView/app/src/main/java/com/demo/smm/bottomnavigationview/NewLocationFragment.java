package com.demo.smm.bottomnavigationview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class NewLocationFragment extends Fragment {

    @BindView(R.id.tv)
    TextView tv;
    Unbinder unbinder;
    public String txt;

    public static NewLocationFragment newInstance(String param1) {
        NewLocationFragment fragment = new NewLocationFragment();
        fragment.txt = param1;
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public NewLocationFragment() {

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
        String agrs1 = null;
        if(bundle != null){
            agrs1= bundle.getString("agrs1");
        }else {
            agrs1 = "ddddd";
        }



        tv.setText(agrs1);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
