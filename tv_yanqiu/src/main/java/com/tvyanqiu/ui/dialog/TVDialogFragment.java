package com.tvyanqiu.ui.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.tvyanqiu.R;
import com.tvyanqiu.config.ConstantTvURL;
import com.tvyanqiu.ui.activity.DifangActivity;

public class TVDialogFragment extends DialogFragment {

    public static final int ASPECT_RATIO_ORIGIN = 0;//原始尺寸
    public static final int ASPECT_RATIO_FIT_PARENT = 1;//适应屏幕
    public static final int ASPECT_RATIO_PAVED_PARENT = 2;//全屏铺满
    public static final int ASPECT_RATIO_16_9 = 3;//16:9
    public static final int ASPECT_RATIO_4_3 = 4;//4:3
    private PLVideoTextureView plVideoTextureView;
    private Activity context;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        // 接收关联Activity传来的数据 -----
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString("TVUrl", ConstantTvURL.url_cctv1);
        }

        //设置透明背景
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        View inflate = inflater.inflate(R.layout.dialogfragment_tv, container, false);
        plVideoTextureView = (PLVideoTextureView) inflate.findViewById(R.id.plv);

        //设置Video的路径
        plVideoTextureView.setVideoPath(url);
        //设置MediaController，这里是拷贝官方Demo的MediaController，当然可以自己实现一个
        plVideoTextureView.setMediaController(null);
        //设置视频预览模式:0原始尺寸、1适应屏幕、2全屏铺满、16:9、4:3
        plVideoTextureView.setDisplayAspectRatio(DifangActivity.style);
        plVideoTextureView.setDisplayOrientation(270);
        //裁剪画面
//        plVideoTextureView.setVideoArea(100, 200, 100, 100);
        //设置错误监听
//        plVideoTextureView.setOnErrorListener(this);
        //设置宽高监听
//        plVideoTextureView.setOnVideoSizeChangedListener(this);


        return inflate;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);//硬件加速--疑似会导致异常崩溃
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);//填充父布局
    }

    @Override
    public void onResume() {
        super.onResume();
        plVideoTextureView.start();//开始播放
    }

    @Override
    public void onPause() {
        super.onPause();
        plVideoTextureView.pause();//暂停播放
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        plVideoTextureView.stopPlayback();//释放资源
        Toast.makeText(context, "播放器注销了", Toast.LENGTH_SHORT).show();
    }

}
