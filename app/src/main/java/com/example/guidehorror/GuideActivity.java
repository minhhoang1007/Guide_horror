package com.example.guidehorror;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guidehorror.model.GuideModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    //    private ArrayList<String> mtitle = new ArrayList<>();
//    private ArrayList<Integer> mimg = new ArrayList<>();
    private String url = "assets/data.json";
    private RecyclerView mList;
    private List<GuideModel> movieList;
    AdView mAdView;
    ImageView img;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(android.R.color.black));
        window.setNavigationBarColor(this.getResources().getColor(android.R.color.black));
        setContentView(R.layout.activity_guide);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mList = findViewById(R.id.rcViewGui);
        img = (ImageView) findViewById(R.id.backgui);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        getDataFromJson(getApplicationContext());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(layoutManager);
        GuideAdapter adapter = new GuideAdapter(this, (ArrayList<GuideModel>) movieList);
        mList.setAdapter(adapter);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
        adapter.setOnClickLisener(new GuideAdapter.OnClickLisener() {
            @Override
            public void onClick(GuideModel data) {
                Intent intent = new Intent(GuideActivity.this, DetailGuideActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", data.getName());
                bundle.putString("img1", data.getDescription().get(0));
                bundle.putString("img2", data.getDescription().get(2));
                bundle.putString("img3", data.getDescription().get(4));
                bundle.putString("txt1", data.getDescription().get(1));
                bundle.putString("txt2", data.getDescription().get(3));

                //Toast.makeText(GuideActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }

    private void getDataFromJson(Context context) {
        Type type1 = new TypeToken<ArrayList<GuideModel>>() {
        }.getType();
        movieList = new Gson().fromJson(loadJSONFromAsset(this, "data"), type1);

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
