package com.tvyanqiu.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tvyanqiu.R;
import com.tvyanqiu.ui.adapter.TvAdapter;
import com.tvyanqiu.bean.TvInfoListBean;
import com.tvyanqiu.http.MyOkHttpClient;
import com.tvyanqiu.config.Constant;
import com.tvyanqiu.ui.dialog.TVDialogFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class DifangActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private List<TvInfoListBean.TvinfoBean> tvinfoList;
    private ArrayList<TvInfoListBean.TvinfoBean> okTvinfoList;
    private TvAdapter myGridAdapter;
    private GridView gvDifangTvinfo;
    private String difangJsonUrl;
    public static int style = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difang);
        Intent intent = getIntent();
        difangJsonUrl = intent.getStringExtra("difangJsonUrl");
        initViewAndListener();
        initData();
    }

    private void initViewAndListener() {
        RadioGroup rgStyle = findViewById(R.id.rg_style);
        TextView tvCaidan = findViewById(R.id.tv_caidan);
        gvDifangTvinfo = findViewById(R.id.gv_difang_tvinfo);
        rgStyle.setOnCheckedChangeListener(this);
        tvCaidan.setOnClickListener(this);
        myGridAdapter = new TvAdapter(this, getFragmentManager(), okTvinfoList);
        gvDifangTvinfo.setAdapter(myGridAdapter);
    }

    private void initData() {
        String url = Constant.BASE_URL + Constant.JSON_URL + difangJsonUrl;
        MyOkHttpClient.getInstance().asyncGet(url, new MyOkHttpClient.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                Log.v("数据请求2", "请求异常");
                finish();
            }

            @Override
            public void onSuccess(Request request, String result) {
                TvInfoListBean tvInfoListBean = new Gson().fromJson(result, TvInfoListBean.class);
                tvinfoList = tvInfoListBean.getTvinfoList();
                if (okTvinfoList == null) {
                    okTvinfoList = new ArrayList<>();
                } else {
                    okTvinfoList.clear();
                }
                for (TvInfoListBean.TvinfoBean tvinfo : tvinfoList) {
                    if (tvinfo.isTv_is_open()) {
                        okTvinfoList.add(tvinfo);
                    }
                }
                myGridAdapter.setData(okTvinfoList);
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_style0:
                style = 0;
                break;
            case R.id.rb_style1:
                style = 1;
                break;
            case R.id.rb_style2:
                style = 2;
                break;
            case R.id.rb_style3:
                style = 3;
                break;
            case R.id.rb_style4:
                style = 4;
                break;
        }
    }

    @Override
    public void onClick(View view) {
        TVDialogFragment webDialogFragment = new TVDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("TVUrl", Constant.CAR_URL);
        webDialogFragment.setArguments(bundle);
        webDialogFragment.show(getFragmentManager(), "webDialogFragment");
    }
}
