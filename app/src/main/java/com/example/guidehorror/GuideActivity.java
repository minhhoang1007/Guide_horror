package com.example.guidehorror;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {
    private ArrayList<String> mtitle = new ArrayList<>();
    private ArrayList<Integer> mimg = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //getSupportActionBar().setTitle("Ice Scream: Horror Neighborhood");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initImageBitmap();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.rcViewGui);
        recyclerView.setLayoutManager(layoutManager);
        GuideAdapter adapter = new GuideAdapter(this, mtitle, mimg);
        recyclerView.setAdapter(adapter);
    }

    private void initImageBitmap(){
        mtitle.add("1.Choose A Preferred Playing Mode");
        mimg.add(R.drawable.guide110);
        mtitle.add("2.Stay Out Of Sight And Be Quiet At All Times");
        mimg.add(R.drawable.guide120);
        mtitle.add("3.Watch Videos To Receive Hints And Clues");
        mimg.add(R.drawable.guide130);
        mtitle.add("4.Use The Map To Travel From One Location To The Other");
        mimg.add(R.drawable.guide140);
        mtitle.add("5.First Go To The Cafeteria");
        mimg.add(R.drawable.guide150);
        mtitle.add("6.Explore The Playground And Find The Guard’s Jacket");
        mimg.add(R.drawable.guide160);
        mtitle.add("7.Visit The Parking Lot And Unlock The Office Room");
        mimg.add(R.drawable.guide170);
        mtitle.add("8.Escape From The Stall If You Get Caught");
        mimg.add(R.drawable.guide180);

    }
}
