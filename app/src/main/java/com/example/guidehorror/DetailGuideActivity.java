package com.example.guidehorror;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.io.InputStream;

public class DetailGuideActivity extends AppCompatActivity {
    AdView mAdView;
    TextView txtGui1,txtGui2,txtGui3, txtguitit;
    ImageView imgGui1,imgGui2,imgGui3, backdetail;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(android.R.color.black));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.black));
        setContentView(R.layout.activity_detail_guide);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        txtGui1 = (TextView) findViewById(R.id.txtGui1);
        txtGui2 = (TextView) findViewById(R.id.txtGui2);
        txtGui3 = (TextView) findViewById(R.id.txtGui3);
        imgGui1 = (ImageView) findViewById(R.id.imgGui1);
        imgGui2 = (ImageView) findViewById(R.id.imgGui2);
        imgGui3 = (ImageView) findViewById(R.id.imgGui3);
        backdetail = (ImageView) findViewById(R.id.backdetail);
        backdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
        txtguitit = (TextView) findViewById(R.id.txtGuititle);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("data");
        String txt1 = bundle.getString("title");
        String txt2 = bundle.getString("txt1");
        String txt3 = bundle.getString("txt2");
        String img1 = bundle.getString("img1");
        String img2 = bundle.getString("img2");
        String img3 = bundle.getString("img3");
        Bitmap bitmap1 = null;
        Bitmap bitmap2 = null;
        Bitmap bitmap3 = null;
        try {
            bitmap1 = loadBitmapImage(this, img1);
            bitmap2 = loadBitmapImage(this, img2);
            bitmap3 = loadBitmapImage(this, img3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(bundle != null){
            txtguitit.setText(txt1);
            txtGui2.setText(txt2);
            txtGui3.setText(txt3);
           imgGui1.setImageBitmap(bitmap1);
            imgGui2.setImageBitmap(bitmap2);
            imgGui3.setImageBitmap(bitmap3);
        }else{
            txtGui1.setText("Error");
        }
    }
    public Bitmap loadBitmapImage(Context context, String name) throws IOException {
        AssetManager assetManager = context.getAssets();

        InputStream istr = assetManager.open(name);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        return bitmap;
    }
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
