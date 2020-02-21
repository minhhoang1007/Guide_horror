package com.example.guidehorror;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnGui, btnQues, btnAbout, btnMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGui = (Button) findViewById(R.id.btnGuide);
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGuideActivity();
            }
        });
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutActivity();
            }
        });
        btnMore = (Button) findViewById(R.id.btnMore);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMoreActivity();
            }
        });
        btnQues = (Button) findViewById(R.id.btnQuestion);
        btnQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestionActivity();
            }
        });
    }
    public void openGuideActivity(){
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
    }
    public void openAboutActivity(){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
    public void openMoreActivity(){
        Intent intent = new Intent(this, MoreActivity.class);
        startActivity(intent);
    }
    public void openQuestionActivity(){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

}
