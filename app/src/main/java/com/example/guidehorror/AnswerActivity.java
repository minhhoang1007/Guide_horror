package com.example.guidehorror;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guidehorror.model.QuestionModel;

public class AnswerActivity extends AppCompatActivity {
    QuestionModel questionModel;
    TextView txtuser, txtans, txtQues;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        txtuser = (TextView) findViewById(R.id.txtUser);
        txtans = (TextView) findViewById(R.id.txtValue);
        txtQues = (TextView) findViewById(R.id.txtQues);

        intent = getIntent();
        Bundle bundle = intent.getBundleExtra("questionModel");


        questionModel = (QuestionModel) bundle.get("aaa");
//        Toast.makeText(this, questionModel.toString(), Toast.LENGTH_SHORT).show();
        Log.e("aaaa", "onCreate: " + intent.getIntExtra("questionModel", -1) );
//        txtuser.setText(questionModel.getAnswer().get(0).getUser());
//        txtQues.setText(questionModel.getQuestion());
//        txtans.setText(questionModel.getAnswer().get(0).getValue());

    }
}
