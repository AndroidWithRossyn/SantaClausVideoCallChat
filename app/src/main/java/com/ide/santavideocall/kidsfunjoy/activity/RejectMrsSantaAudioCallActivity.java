package com.ide.santavideocall.kidsfunjoy.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ide.santavideocall.kidsfunjoy.R;
import com.ide.santavideocall.kidsfunjoy.drf;
import com.pesonal.adsdk.AppManage;

public class RejectMrsSantaAudioCallActivity extends AppCompatActivity {
    long k = 0;
    MediaPlayer m;
    String n = "1";
    drf l = new drf(this);
    private Dialog adprogress;
    private View pos;
    MediaPlayer audioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_reject_mrs_santa_audio_call);
        AppManage.getInstance(RejectMrsSantaAudioCallActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(RejectMrsSantaAudioCallActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        StringBuilder sb = new StringBuilder();
        sb.append("");
//        this.audioPlayer = MediaPlayer.create(this, this.songsItemListToPlay[Advertise.getAudioSelectionId(this).intValue() - 1]);
        sb.append(this.l.b());
        this.n = sb.toString();
        l();
        this.m = MediaPlayer.create(this, R.raw.fb_messenger_tone);
        this.m.start();
        this.m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                RejectMrsSantaAudioCallActivity.this.m.start();
                RejectMrsSantaAudioCallActivity.this.m();
            }
        });
        m();
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

    public void l() {
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.mrs_claus);
        ImageView imageView = (ImageView) findViewById(R.id.ImageView02);
        TextView textView = (TextView) findViewById(R.id.textView1);
        if (this.n.equalsIgnoreCase("1")) {
            textView.setText("Holly");
            imageView.setImageBitmap(a(decodeResource, 100));
        }
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        if (!(bitmap.getWidth() == i && bitmap.getHeight() == i)) {
            bitmap = Bitmap.createScaledBitmap(bitmap, i, i, false);
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(((float) (bitmap.getWidth() / 2)) + 0.7f, ((float) (bitmap.getHeight() / 2)) + 0.7f, ((float) (bitmap.getWidth() / 2)) + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public void accept_call(View view) {
        AppManage.getInstance(RejectMrsSantaAudioCallActivity.this).showInterstitialAd(RejectMrsSantaAudioCallActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                pos = view;
                k();
                startActivity(new Intent(view.getContext(), PlayMrsSantaAudioCallActivity.class));
                finish();
            }
        }, "", AppManage.app_mainClickCntSwAd);


    }

    public void reject_call(View view) {
        AppManage.getInstance(RejectMrsSantaAudioCallActivity.this).showInterstitialAd(RejectMrsSantaAudioCallActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                k();
                finish();
            }
        }, "", AppManage.app_mainClickCntSwAd);


    }

    @SuppressLint("WrongConstant")
    public void onBackPressed() {

        if (this.k + 2000 > System.currentTimeMillis()) {
            k();
            finish();
            startActivity(new Intent(RejectMrsSantaAudioCallActivity.this, DashboardActivity.class));
        } else {
            Toast.makeText(getBaseContext(), "Call will decline if you press again", 0).show();
        }
        this.k = System.currentTimeMillis();
    }

    @SuppressLint("WrongConstant")
    public void a(String str) {
        Toast.makeText(getApplicationContext(), str, 1).show();
    }
}
