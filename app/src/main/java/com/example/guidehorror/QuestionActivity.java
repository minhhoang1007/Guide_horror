package com.example.guidehorror;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guidehorror.model.AnswerModel;
import com.example.guidehorror.model.QuestionModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private String url = "assets/question.json";
    private RecyclerView mList;
    private List<QuestionModel> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        mList = findViewById(R.id.rcViewQues);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        getDataFromJson(getApplicationContext());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(layoutManager);
        QuestionAdapter adapter = new QuestionAdapter(this, movieList);
        mList.setAdapter(adapter);

        adapter.setOnClickLisener(new QuestionAdapter.OnClickLisener() {
            @Override
            public void onClick(QuestionModel data) {
                Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
                Log.e("aaaa", "onClick: " + data.toString());

                Bundle bundle = new Bundle();
                bundle.putSerializable("aaa", data);
                intent.putExtra("questionModel", bundle);
                startActivity(intent);
            }
        });

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

}
