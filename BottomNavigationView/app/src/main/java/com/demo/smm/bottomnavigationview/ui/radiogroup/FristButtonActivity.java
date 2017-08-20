package com.demo.smm.bottomnavigationview.ui.radiogroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.demo.smm.bottomnavigationview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2017/2/8.
 * <p>
 * https://github.com/Kaopiz/android-segmented-control
 * 使用第三方控件
 */

public class FristButtonActivity extends AppCompatActivity {

    @BindView(R.id.rb_kh_all)
    RadioButton rbKhAll;
    @BindView(R.id.rb_kh_bb)
    RadioButton rbKhBb;
    @BindView(R.id.rb_kh_dk)
    RadioButton rbKhDk;
    @BindView(R.id.rb_kh_rg)
    RadioButton rbKhRg;
    @BindView(R.id.rb_kh_qy)
    RadioButton rbKhQy;

    @BindView(R.id.rl_top1)
    RelativeLayout rlTop1;
    @BindView(R.id.rl_top2)
    RelativeLayout rlTop2;
    @BindView(R.id.mRb_home)
    RadioButton mRbHome;
    @BindView(R.id.mRb_message)
    RadioButton mRbMessage;
    @BindView(R.id.mRb_find)
    RadioButton mRbFind;
    @BindView(R.id.mRb_my)
    RadioButton mRbMy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstbtitton);
        ButterKnife.bind(this);
//        addBadgeAt(5);
    }

    private Badge addBadgeAt(int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(12, 2, true)
                .bindTarget(mRbMy);
//                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
//                    @Override
//                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
//                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
//                            Toast.makeText(FristButtonActivity.this, "dddd", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
}
