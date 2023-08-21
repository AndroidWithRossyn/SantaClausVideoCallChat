package com.ide.santavideocall.kidsfunjoy.play;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.ide.santavideocall.kidsfunjoy.R;
import com.ide.santavideocall.kidsfunjoy.activity.VideoMeetingActivity;
import com.ide.santavideocall.kidsfunjoy.drf;
import com.pesonal.adsdk.AppManage;

import java.io.IOException;

public class PlayVideoCallActivity extends Activity implements SurfaceHolder.Callback {
    long a = 0;
    Camera b;
    drf c = new drf(this);
    boolean d = false;
    RelativeLayout e;
    SurfaceHolder f;
    SurfaceView g;
    Uri h = null;
    VideoView i;
    String j = "1";
    private final String k = "VideoServer";

    private Dialog adprogress;
    private View pos;
    private int admobinter;

    public void a() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PlayVideoCallActivity.this.e.setVisibility(View.GONE);
            }
        }, 2000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_play_video_call);

        AppManage.getInstance(PlayVideoCallActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);

        //todo Ad Loading Dialog
        adprogress = new Dialog(PlayVideoCallActivity.this, R.style.Custom);
        adprogress.requestWindowFeature(1);
        adprogress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        adprogress.setCancelable(false);
        adprogress.setContentView(R.layout.z1_ads_loading_dialog_inter);

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.c.b());
        this.j = sb.toString();
        this.e = (RelativeLayout) findViewById(R.id.RelativeLayout04);
        a();
        this.i = (VideoView) findViewById(R.id.video);
        StringBuilder sb2 = new StringBuilder();
        String str = "android.resource://";
        sb2.append(str);
        sb2.append(getPackageName());
        String str2 = "/";
        sb2.append(str2);
        sb2.append(R.raw.video1);
        this.h = Uri.parse(sb2.toString());
//        if (this.j.equalsIgnoreCase("1")) {
//            StringBuilder sb3 = new StringBuilder();
//            sb3.append(str);
//            sb3.append(getPackageName());
//            sb3.append(str2);
//            sb3.append(R.raw.video1);
//            this.h = Uri.parse(sb3.toString());
//        } else if (this.j.equalsIgnoreCase("2")) {
//            StringBuilder sb4 = new StringBuilder();
//            sb4.append(str);
//            sb4.append(getPackageName());
//            sb4.append(str2);
//            sb4.append(R.raw.video2);
//            this.h = Uri.parse(sb4.toString());
//        } else if (this.j.equalsIgnoreCase("3")) {
//            StringBuilder sb5 = new StringBuilder();
//            sb5.append(str);
//            sb5.append(getPackageName());
//            sb5.append(str2);
//            sb5.append(R.raw.video3);
//            this.h = Uri.parse(sb5.toString());
//        } else if (this.j.equalsIgnoreCase("4")) {
//            StringBuilder sb6 = new StringBuilder();
//            sb6.append(str);
//            sb6.append(getPackageName());
//            sb6.append(str2);
//            sb6.append(R.raw.video4);
//            this.h = Uri.parse(sb6.toString());
//        }
//        else if (this.j.equalsIgnoreCase("5")) {
//            StringBuilder sb7 = new StringBuilder();
//            sb7.append(str);
//            sb7.append(getPackageName());
//            sb7.append(str2);
//            sb7.append(R.raw.video5);
//            this.h = Uri.parse(sb7.toString());
//        }
        this.g = (SurfaceView) findViewById(R.id.surfaceView1);
        this.f = this.g.getHolder();
        this.f.addCallback(this);
        this.f.setType(3);
        d();
        this.i.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                try {
                    PlayVideoCallActivity.this.i.requestFocus();
                    PlayVideoCallActivity.this.i.start();
                } catch (Exception e) {
                    System.out.printf("Error playing video %s\n", new Object[]{e});
                }
            }
        });
        this.i.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                PlayVideoCallActivity play_Video_Call_Activity = PlayVideoCallActivity.this;
                play_Video_Call_Activity.d = true;
                play_Video_Call_Activity.a("Call ended...");
                PlayVideoCallActivity.this.finish();
            }
        });
        this.i.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public void b() {
        this.i.setMediaController(new MediaController(this));
        this.i.setVideoURI(this.h);
        this.i.start();
    }

    private void d() {
        String str = "VideoServer";
        String str2 = "init_camera: ";
        try {
            c();
            try {
                this.b.setPreviewDisplay(this.g.getHolder());
                this.b.startPreview();
            } catch (Exception e2) {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(e2);
                Log.e(str, sb.toString());
            }
        } catch (RuntimeException e3) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(e3);
            Log.e(str, sb2.toString());
        }
    }

    public void c() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 1) {
                try {
                    this.b = Camera.open(i2);
                } catch (RuntimeException e2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Camera failed to open: ");
                    sb.append(e2.getLocalizedMessage());
                    Log.e("Error Getting Cam", sb.toString());
                }
            }
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(1, cameraInfo);
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int i5 = rotation != 1 ? rotation != 2 ? rotation != 3 ? 0 : 270 : 180 : 90;
        int i6 = ((cameraInfo.orientation - i5) + 360) % 360;
        Camera.Parameters parameters = this.b.getParameters();
        parameters.setRotation(i6);
        this.b.setParameters(parameters);
        this.b.setDisplayOrientation(90);
        this.b.startPreview();
        b();
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Camera camera = this.b;
        if (camera != null) {
            try {
                camera.setPreviewDisplay(this.g.getHolder());
            } catch (NullPointerException e2) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Camera surface error ");
                    sb.append(e2.getMessage());
                    Log.e("Error getting Cam", sb.toString());
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i("PREVIEW", "surfaceDestroyed");
    }


    public void end_call(View view) {
        pos = view;
//        if (adsdata != null && adsdata.size() > 0) {
//            admobinter = 1;
//            if (adsdata.get(0).getCheckadEndcallInter().equals("admob")) {
//                adprogress.show();
//                admob_inter();
//            } else if (adsdata.get(0).getCheckadEndcallInter().equals("off")) {
        view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.viewpush));
        VideoView videoView = this.i;
        if (videoView != null) {
            if (videoView.isPlaying()) {
                this.i.stopPlayback();
            }
            finish();
        }
//            } else {
//                view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.viewpush));
//                VideoView videoView = this.i;
//                if (videoView != null) {
//                    if (videoView.isPlaying()) {
//                        this.i.stopPlayback();
//                    }
//                    finish();
//                }
//            }
//        } else {
//            view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.viewpush));
//            VideoView videoView = this.i;
//            if (videoView != null) {
//                if (videoView.isPlaying()) {
//                    this.i.stopPlayback();
//                }
//                finish();
//            }
//        }
    }

    public void onBackPressed() {

//        if (adsdata != null && adsdata.size() > 0) {
//            admobinter = 2;
//            if (adsdata.get(0).getCheckadVideocallbackpressInter().equals("admob")) {
//                adprogress.show();
//                admob_inter();
//            } else if (adsdata.get(0).getCheckadVideocallbackpressInter().equals("off")) {
        if (this.a + 2000 > System.currentTimeMillis()) {
            VideoView videoView = this.i;
            if (videoView != null) {
                if (videoView.isPlaying()) {
                    this.i.stopPlayback();
                }
                finish();
            }
            startActivity(new Intent(PlayVideoCallActivity.this, VideoMeetingActivity.class));
        } else {
            Toast.makeText(getBaseContext(), "Video call session will end if you presss again", Toast.LENGTH_SHORT).show();
        }
        this.a = System.currentTimeMillis();
//            } else {
//                if (this.a + 2000 > System.currentTimeMillis()) {
//                    VideoView videoView = this.i;
//                    if (videoView != null) {
//                        if (videoView.isPlaying()) {
//                            this.i.stopPlayback();
//                        }
//                        finish();
//                    }
//                    startActivity(new Intent(Play_Video_Call_Activity.this, Reject_Video_CallActivity.class));
//                } else {
//                    Toast.makeText(getBaseContext(), "Video call session will end if you presss again", 0).show();
//                }
//                this.a = System.currentTimeMillis();
//            }
//        } else {
//            if (this.a + 2000 > System.currentTimeMillis()) {
//                VideoView videoView = this.i;
//                if (videoView != null) {
//                    if (videoView.isPlaying()) {
//                        this.i.stopPlayback();
//                    }
//                    finish();
//                }
//                startActivity(new Intent(Play_Video_Call_Activity.this, Reject_Video_CallActivity.class));
//            } else {
//                Toast.makeText(getBaseContext(), "Video call session will end if you presss again", 0).show();
//            }
//            this.a = System.currentTimeMillis();
//        }
    }

    public void a(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }


}
