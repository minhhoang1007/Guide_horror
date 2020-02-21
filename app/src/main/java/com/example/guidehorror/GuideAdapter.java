package com.example.guidehorror;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {
   private ArrayList<String> mtitle;
   private ArrayList<Integer> mimage;
   private Context mcontext;
   public GuideAdapter(Context context, ArrayList<String> title, ArrayList<Integer> img){

       mtitle = title;
       mimage = img;
       mcontext = context;

   }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewGuide = inflater.inflate(R.layout.item_guide, parent, false);
        return new ViewHolder(viewGuide);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mcontext)
                .asBitmap()
                .load(mimage.get(position))
                .into(holder.image);
        holder.title.setText(mtitle.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext, "abc", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return mimage.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;

        public ViewHolder(@NonNull final View itemView){
            super(itemView);
            title =itemView.findViewById(R.id.titleGuide);
            image = itemView.findViewById(R.id.imgguide);

        }
    }
}
