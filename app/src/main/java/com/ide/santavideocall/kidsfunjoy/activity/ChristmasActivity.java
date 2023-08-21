package com.ide.santavideocall.kidsfunjoy.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.ide.santavideocall.kidsfunjoy.R;
import com.pesonal.adsdk.AppManage;

public class ChristmasActivity extends AppCompatActivity {
    MediaPlayer m;
    long k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_christmas);
        this.m = MediaPlayer.create(this, R.raw.jingle_bell);
        this.m.start();
        this.m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                ChristmasActivity.this.m.start();
                ChristmasActivity.this.m();
            }
        });
        m();

    }
    @Override
    public void onBackPressed() {
        AppManage.getInstance(ChristmasActivity.this).showInterstitialAd(ChristmasActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                k();
                ChristmasActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }

    public void k() {
        MediaPlayer mediaPlayer = this.m;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.m.stop();
        }
    }
    /* access modifiers changed from: private */
    public void m() {
        Window window = getWindow();
        window.addFlags(4194304);
        window.addFlags(524288);
        window.addFlags(2097152);
    }
}