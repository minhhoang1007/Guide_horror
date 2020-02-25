package com.example.guidehorror;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guidehorror.model.QuestionModel;
import com.example.guidehorror.utils.BannerUltils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AnswerActivity extends AppCompatActivity {
    TextView txtuser, txtans, txtQues;
    ImageView img;
    AdView mAdView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(android.R.color.black));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.black));
        setContentView(R.layout.activity_answer);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.setAdListener(new BannerUltils().adListener(mAdView,1));
        mAdView.loadAd(adRequest);
        txtuser = (TextView) findViewById(R.id.txtUser);
        txtans = (TextView) findViewById(R.id.txtValue);
        txtQues = (TextView) findViewById(R.id.txtQues);
        img = (ImageView) findViewById(R.id.backanswer);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("question");
        String ques = bundle.getString("ques");
        String user = bundle.getString("user");
        String answer = bundle.getString("answer");
        //Toast.makeText(this, ques, Toast.LENGTH_SHORT).show();
        if (bundle != null) {
            txtQues.setText(ques);
            txtuser.setText(user + ":");
            txtans.setText(answer);
        } else {
            txtQues.setText("Error");
        }

    }
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
