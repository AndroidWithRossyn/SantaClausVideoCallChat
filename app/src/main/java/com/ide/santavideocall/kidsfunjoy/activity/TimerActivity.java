package com.ide.santavideocall.kidsfunjoy.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import android.os.Handler;
import android.view.View;

import java.text.SimpleDateFormat;

import com.airbnb.lottie.LottieAnimationView;
import com.ide.santavideocall.kidsfunjoy.R;
import com.pesonal.adsdk.AppManage;

public class TimerActivity extends AppCompatActivity {
    private TextView txtDay, txtHour, txtMinute, txtSecond;
    private ImageView forward;
    private Handler handler;
    private Runnable runnable;
    AnalogClock analogClock1;
    LottieAnimationView lottieVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        txtDay = (TextView) findViewById(R.id.txtDay);
        txtHour = (TextView) findViewById(R.id.txtHour);
        txtMinute = (TextView) findViewById(R.id.txtMinute);
        txtSecond = (TextView) findViewById(R.id.txtSecond);
        forward = (ImageView) findViewById(R.id.forward);
        lottieVideo = (LottieAnimationView) findViewById(R.id.lottieVideo);

        AppManage.getInstance(TimerActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(TimerActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManage.getInstance(TimerActivity.this).showInterstitialAd(TimerActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Intent intent = new Intent(TimerActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                }, "", AppManage.app_mainClickCntSwAd);

            }
        });

        countDownStart();
    }

    public void countDownStart() {
        handler = new Handler();
        analogClock1 = findViewById(R.id.analogClock1);
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    // Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2023-12-25");
//                    Date currentDate1=dateFormat.parse("2022-9-28");

                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtDay.setText("" + String.format("%02d", days));
                        txtHour.setText("" + String.format("%02d", hours));
                        txtMinute.setText(""
                                + String.format("%02d", minutes));
                        txtSecond.setText(""
                                + String.format("%02d", seconds));
                    } else {
//                        Toast.makeText(TimerActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();

//                        tvEventStart.setVisibility(View.VISIBLE);
//                        tvEventStart.setText("The event started!");
                        txtDay.setText("00");
                        txtHour.setText("00");
                        txtMinute.setText("00");
                        txtSecond.setText("00");
//                        textViewGone();
                    }
//                    if (futureDate == currentDate) {
//                        analogClock1.setVisibility(View.INVISIBLE);
//                        lottieVideo.setVisibility(View.VISIBLE);
//                    } else {
//                    }

                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }
        }

        ;
        handler.postDelayed(runnable, 1 * 1000);
    }
    @Override
    public void onBackPressed() {
        AppManage.getInstance(TimerActivity.this).showInterstitialAd(TimerActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                TimerActivity.super.onBackPressed();
            }
        }, "", AppManage.app_mainClickCntSwAd);
    }

//    public void textViewGone() {
//        findViewById(R.id.LinearLayout1).setVisibility(View.GONE);
//        findViewById(R.id.LinearLayout2).setVisibility(View.GONE);
//        findViewById(R.id.LinearLayout3).setVisibility(View.GONE);
//        findViewById(R.id.LinearLayout4).setVisibility(View.GONE);
//    }
}