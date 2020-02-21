package com.example.guidehorror;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guidehorror.model.QuestionModel;

import java.util.List;

import static android.content.ContentValues.TAG;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private Context context;
    private List<QuestionModel> list;
    private OnClickLisener onClickLisener;

    public QuestionAdapter(Context context, List<QuestionModel> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnClickLisener(QuestionAdapter.OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_question, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, final int position) {
        QuestionModel questionModel = list.get(position);
        holder.question.setText(questionModel.getQuestion());
        holder.stt.setText((position + 1) + "");
        holder.question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: " + onClickLisener!= null ? "true":"false" );
                if(onClickLisener!= null)
                onClickLisener.onClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView question;
        public TextView stt;

        public ViewHolder(View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.txtquestion);
            stt = itemView.findViewById(R.id.stt);

        }
    }

    interface OnClickLisener {
        void onClick(QuestionModel data);

    }

}
