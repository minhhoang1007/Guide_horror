package com.example.guidehorror;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.guidehorror.utils.SharedPrefsUtils;
import com.example.ratedialog.RatingDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;

import java.util.Random;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements RatingDialog.RatingDialogInterFace{
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    Button btnGui, btnQues, btnAbout, btnMore;
    ImageView btnShare;
    View loading;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set statusbar
        Window window = this.getWindow();
        Drawable background = this.getResources().getDrawable(R.drawable.banner1);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(android.R.color.transparent));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.transparent));
        window.setBackgroundDrawable(background);
        setContentView(R.layout.activity_main);
        rateAuto();
        MobileAds.initialize(this, getString(R.string.app_ads_id));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ads_inter));

        loading = (View) findViewById(R.id.loading);
        btnShare = (ImageView) findViewById(R.id.btnShare);
        btnGui = (Button) findViewById(R.id.btnGuide);

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               intentToScreenAds(GuideActivity.class);
            }
        });
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToScreenAds(AboutActivity.class);
            }
        });
        btnMore = (Button) findViewById(R.id.btnMore);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToScreenAds(MoreActivity.class);
            }
        });
        btnQues = (Button) findViewById(R.id.btnQuestion);
        btnQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               intentToScreenAds(QuestionActivity.class);
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "com.example.guidehorror";
                //String shareSub = "Your subject here";
                //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "http://play.google.com/store/apps/details?id=" + shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });
    }
    private void intentToScreenAds(final Class guideClass) {
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
                openActivity(guideClass);
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                openActivity(guideClass);
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
                    openActivity(guideClass);
                }
            }, 1000);
        }

    }
    public void openActivity(final Class Name) {
        Intent intent = new Intent(this, Name);
        startActivity(intent);

    }
    void moveToNewApp(String appId) {
        Intent intent = new Intent(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + appId)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

//    void open(String magnet) {
//        this.magnet = magnet;
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
//        browserIntent.setData(Uri.parse(magnet));
//        try {
//            startActivity(browserIntent);
//        } catch (ActivityNotFoundException ex) {
//            Log.d("dddddd", "abcd");
//        }
//    }

    void goToMarket() {
        Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("market://search?q=torrent clients"));
        startActivity(goToMarket);
    }

    public static void rateApp(Context context) {
        Intent intent = new Intent(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void rateAuto() {
        int rate = SharedPrefsUtils.getInstance(this).getInt("rate");
        if (rate < 1) {
            RatingDialog ratingDialog = new RatingDialog(this);
            ratingDialog.setRatingDialogListener((RatingDialog.RatingDialogInterFace) this);
            ratingDialog.showDialog();
            SharedPrefsUtils.getInstance(this).putInt("rate", 5);

        }
    }

    void rateManual() {
        RatingDialog ratingDialog = new RatingDialog(this);
        ratingDialog.setRatingDialogListener(this);
        ratingDialog.showDialog();
    }

    @Override
    public void onDismiss() {
    }

    @Override
    public void onSubmit(float rating) {
        if (rating > 3) {
            rateApp(this);
            SharedPrefsUtils.getInstance(this).putInt("rate", 5);
        }
    }

    @Override
    public void onRatingChanged(float rating) {
    }
}
