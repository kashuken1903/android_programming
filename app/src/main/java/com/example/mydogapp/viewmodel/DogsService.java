package com.example.mydogapp.viewmodel;

import com.example.mydogapp.model.DogBreed;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsService {

    private static final String BASE_URL = "https://raw.githubusercontent.com/";
    private static DogsApi api;

    public DogsService(){
        if(api == null){
            api = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                    .create(DogsApi.class);
        }
    }

    public Single<List<DogBreed>> getAllDogs(){
        return api.getAllDogs();
    }
}
