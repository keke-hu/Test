package test.mmote.com.testapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.czt.mp3recorder.MP3Recorder;
import com.mmote.play.bean.AudioBean;
import com.shuyu.waveview.AudioPlayer;
import com.shuyu.waveview.FileUtils;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by KeKe on 2017/9/15.
 */

public class RecordAudioActivity extends BaseActivity {
    AudioBean audioBean = new AudioBean("", "", "");
    private ImageView ivStar, ivRecordAgain, ivComplete;
    public final String TAG = this.getClass().getSimpleName();
    private TextView tvTime;
    private MP3Recorder mRecorder;
    private AudioPlayer audioPlayer;
    private final String START_TIME = "00:00";
    private String audioLength = "";
    private long time;
    private String filePath = "";
    /**
     * 录制是否完成
     */
    private boolean recordCompleted = false;
    /**
     * 正在播放
     */
    private boolean playing = false;
    /**
     * 播放暂停
     */
    private boolean playerPause = false;
    private int curPosition = 0;
    private Subscription subscription;
    private final Handler handler = new AudioHandler(this);

    public static void action(Activity activity, AudioBean audioBean, int code, boolean isMaster) {
        Intent intent = new Intent(activity, RecordAudioActivity.class);
        intent.putExtra("AudioBean", audioBean);
        if (isMaster) {
            intent.putExtra("isMaster", isMaster);
        }
        activity.startActivityForResult(intent, code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio);
        ivStar = (ImageView) findViewById(R.id.iv_star);
        ivRecordAgain = (ImageView) findViewById(R.id.iv_record_again);
        ivComplete = (ImageView) findViewById(R.id.iv_complete);
        tvTime = (TextView) findViewById(R.id.tv_time);
        if (getIntent().hasExtra("AudioBean")) {
            audioBean = (AudioBean) getIntent().getSerializableExtra("AudioBean");
        }
        ivComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });

        findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
        initRecord();
        initRecordAgain();
        initPlay();
        new RxPermissions(this)
                .requestEach(Manifest.permission.RECORD_AUDIO)
                .subscribe(new Action1<Permission>() {
                    @Override
                    public void call(Permission permission) {
                        if (permission.granted) {
                            ivStar.setEnabled(true);
                        } else {
                            ivStar.setEnabled(false);
                            longToast("为了更好的用户体验，请打开前往应用设置页面打开录音权限");
                        }
                    }
                });
    }


    private void commit() {
        if (TextUtils.isEmpty(filePath) && TextUtils.isEmpty(audioBean.getNetPath())) {
            toast("请上传音频");
            return;
        }
        if (!TextUtils.isEmpty(filePath)) {
//            uploadAudio(filePath);
        } else {
            back();
        }
    }

    private void back() {
        Intent intent = new Intent();
        intent.putExtra("result", audioBean);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    private void initPlay() {
        audioPlayer = new AudioPlayer(this, handler);
    }

    private void initRecord() {
        ivStar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!recordCompleted) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Log.d(TAG, "ACTION_DOWN");
                        time = System.currentTimeMillis();
                        boolean result = startRecord();
                        if (!result) {
                            //魅族手机录音时会弹出权限申请bug
                            ivStar.performClick();
                        }
                        return result;
                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        Log.d(TAG, "ACTION_UP");
                        ivStar.setImageResource(R.drawable.sound_star_n);
                        stopRecord();
                        if (System.currentTimeMillis() - time < 5000) {
                            toast("录制时间太短");
                            recordCompleted = true;
                            filePath = "";
                            unSubscribe();
                            ivRecordAgain.performClick();//重置
                        }
                        return true;
                    }
                }
                return false;
            }
        });
        ivStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recordCompleted) {
                    if (playing) {//播放中
                        pause();//暂停
                    } else {
                        if (playerPause) {//暂停中
                            audioPlayer.play();//播放
                        } else {
                            if (hasAudio()) {
                                if (!TextUtils.isEmpty(audioBean.getLocalPath())) {
                                    audioPlayer.playUrl(audioBean.getLocalPath());
                                } else {
                                    audioPlayer.playUrl(audioBean.getNetPath());
                                }
                            } else {
                                audioPlayer.playUrl(filePath);
                            }
                        }
                        playing = true;
                        ivStar.setImageResource(R.drawable.sound_pause_selector);
                    }
                }
            }
        });
        if (hasAudio()) {
            recordCompleted = true;
            ivStar.setImageResource(R.drawable.sound_play_selector);
            Observable.just(audioBean.getAudioLength())
                    .map(new Func1<String, Integer>() {
                        @Override
                        public Integer call(String s) {
                            return Integer.parseInt(s);
                        }
                    })
                    .map(new Func1<Integer, String>() {
                        @Override
                        public String call(Integer integer) {
                            return integer < 10 ? "0" + integer : "" + integer;
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            tvTime.setText(getString(R.string.time, s));
                        }
                    });
        }
    }

    /**
     * 已经有录音了
     *
     * @return
     */
    private boolean hasAudio() {
        return audioBean != null
                && (!TextUtils.isEmpty(audioBean.getLocalPath())
                || !TextUtils.isEmpty(audioBean.getNetPath()));
    }

    /**
     * 录音完毕,将播放的url切换最最新的，之前可能是服务器下发的播放地址
     */
    private void initRecordComplete() {
        initPlay();
        ivStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recordCompleted) {
                    if (playing) {//播放中
                        pause();//暂停
                    } else {
                        if (playerPause) {//暂停中
                            audioPlayer.play();//播放
                        } else {
                            audioPlayer.playUrl(filePath);
                        }
                        playing = true;
                        ivStar.setImageResource(R.drawable.sound_pause_selector);
                    }
                }
            }
        });
    }


    /**
     * 暂停
     */
    public void pause() {
        if (playing && audioPlayer != null) {
            audioPlayer.pause();
            playerPause = true;
            playing = false;
            ivStar.setImageResource(R.drawable.sound_play_selector);
        }
    }


    /**
     * 重录
     */
    private void initRecordAgain() {
        ivRecordAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recordCompleted) {
                    if (!TextUtils.isEmpty(filePath)) {
                        FileUtils.deleteFile(filePath);
                    }
                    pause();
                    tvTime.setText(START_TIME);
                    ivStar.setImageResource(R.drawable.sound_star_selector);
                    playerPause = false;
                    recordCompleted = false;
                }
            }
        });

    }

    /**
     * 开始录音
     */
    private boolean startRecord() {
        if (!TextUtils.isEmpty(filePath)) {
            FileUtils.deleteFile(filePath);//删除之前的
        }
        tvTime.setText(START_TIME);
        filePath = FileUtils.getAppPath();
        File file = new File(filePath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                toast("创建文件失败");
                return false;
            }
        }
        filePath = FileUtils.getAppPath() + System.currentTimeMillis() + ".mp3";
        mRecorder = new MP3Recorder(new File(filePath));
        mRecorder.setErrorHandler(new RecordHandler(this));
        try {
            long time = System.currentTimeMillis();
            Log.d(TAG,"start");
            mRecorder.start();
            Log.d(TAG,"end");
            long endtime = System.currentTimeMillis();
            long dur = endtime - time;
            if (dur > 1000) {
                return false;
            }
        } catch (IOException e) {
            Log.d(TAG,"IOException");
            e.printStackTrace();
            toast("录音出现异常");
            return false;
        }
        ivStar.setImageResource(R.drawable.sound_star_h);
        recordCompleted = false;
        subscription = Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        audioLength = String.valueOf(aLong);
                        return aLong < 10 ? "0" + aLong : "" + aLong;
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tvTime.setText(getString(R.string.time, s));
                        if (s.equals("15")) {//最长15秒
                            maxTime();
                        }
                    }
                });
        return true;
    }

    private void maxTime() {
        ivStar.setImageResource(R.drawable.sound_play_selector);
        toast("最长15秒");
        unSubscribe();
    }

    private void unSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    /**
     * 停止录音
     */
    private void stopRecord() {
        if (!recordCompleted && mRecorder != null && mRecorder.isRecording()) {
            mRecorder.setPause(false);
            mRecorder.stop();
            unSubscribe();
        }
        ivStar.setImageResource(R.drawable.sound_play_selector);
        recordCompleted = true;
        initRecordComplete();
    }


    private static class AudioHandler extends Handler {
        private final WeakReference<RecordAudioActivity> activityWeakReference;

        AudioHandler(RecordAudioActivity audioActivity) {
            activityWeakReference = new WeakReference<>(audioActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RecordAudioActivity audioActivity = activityWeakReference.get();
            switch (msg.what) {
                case AudioPlayer.HANDLER_CUR_TIME://更新的时间
                    audioActivity.curPosition = ((int) msg.obj) / 1000;
                    String time = audioActivity.curPosition < 10 ? "0" + audioActivity.curPosition : audioActivity.curPosition + "";
                    audioActivity.tvTime.setText(audioActivity.getString(R.string.time, time));
                    break;
                case AudioPlayer.HANDLER_COMPLETE://播放结束
                    audioActivity.playing = false;
                    audioActivity.ivStar.setImageResource(R.drawable.sound_play_selector);
                    break;
                case AudioPlayer.HANDLER_PREPARED://播放开始
                    audioActivity.playing = true;
                    audioActivity.ivStar.setImageResource(R.drawable.sound_pause_selector);
                    break;
                case AudioPlayer.HANDLER_ERROR://播放错误
                    audioActivity.playing = false;
                    audioActivity.toast("出错了");
                    audioActivity.ivStar.setImageResource(R.drawable.sound_play_selector);
                    break;
            }

        }
    }

    private static class RecordHandler extends Handler {
        private final WeakReference<RecordAudioActivity> activityWeakReference;

        RecordHandler(RecordAudioActivity audioActivity) {
            activityWeakReference = new WeakReference<>(audioActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RecordAudioActivity audioActivity = activityWeakReference.get();
            if (msg.what == MP3Recorder.ERROR_TYPE) {
                audioActivity.toast("出错了");
            }
        }
    }

}

