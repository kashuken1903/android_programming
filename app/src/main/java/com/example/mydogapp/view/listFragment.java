package com.example.mydogapp.view;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydogapp.R;
import com.example.mydogapp.model.DogBreed;
import com.example.mydogapp.viewmodel.AppDatabase;
import com.example.mydogapp.viewmodel.DogBreedAdapter;
import com.example.mydogapp.viewmodel.DogsDao;
import com.example.mydogapp.viewmodel.DogsService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class listFragment extends Fragment {

    private DogsService dogsService;
    private RecyclerView rvDogs;
    private ArrayList<DogBreed> dogsList;
    private DogBreedAdapter dogsAdapter;
    private AppDatabase appDatabase;
    private DogsDao dogsDao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvDogs = view.findViewById(R.id.rv_dogs);
        appDatabase = AppDatabase.getInstance(getContext());
        dogsDao = appDatabase.dogsDao();
        dogsList = new ArrayList<>();
        dogsAdapter = new DogBreedAdapter(getContext(), dogsList);
        rvDogs.setAdapter(dogsAdapter);
        rvDogs.setLayoutManager(new GridLayoutManager(getContext(), 2));

        dogsService = new DogsService();
        dogsService.getAllDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<DogBreed> dogBreeds) {
                        AsyncTask.execute(() -> {
                            for (DogBreed dog: dogBreeds) {
                                dogsDao.insert(dog);
                            }
                        });
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("Dog ", e.getMessage());
                    }
                });
        dogsDao.getAllDogs().observe(getViewLifecycleOwner(), dogBreeds -> {
            dogsList.clear();
            dogsList.addAll(dogBreeds);
            dogsAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String text) {
        ArrayList<DogBreed> filteredList = new ArrayList<>();
        for (DogBreed item : dogsList) {
            String fullName = item.getName();
            if (fullName.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (!filteredList.isEmpty()) {
            dogsAdapter.filterList(filteredList);
        }
    }
}