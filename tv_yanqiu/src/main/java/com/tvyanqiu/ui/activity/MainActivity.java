package com.tvyanqiu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tvyanqiu.R;
import com.tvyanqiu.bean.DifangInfoListBean;
import com.tvyanqiu.http.MyOkHttpClient;
import com.tvyanqiu.ui.adapter.DifangAdapter;
import com.tvyanqiu.config.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private List<DifangInfoListBean.DifangtvinfoBean> difanginfoList;
    private ArrayList<DifangInfoListBean.DifangtvinfoBean> okDifanginfoList;
    private DifangAdapter difangAdapter;
    private GridView gvDifangTvinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewAndListener();
        initData();
    }

    private void initViewAndListener() {
        gvDifangTvinfo = findViewById(R.id.gv_main_tvinfo);
        difangAdapter = new DifangAdapter(this, okDifanginfoList);
        gvDifangTvinfo.setAdapter(difangAdapter);
    }

    private void initData() {
        String url = Constant.BASE_URL + Constant.JSON_URL + Constant.DIFANGINFOS_URL;
        MyOkHttpClient.getInstance().asyncGet(url, new MyOkHttpClient.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                Log.v("数据请求2", "请求异常");
                finish();
            }

            @Override
            public void onSuccess(Request request, String result) {
                DifangInfoListBean tvInfoListBean = new Gson().fromJson(result, DifangInfoListBean.class);
                difanginfoList = tvInfoListBean.getDifangtvinfoList();
                if (okDifanginfoList == null) {
                    okDifanginfoList = new ArrayList<>();
                } else {
                    okDifanginfoList.clear();
                }
                for (DifangInfoListBean.DifangtvinfoBean tvinfo : difanginfoList) {
                    if (tvinfo.isTv_is_open()) {
                        okDifanginfoList.add(tvinfo);
                    }
                }
                difangAdapter.setData(okDifanginfoList);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        killApp();
    }

    private void killApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
}
