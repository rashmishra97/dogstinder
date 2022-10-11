package com.example.dogstinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

      private String model_img;
      private Context context;

    public MyAdapter(String model_img, Context context) {
        this.model_img = model_img;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_layout, parent, false);
        return new MyViewHolder(view_item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dog_image.setImageResource(Integer.parseInt(model_img));
        //Picasso.get().load("https://images.dog.ceo//breeds//poodle-miniature//"+model_img).into(holder.dog_image);
        Glide.with(context).load("https://images.dog.ceo/breeds/bullterrier-staffordshire/n02093256_1134.jpg").into(holder.dog_image);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView dog_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            dog_image =  itemView.findViewById(R.id.dog_image);
        }


    }

}
