package com.konka.test;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.konka.constant.Constant;
import com.konka.ijkui.IjkVideoView;

import java.util.Timer;
import java.util.TimerTask;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by DongJunJie on 2017-3-7.
 */

public class A extends Activity {

    /**
     * Called when the activity is first created.
     */

    private SeekBar skb_audio = null;
    private Button btn_start_audio = null;
    private Button btn_stop_audio = null;

    private SeekBar skb_video = null;
    private Button btn_start_video = null;
    private Button btn_stop_video = null;
    private IjkVideoView surfaceView;


    private Timer mTimer;
    private TimerTask mTimerTask;

    private boolean isChanging = false;//互斥变量，防止定时器与SeekBar拖动时进度冲突

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);
        //----------Media控件设置---------//
//        m = new MediaPlayer();
        //播放结束之后弹出提示
//        m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer arg0) {
//                Toast.makeText(A.this, "结束", Toast.LENGTH_LONG).show();
//                m.release();
//            }
//        });
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        btn_start_audio = (Button) this.findViewById(R.id.Button01);
        btn_stop_audio = (Button) this.findViewById(R.id.Button02);
        btn_start_audio.setOnClickListener(new ClickEvent());
        btn_stop_audio.setOnClickListener(new ClickEvent());
        skb_audio = (SeekBar) this.findViewById(R.id.SeekBar01);
        skb_audio.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        btn_start_video = (Button) this.findViewById(R.id.Button03);
        btn_stop_video = (Button) this.findViewById(R.id.Button04);
        btn_start_video.setOnClickListener(new ClickEvent());
        btn_stop_video.setOnClickListener(new ClickEvent());
        skb_video = (SeekBar) this.findViewById(R.id.SeekBar02);
        skb_video.setOnSeekBarChangeListener(new SeekBarChangeEvent());
        surfaceView = (IjkVideoView) findViewById(R.id.SurfaceView01);
        surfaceView.setVideoURI(Uri.parse(Constant.sPath3));

    }

    /*
     * 按键事件处理
     */
    class ClickEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == btn_start_audio) {
//                m.reset();//恢复到未初始化的状态
//                m=new MediaPlayer();
//                m = MediaPlayer.create(TestActivity.this, R.raw.big);//读取音频
//                skb_audio.setMax(m.getDuration());//设置SeekBar的长度
//                try {
//                    m.prepare();    //准备
//                } catch (IllegalStateException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//                m.start();  //播放
            } else if (v == btn_stop_audio || v == btn_stop_video) {
//                m.stop();
                surfaceView.stopPlayback();
            } else if (v == btn_start_video) {
//                m.reset();//恢复到未初始化的状态
//                surfaceView.
                surfaceView.start();
            }
        }
    }

    /*
     * SeekBar进度改变事件
     */
    class SeekBarChangeEvent implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            isChanging = true;
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            surfaceView.seekTo(seekBar.getProgress());
            isChanging = false;
        }
    }
}
