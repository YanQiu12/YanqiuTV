package com.tvyanqiu.ui.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tvyanqiu.config.ConstantTvURL;

public class TVDialogFragment extends DialogFragment {

    private int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private int WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;
    private Context context;
    private MediaPlayer mediaPlayer;
    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        // 接收关联Activity传来的数据 -----
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString("TVUrl", ConstantTvURL.url_cctv1);
        }
        context = getActivity();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        final TextureView myVideoView = new TextureView(context);

        final LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        ll.addView(myVideoView, ll_lp);
        myVideoView.setRotation(90);
        myVideoView.setScaleX(464f / 260);
        myVideoView.setScaleY(260f / 464);
        myVideoView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface,
                                                    int width, int height) {
                // TODO Auto-generated method stub
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface,
                                                  int width, int height) {
                // TODO Auto-generated method stub
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(url);
                    Surface surf = new Surface(surface);
                    mediaPlayer.setSurface(surf);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // TODO Auto-generated method stub
                            mp.start();
                        }
                    });

                    mediaPlayer
                            .setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    // TODO Auto-generated method stub
                                    // Logger.v("监听结束", "监听结束");
                                    if (mediaPlayer != null) {
                                        mediaPlayer.stop();
                                        mediaPlayer.release();
                                        mediaPlayer = null;
                                    }
                                }
                            });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        return ll;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);//硬件加速
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);//填充父布局
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Toast.makeText(context, "播放器注销了", Toast.LENGTH_SHORT).show();
    }

}
