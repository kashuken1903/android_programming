package com.example.mydogapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mydogapp.R;
import com.example.mydogapp.model.DogBreed;

import java.util.ArrayList;

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
        holder.ibFavor.setOnClickListener(view -> {
            if(dog.getStatus().equals(true)){
                dog.setStatus(false);
                holder.ibFavor.setImageResource(R.drawable.heart_sad);
            } else{
                dog.setStatus(true);
                holder.ibFavor.setImageResource(R.drawable.heart_happy);
            }
        });
    }
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<DogBreed> filterList) {
        dogs = filterList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.dogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
            view.setOnClickListener(view1 -> {
                DogBreed dog = dogs.get(getAdapterPosition());
                Bundle bundle = new Bundle();
                bundle.putSerializable("dog", dog);
                Navigation.findNavController(view1).navigate(R.id.detailFragment, bundle);
            });
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
        }
    }
}
