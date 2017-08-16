package com.demo.smm.bottomnavigationview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/3.
 */

public class AllBottonActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.mode_fixed)
    CheckBox modeFixed;
    @BindView(R.id.mode_shifting)
    CheckBox modeShifting;
    @BindView(R.id.bg_static)
    CheckBox bgStatic;
    @BindView(R.id.bg_ripple)
    CheckBox bgRipple;
    @BindView(R.id.items_3)
    CheckBox items3;
    @BindView(R.id.items_4)
    CheckBox items4;
    @BindView(R.id.items_5)
    CheckBox items5;
    @BindView(R.id.auto_hide)
    CheckBox autoHide;
    @BindView(R.id.toggle_hide)
    Button toggleHide;
    @BindView(R.id.toggle_badge)
    Button toggleBadge;
    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.scrollable_text)
    TextView scrollableText;
    @BindView(R.id.nested_scroll_child)
    LinearLayout nestedScrollChild;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.fab_home)
    FloatingActionButton fabHome;

    int lastSelectedPosition = 0;

    BadgeItem numberBadgeItem;

    @Override
    public int getLayoutId() {
        return R.layout.activity_all;
    }

    @Override
    public void init() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        ButterKnife.bind(this);

        modeFixed.setOnCheckedChangeListener(this);
        modeShifting.setOnCheckedChangeListener(this);
        bgRipple.setOnCheckedChangeListener(this);
        bgStatic.setOnCheckedChangeListener(this);
        items3.setOnCheckedChangeListener(this);
        items4.setOnCheckedChangeListener(this);
        items5.setOnCheckedChangeListener(this);
        autoHide.setOnCheckedChangeListener(this);

        toggleHide.setOnClickListener(this);
        toggleBadge.setOnClickListener(this);
        fabHome.setOnClickListener(this);

        bottomNavigationBar.setTabSelectedListener(this);

        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_github:
                String url = "https://github.com/Ashok-Varma/BottomNavigation";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toggle_hide) {
            if (bottomNavigationBar != null) {
                bottomNavigationBar.show();// .toggle();
            }
        } else if (v.getId() == R.id.toggle_badge) {
            if (numberBadgeItem != null) {
                numberBadgeItem.toggle();
            }
        } else if (v.getId() == R.id.fab_home) {
            final Snackbar snackbar = Snackbar.make(message, "Fab Clicked", Snackbar.LENGTH_LONG);
            snackbar.setAction("dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.bg_ripple:
                bgStatic.setChecked(!isChecked);
                break;
            case R.id.bg_static:
                bgRipple.setChecked(!isChecked);
                break;
            case R.id.mode_fixed:
                modeShifting.setChecked(!isChecked);
                break;
            case R.id.mode_shifting:
                modeFixed.setChecked(!isChecked);
                break;
            case R.id.items_3:
                if (isChecked) {
                    items4.setChecked(false);
                    items5.setChecked(false);
                }
                break;
            case R.id.items_4:
                if (isChecked) {
                    items3.setChecked(false);
                    items5.setChecked(false);
                }
                break;
            case R.id.items_5:
                if (isChecked) {
                    items4.setChecked(false);
                    items3.setChecked(false);
                }
                break;
        }
        if (!items5.isChecked() && !items3.isChecked() && !items4.isChecked()) {
            buttonView.setChecked(true);
        }
        refresh();
    }
    private void refresh() {

        bottomNavigationBar.clearAll();
//        bottomNavigationBar.setFab(fabHome, BottomNavigationBar.FAB_BEHAVIOUR_TRANSLATE_AND_STICK);
        bottomNavigationBar.setFab(fabHome);

        setScrollableText(lastSelectedPosition);

        numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(android.R.color.holo_blue_bright)
                .setText("" + lastSelectedPosition)
                .setHideOnSelect(autoHide.isChecked());


        if (modeFixed.isChecked()) {
            bottomNavigationBar
                    .setMode(BottomNavigationBar.MODE_FIXED);
        } else if (modeShifting.isChecked()) {
            bottomNavigationBar
                    .setMode(BottomNavigationBar.MODE_SHIFTING);
        }

        if (bgStatic.isChecked()) {
            bottomNavigationBar
                    .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        } else if (bgRipple.isChecked()) {
            bottomNavigationBar
                    .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        }

        if (items3.isChecked()) {
            bottomNavigationBar
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_kehu, "Nearby")
                            .setActiveColorResource(R.color.blue)
                            .setBadgeItem(numberBadgeItem))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_wode_pr, "Find")
                            .setActiveColorResource(R.color.teal))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_shouye_pr, "Categories").setActiveColorResource(R.color.blue))
                    .setFirstSelectedPosition(lastSelectedPosition > 2 ? 2 : lastSelectedPosition)
                    .initialise();
        } else if (items4.isChecked()) {
            bottomNavigationBar
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_kehu, "Home").setActiveColorResource(R.color.blue).setBadgeItem(numberBadgeItem))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_wode_pr, "Books").setActiveColorResource(R.color.teal))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_xiangmu_pr, "Music").setActiveColorResource(R.color.blue))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_xinxi_pr, "Movies & TV").setActiveColorResource(R.color.brown))
                    .setFirstSelectedPosition(lastSelectedPosition > 3 ? 3 : lastSelectedPosition)
                    .initialise();
        } else if (items5.isChecked()) {
            bottomNavigationBar
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_wode_pr, "Home").setActiveColorResource(R.color.orange).setBadgeItem(numberBadgeItem))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_xiangmu_pr, "Books").setActiveColorResource(R.color.teal))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_shouye_pr, "Music").setActiveColorResource(R.color.blue))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_kehu_pr, "Movies & TV").setActiveColorResource(R.color.brown))
                    .addItem(new BottomNavigationItem(R.drawable.common_btn_xiangmu_pr, "Games").setActiveColorResource(R.color.grey))
                    .setFirstSelectedPosition(lastSelectedPosition)
                    .initialise();
        }
    }

    @Override
    public void onTabSelected(int position) {
        lastSelectedPosition = position;
        setMessageText(position + " Tab Selected");
        if (numberBadgeItem != null) {
            numberBadgeItem.setText(Integer.toString(position));
        }
        setScrollableText(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {
        setMessageText(position + " Tab Reselected");
    }

    private void setMessageText(String messageText) {
        message.setText(messageText);
    }

    private void setScrollableText(int position) {
        switch (position) {
            case 0:
                scrollableText.setText(R.string.para1);
                break;
            case 1:
                scrollableText.setText(R.string.para2);
                break;
            case 2:
                scrollableText.setText(R.string.para3);
                break;
            case 3:
                scrollableText.setText(R.string.para4);
                break;
            case 4:
                scrollableText.setText(R.string.para5);
                break;
            default:
                scrollableText.setText(R.string.para6);
                break;
        }
    }
}
