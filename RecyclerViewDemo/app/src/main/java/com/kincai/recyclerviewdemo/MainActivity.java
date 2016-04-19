package com.kincai.recyclerviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.kincai.recyclerviewdemo.adapter.ListAdapter;
import com.kincai.recyclerviewdemo.bean.DATAS;
import com.kincai.recyclerviewdemo.bean.DataInfo;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ListAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    ListAdapter adapter;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark, R.color.white);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        loadListData(false, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_list_normal:
                //list 标准样式 listview垂直样式
                loadListData(false, true);
                break;
            case R.id.action_list_vertical_reverse:
                loadListData(true, true);
                break;
            case R.id.action_list_horizontal:
                loadListData(false, false);
                break;
            case R.id.action_list_horizontal_reverse:
                loadListData(true, false);
                break;
            case R.id.action_grid_normal:
                loadGridData(false, true);
                break;
            case R.id.action_grid_vertical_reverse:
                loadGridData(true, true);
                break;
            case R.id.action_grid_horizontal:
                loadGridData(false, false);
                break;
            case R.id.action_grid_horizontal_reverse:
                loadGridData(true, false);
                break;
            case R.id.action_staggered_normal:
                loadStaggered(false, true);
                break;
            case R.id.action_staggered_vertical_reverse:
                loadStaggered(true, true);
                break;
            case R.id.action_staggered_horizontal:
                loadStaggered(false, false);
                break;
            case R.id.action_staggered_horizontal_reverse:
                loadStaggered(true, false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * list显示
     *
     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    private void loadListData(boolean reverse, boolean vertical) {
        List<DataInfo> dataInfos = new ArrayList<>();
        int lenght = DATAS.ICONS.length;
        for (int i = 0; i < lenght; i++) {
            DataInfo data = new DataInfo(DATAS.ICONS[i], "list展示" + i);
            dataInfos.add(data);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置是否反向显示
        layoutManager.setReverseLayout(reverse);
        layoutManager.setOrientation(vertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(adapter = new ListAdapter(this, dataInfos, vertical));
        adapter.setOnItemClickListener(this);
    }

    /**
     * grid显示
     *
     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    private void loadGridData(boolean reverse, boolean vertical) {
        List<DataInfo> dataInfos = new ArrayList<>();
        int lenght = DATAS.ICONS.length;
        for (int i = 0; i < lenght; i++) {
            DataInfo data = new DataInfo(DATAS.ICONS[i], "list展示" + i);
            dataInfos.add(data);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        //设置是否反向显示
        layoutManager.setReverseLayout(reverse);
        layoutManager.setOrientation(vertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter = new ListAdapter(this, dataInfos, false));
        adapter.setOnItemClickListener(this);
    }

    /**
     * 瀑布流显示
     *
     * @param reverse  是否反向
     * @param vertical 是否垂直显示
     */
    private void loadStaggered(boolean reverse, boolean vertical) {
        List<DataInfo> dataInfos = new ArrayList<>();
        int lenght = DATAS.PICS.length;
        for (int i = 0; i < lenght; i++) {
            DataInfo data = new DataInfo(DATAS.PICS[i], "list展示" + i);
            dataInfos.add(data);
        }
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, vertical ?
                StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(reverse);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter = new ListAdapter(this, dataInfos, false));
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View itemView, int position) {
        Toast.makeText(this, "第"+(position+1)+"条", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(View itemView, int position) {
        Toast.makeText(this, "第"+(position+1)+"条", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0, 3000);
        System.out.println("---1---");
    }
}
