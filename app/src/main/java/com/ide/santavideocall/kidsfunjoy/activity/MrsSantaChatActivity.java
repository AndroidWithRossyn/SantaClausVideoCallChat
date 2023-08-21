package com.ide.santavideocall.kidsfunjoy.activity;

import static com.pesonal.adsdk.AppManage.ADMOB_N;
import static com.pesonal.adsdk.AppManage.FACEBOOK_N;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ide.santavideocall.kidsfunjoy.R;
import com.ide.santavideocall.kidsfunjoy.adapter.MrsSantaChatAdapter;
import com.pesonal.adsdk.AppManage;

import java.util.ArrayList;

public class MrsSantaChatActivity extends AppCompatActivity {
    MrsSantaChatAdapter adapter;
    ArrayList<Chat> chatArrayList;
    boolean check = false;
    int count = 0;

    ImageView img_call;
    ImageView img_chat_refresh;

    MediaPlayer mediaPlayer;
    RecyclerView recyclerView;
    FloatingActionButton send_msg_txt;
    String[] sender = {"Hello,... ! ☺", "What's your name 😍 😍 ?", "Nice to meet you %s, I am %s , do you know me ? 😊", "How old are you 😊 ?", "Have you been naughty or nice this year ? ☺", "Oh ho ho ho ho fantastic ☺ ☺", "Where do you live 😍 😍 ?", "Now tell me what do you want for Christmas ? 😍 ☺", "Say it again ? ☺ 😍 😍 ", "Oh Okay, Got it ? 💖 😍", "I will see you soon ☺ 😍", "Do you Like this Application ?💖 😍", "Rate your experience and leave a comment. ☺ 😍 😍 \" ☺ 😍 😍"};
    Toolbar toolbar;
    int finalcount = this.sender.length;
    EditText txt_view_msg_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mrs_santa_chat);
        AppManage.getInstance(MrsSantaChatActivity.this).loadInterstitialAd(this);
        AppManage.getInstance(MrsSantaChatActivity.this).showNative((ViewGroup) findViewById(R.id.native_ads), ADMOB_N[0], FACEBOOK_N[0]);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        findViewById();
        setSupportActionBar(this.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        this.mediaPlayer = MediaPlayer.create(this, R.raw.ring_msg);
        this.chatArrayList = new ArrayList<>();
        this.chatArrayList.add(new Chat(2, this.sender[this.count]));
        this.adapter = new MrsSantaChatAdapter(this, this.chatArrayList);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.recyclerView.setAdapter(this.adapter);
        this.send_msg_txt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View view) {
                final String obj = MrsSantaChatActivity.this.txt_view_msg_txt.getText().toString();
                if (obj.isEmpty()) {
                    Toast.makeText(MrsSantaChatActivity.this, "Empty Message", 0).show();
                    return;
                }
                MrsSantaChatActivity.this.count++;
                if (MrsSantaChatActivity.this.count == MrsSantaChatActivity.this.finalcount) {
                    MrsSantaChatActivity chatActivity = MrsSantaChatActivity.this;
                    chatActivity.check = true;
                    AlertDialog create = new AlertDialog.Builder(chatActivity).create();
                    create.setMessage("How was your experience with us?");
                    create.setButton(-1, (CharSequence) "Rate us", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            StringBuilder sb = new StringBuilder();
                            sb.append("market://details?id=");
                            sb.append(MrsSantaChatActivity.this.getPackageName());
                            intent.setData(Uri.parse(sb.toString()));
                            MrsSantaChatActivity.this.startActivity(intent);
                        }
                    });
                    create.setButton(-2, (CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    create.show();
                } else if (!MrsSantaChatActivity.this.check) {
                    MrsSantaChatActivity.this.chatArrayList.add(new Chat(1, obj));
                    MrsSantaChatActivity.this.txt_view_msg_txt.setText("");
                    MrsSantaChatActivity.this.adapter.notifyDataSetChanged();
                    MrsSantaChatActivity.this.recyclerView.post(new Runnable() {
                        public void run() {
                            MrsSantaChatActivity.this.recyclerView.smoothScrollToPosition(MrsSantaChatActivity.this.adapter.getItemCount() - 1);
                        }
                    });
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (MrsSantaChatActivity.this.count == 2) {
                                ArrayList<Chat> arrayList = MrsSantaChatActivity.this.chatArrayList;
                                StringBuilder sb = new StringBuilder();
                                sb.append("Nice to meet you ");
                                sb.append(obj);
                                sb.append(", I am Mrs santa claus , do you know me  ? 😁 😊");
                                arrayList.add(new Chat(2, sb.toString()));
                            } else {
                                MrsSantaChatActivity.this.chatArrayList.add(new Chat(2, MrsSantaChatActivity.this.sender[MrsSantaChatActivity.this.count]));
                            }
                            MrsSantaChatActivity.this.adapter.notifyDataSetChanged();
                            MrsSantaChatActivity.this.mediaPlayer.start();
                            MrsSantaChatActivity.this.recyclerView.post(new Runnable() {
                                public void run() {
                                    MrsSantaChatActivity.this.recyclerView.smoothScrollToPosition(MrsSantaChatActivity.this.adapter.getItemCount() - 1);
                                }
                            });
                        }
                    }, 500);
                } else {
                    MrsSantaChatActivity.this.img_chat_refresh.performClick();
                }
            }
        });
        this.img_chat_refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog create = new AlertDialog.Builder(MrsSantaChatActivity.this).create();
                create.setMessage("Do you want to Replay Chat ?");
                create.setButton(-1, (CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MrsSantaChatActivity.this.count = 0;
                        MrsSantaChatActivity.this.chatArrayList.clear();
                        MrsSantaChatActivity.this.chatArrayList.add(new Chat(2, MrsSantaChatActivity.this.sender[MrsSantaChatActivity.this.count]));
                        MrsSantaChatActivity.this.adapter.notifyDataSetChanged();
                        MrsSantaChatActivity.this.check = false;
                    }
                });
                create.setButton(-2, (CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                create.show();
            }
        });
    }

    private void findViewById() {
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.txt_view_msg_txt = (EditText) findViewById(R.id.txt_view_msg_txt);
        this.send_msg_txt = (FloatingActionButton) findViewById(R.id.send_msg_txt);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.img_chat_refresh = (ImageView) findViewById(R.id.img_chat_refresh);
        this.img_call = (ImageView) findViewById(R.id.img_call);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    public void onBackPressed() {
        AppManage.getInstance(MrsSantaChatActivity.this).showInterstitialAd(MrsSantaChatActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                MrsSantaChatActivity.super.onBackPressed();
                overridePendingTransition(R.anim.back_slide_in, R.anim.back_slide_out);
            }
        }, "", AppManage.app_mainClickCntSwAd);



    }


}