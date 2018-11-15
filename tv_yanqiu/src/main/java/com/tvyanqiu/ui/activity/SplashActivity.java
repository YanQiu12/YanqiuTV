package com.tvyanqiu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tvyanqiu.R;
import com.tvyanqiu.bean.VersionBean;
import com.tvyanqiu.http.MyOkHttpClient;
import com.tvyanqiu.config.Constant;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Request;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private VersionBean versionBean;
    private boolean MainActivityIsOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
    }

    private void initView() {
        RelativeLayout llJump = findViewById(R.id.ll_jump);
        llJump.setOnClickListener(this);
        MainActivityIsOpen = false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_jump:
                Intent intent = new Intent(context, DifangActivity.class);
                MainActivityIsOpen = true;
                startActivity(intent);
                finish();
                break;
        }
    }

    private void initData() {
        context = this;
        String url = Constant.BASE_URL + Constant.JSON_URL + Constant.VERSION_URL;
        MyOkHttpClient.getInstance().asyncGet(url, new MyOkHttpClient.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                Log.v("数据请求1", "请求异常");
                finish();
                killApp();
            }

            @Override
            public void onSuccess(Request request, String result) {
                int currentVersionCode = getCurrentVersionCode(context);
                versionBean = new Gson().fromJson(result, VersionBean.class);
                String[] usedVersionCode = versionBean.getUsedVersionCode().split(",");
                if (shuzuHaveYuansu(usedVersionCode, currentVersionCode + "")) {
                    if (versionBean.getVersionCode() > currentVersionCode) {
                        Toast.makeText(context, "请下载最新版，谢谢！", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable(){
                            public void run() {
                                finish();
                                killApp();
                            }
                        }, 2000);
                    } else {
                        if (MainActivityIsOpen) {

                        } else {
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                } else {
                    Toast.makeText(context, "当前版本已停用。。。", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            finish();
                            killApp();
                        }
                    }, 2000);
                }
            }
        });
    }

    private void killApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private boolean shuzuHaveYuansu(String[] shuzu, String yuansu) {
        return Arrays.asList(shuzu).contains(yuansu);
    }

    /**
     * 获取当前本地apk的版本
     *
     * @param mContext
     * @return
     */
    private int getCurrentVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
