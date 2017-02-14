package com.dream.aggregation.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.dream.aggregation.DreamAggApplication;
import com.dream.aggregation.R;
import com.dream.aggregation.dialog.WaitDialog;
import com.dream.aggregation.util.URLUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class PlayActivity extends BaseActivity {

    String htmlUrl;
    String playUrl;
    String LoadUrl;

    String key;

    @BindView(R.id.surface_view)
    public VideoView mVideoView;

    @BindView(R.id.operation_volume_brightness)
    public View mVolumeBrightnessLayout;

    @BindView(R.id.operation_bg)
    public ImageView mOperationBg;

    @BindView(R.id.operation_percent)
    public ImageView mOperationPercent;

    private AudioManager mAudioManager;
    /** 最大声音 */
    private int mMaxVolume;
    /** 当前声音 */
    private int mVolume = -1;
    /** 当前亮度 */
    private float mBrightness = -1f;
    /** 当前缩放模式 */
    private int mLayout = VideoView.VIDEO_LAYOUT_ZOOM;
    private GestureDetector mGestureDetector;
    private MediaController mMediaController;

    private WaitDialog waitDialog;

    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DreamAggApplication)DreamAggApplication.getContext()).addActivity(this);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_play);

        waitDialog = WaitDialog
                .createDialog(this);
        waitDialog.show();

        Intent intent = this.getIntent();
        htmlUrl=(String)intent.getSerializableExtra("htmlUrl");
        key=(String)intent.getSerializableExtra("key");

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(playUrl == null || "".equals(playUrl)) {
                    Message message = new Message();
                    message.what = 1;
                    timeHandler.sendMessage(message);
                }
            }
        };
        timer.schedule(task, 15000);

        ButterKnife.bind(this);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mMediaController = new MediaController(this);
        mGestureDetector = new GestureDetector(this, new MyGestureListener());
        mVideoView.setActivity(this);

        getPlayUrl();
    }

    private void getPlayUrl(){
        if(htmlUrl.contains(URLUtil.DOUYU_NAME_BS) || htmlUrl.contains(URLUtil.XIONGMAO_NAME_BS)){
            LoadUrl = "javascript:window.js_method.showSource(document.getElementsByTagName('video')[0].src);";
            initWebView();
        } else if(htmlUrl.contains(URLUtil.ZHANQI_NAME_BS)){
            LoadUrl = "javascript:window.js_method.showSource(document.getElementsByTagName('source')[0].src);";
            initWebView();
        }else if(htmlUrl.contains(URLUtil.QUANMIN_NAME_BS)){
            playUrl = "http://hls.quanmin.tv/live/" + key + "/playlist.m3u8";
            mVideoView.setVideoPath(playUrl);
            mVideoView.setMediaController(mMediaController);
            mVideoView.requestFocus();
            mVideoView
                    .setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.setPlaybackSpeed(1.0f);
                            waitDialog.dismiss();
                        }
                    });
        }else if(htmlUrl.contains(URLUtil.HUYA_NAME_BS)){

        }
    }

    void initWebView(){
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        // 启用localStorage 和 essionStorage
        webView.getSettings().setDomStorageEnabled(true);

        // 开启应用程序缓存
        webView.getSettings().setAppCacheEnabled(true);
        String appCacheDir = this.getApplicationContext()
                .getDir("cache", Context.MODE_PRIVATE).getPath();
        webView.getSettings().setAppCachePath(appCacheDir);
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 10);// 设置缓冲大小，我设的是10M
        webView.getSettings().setAllowFileAccess(true);

        webView.getSettings()
                .setUserAgentString(
                        "Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Version/7.0 Mobile/11D5145e Safari/9537.53");

        webView.addJavascriptInterface(new InJavaScriptLocalObj(), "js_method");
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(htmlUrl);
    }

    final class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.d("WebView","onPageStarted");
            super.onPageStarted(view, url, favicon);
        }
        public void onPageFinished(WebView view, String url) {
            Log.d("WebView","onPageFinished ");
            view.loadUrl(LoadUrl);
            super.onPageFinished(view, url);
        }
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            playUrl = html;
            mPlayHandler.removeMessages(0);
            mPlayHandler.sendEmptyMessageDelayed(0, 500);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event))
            return true;

        // 处理手势结束
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }

        return super.onTouchEvent(event);
    }

    /** 手势结束 */
    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;

        // 隐藏
        mDismissHandler.removeMessages(0);
        mDismissHandler.sendEmptyMessageDelayed(0, 500);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        /** 双击 */
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            if (mLayout == VideoView.VIDEO_LAYOUT_ZOOM)
                mLayout = VideoView.VIDEO_LAYOUT_ORIGIN;
            else
                mLayout++;
            if (mVideoView != null)
                mVideoView.setVideoLayout(mLayout, 0);
            return true;
        }

        /** 滑动 */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            Display disp = getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();

            if (mOldX > windowWidth * 4.0 / 5)// 右边滑动
                onVolumeSlide((mOldY - y) / windowHeight);
            else if (mOldX < windowWidth / 5.0)// 左边滑动
                onBrightnessSlide((mOldY - y) / windowHeight);

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    /** 定时隐藏 */
    private Handler mDismissHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mVolumeBrightnessLayout.setVisibility(View.GONE);
        }
    };

    private Handler mPlayHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(playUrl == null || "".equals(playUrl)){
                return;
            }
            mVideoView.setVideoPath(playUrl);
            mVideoView.setMediaController(mMediaController);
            mVideoView.requestFocus();
            mVideoView
                    .setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.setPlaybackSpeed(1.0f);
                            waitDialog.dismiss();
                        }
                    });

        }
    };

    Handler timeHandler = new Handler() {
        public void handleMessage(Message msg) {
            // 要做的事情
            Toast.makeText(PlayActivity.this, getResources().getString(R.string.timeout_msg),Toast.LENGTH_SHORT).show();
            PlayActivity.this.finish();
        }
    };

    /**
     * 滑动改变声音大小
     *
     * @param percent
     */
    private void onVolumeSlide(float percent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;

            // 显示
            mOperationBg.setImageResource(R.drawable.video_volumn_bg);
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }

        int index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;

        // 变更声音
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);

        // 变更进度条
        ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
        lp.width = findViewById(R.id.operation_full).getLayoutParams().width
                * index / mMaxVolume;
        mOperationPercent.setLayoutParams(lp);
    }

    /**
     * 滑动改变亮度
     *
     * @param percent
     */
    private void onBrightnessSlide(float percent) {
        if (mBrightness < 0) {
            mBrightness = getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;

            // 显示
            mOperationBg.setImageResource(R.drawable.video_brightness_bg);
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }
        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getWindow().setAttributes(lpa);

        ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
        lp.width = (int) (findViewById(R.id.operation_full).getLayoutParams().width * lpa.screenBrightness);
        mOperationPercent.setLayoutParams(lp);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mVideoView != null)
            mVideoView.setVideoLayout(mLayout, 0);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
