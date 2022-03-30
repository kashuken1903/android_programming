package com.example.mydogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mydogapp.databinding.ActivityMainBinding;
import com.example.mydogapp.model.DogBreed;
import com.example.mydogapp.viewmodel.DogBreedAdapter;
import com.example.mydogapp.viewmodel.DogsService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private DogsService dogsService;
    private ArrayList<DogBreed> dogsList;
    private DogBreedAdapter dogsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dogsList = new ArrayList<>();
        binding.rvView.setLayoutManager(new GridLayoutManager(this, 2));

        dogsService = new DogsService();
        dogsService.getAllDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        dogsList.clear();
                        dogsList.addAll(dogBreeds);
                        dogsAdapter = new DogBreedAdapter(MainActivity.this, dogsList);
                        binding.rvView.setAdapter(dogsAdapter);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Dog ", e.getMessage());
                    }
                });
    }
}