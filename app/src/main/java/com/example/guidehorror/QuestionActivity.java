package com.example.guidehorror;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guidehorror.model.AnswerModel;
import com.example.guidehorror.model.QuestionModel;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.security.auth.login.LoginException;

public class QuestionActivity extends AppCompatActivity {
    private String url = "assets/question.json";
    private RecyclerView mList;
    private List<QuestionModel> movieList;
    ImageView img;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;
    View loading;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(android.R.color.black));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.black));
        setContentView(R.layout.activity_question);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ads_inter));
        loading = (View) findViewById(R.id.loading);
        mList = findViewById(R.id.rcViewQues);
        img = (ImageView) findViewById(R.id.backques);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        getDataFromJson(getApplicationContext());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(layoutManager);
        QuestionAdapter adapter = new QuestionAdapter(this, movieList);
        mList.setAdapter(adapter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
        adapter.setOnClickLisener(new QuestionAdapter.OnClickLisener() {
            @Override
            public void onClick(QuestionModel data) {
                Intent intent = new Intent(getApplicationContext(), AnswerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ques", data.getQuestion());
                bundle.putString("user",data.getAnswer().get(0).getUser());
                bundle.putString("answer",data.getAnswer().get(0).getValue());
                //Toast.makeText(QuestionActivity.this,data.getAnswer().get(0).getUser() , Toast.LENGTH_SHORT).show();
                intent.putExtra("question", bundle);
                intentToScreenAds(intent);
            }
        });

    }
    private void intentToScreenAds(final Intent guideintent) {
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdOpened() {
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                loading.setVisibility(View.GONE);
                startActivity(guideintent);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startActivity(guideintent);
            }
        });
        loading.setVisibility(View.VISIBLE);
        int random = new Random().nextInt(3);
        if (random == 1) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        } else {
            loading.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loading.setVisibility(View.GONE);
                    startActivity(guideintent);
                }
            }, 1000);
        }

    }

    private void getDataFromJson(Context context) {
        Type type2 = new TypeToken<ArrayList<QuestionModel>>() {
        }.getType();
        movieList = new Gson().fromJson(loadJSONFromAsset(this, "question"), type2);

    }

    public String loadJSONFromAsset(Context context, String name) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(name + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
