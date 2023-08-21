package com.ide.santavideocall.kidsfunjoy.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ide.santavideocall.kidsfunjoy.R;
import com.ide.santavideocall.kidsfunjoy.reject.RejectVideoCallActivity;
import com.ide.santavideocall.kidsfunjoy.reject.RejectVideoCallOneActivity;
import com.ide.santavideocall.kidsfunjoy.reject.RejectVideoCallThreeActivity;
import com.ide.santavideocall.kidsfunjoy.reject.RejectVideoCallTwoActivity;
import com.pesonal.adsdk.AppManage;

public class VideoMeetingActivity extends AppCompatActivity {
    private ImageView pixie,glitzy,cosmo,jolly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_meeting);
        pixie=findViewById(R.id.pixie);
        glitzy=findViewById(R.id.glitzy);
        cosmo=findViewById(R.id.cosmo);
        jolly=findViewById(R.id.jolly);
        AppManage.getInstance(VideoMeetingActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(VideoMeetingActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        pixie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(VideoMeetingActivity.this).showInterstitialAd(VideoMeetingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(VideoMeetingActivity.this, RejectVideoCallActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        glitzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(VideoMeetingActivity.this).showInterstitialAd(VideoMeetingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(VideoMeetingActivity.this, RejectVideoCallOneActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        cosmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(VideoMeetingActivity.this).showInterstitialAd(VideoMeetingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(VideoMeetingActivity.this, RejectVideoCallTwoActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
        jolly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(VideoMeetingActivity.this).showInterstitialAd(VideoMeetingActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent=new Intent(VideoMeetingActivity.this, RejectVideoCallThreeActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(VideoMeetingActivity.this).showInterstitialAd(VideoMeetingActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                VideoMeetingActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }
}