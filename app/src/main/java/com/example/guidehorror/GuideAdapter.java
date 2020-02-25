package com.example.guidehorror;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guidehorror.model.GuideModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {
    List<GuideModel> list;
   private Context mcontext;
   private OnClickLisener onClickLisener;
   public GuideAdapter(Context context, ArrayList<GuideModel> list){
       this.list = list;
       this.mcontext = context;
   }
    public void setOnClickLisener(GuideAdapter.OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewGuide = inflater.inflate(R.layout.item_guide, parent, false);
        return new ViewHolder(viewGuide);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        GuideModel guideModel = list.get(position);
        holder.title.setText(guideModel.getName());
        Bitmap bitmap = null;
        try {
            bitmap = loadBitmapImage(holder.itemView.getContext(), guideModel.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.image.setImageBitmap(bitmap);

        holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onClickLisener.onClick(list.get(position));
                    }
                });
    }



    @Override
    public int getItemCount() {
        return list.size();
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
    interface OnClickLisener {
        void onClick(GuideModel data);

    }
    public Bitmap loadBitmapImage(Context context, String name) throws IOException {
        AssetManager assetManager = context.getAssets();

        InputStream istr = assetManager.open(name);
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        return bitmap;
    }
}
