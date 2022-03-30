package com.example.mydogapp.viewmodel;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydogapp.R;
import com.example.mydogapp.model.DogBreed;

import java.util.ArrayList;
import java.util.Objects;

public class DogBreedAdapter extends RecyclerView.Adapter<DogBreedAdapter.ViewHolder> {

    private ArrayList<DogBreed> dogs;
    private Context context;

    public DogBreedAdapter(Context context, ArrayList<DogBreed> dogs){
        this.dogs = dogs;
        this.context = context;
    }

    @NonNull
    @Override
    public DogBreedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DogBreed dog = this.dogs.get(position);
        dog.setStatus(false);
        Glide.with(context).load(dog.getUrl()).into(holder.ivImage);
        holder.tvName.setText(dog.getName());
        holder.tvOrigin.setText(String.format("%s %s", (dog.getOrigin() != null) ? dog.getOrigin():"Life", dog.getLifeSpan()));
        holder.ibFavor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dog.getStatus().equals(true)){
                    dog.setStatus(false);
                    holder.ibFavor.setImageResource(R.drawable.heart_sad);
                } else{
                    dog.setStatus(true);
                    holder.ibFavor.setImageResource(R.drawable.heart_happy);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.dogs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivImage;
        public TextView tvName;
        public CardView cvDog;
        public TextView tvOrigin;
        public ImageButton ibFavor;
        public ViewHolder(View view){
            super(view);
            cvDog = view.findViewById(R.id.cv_dog);
            ivImage = view.findViewById(R.id.iv_dog_img);
            tvName = view.findViewById(R.id.tv_dog_name);
            ibFavor = view.findViewById(R.id.ib_favor);
            tvOrigin = view.findViewById(R.id.tv_dog_origin);
        }
    }
}
