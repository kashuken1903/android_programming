package com.example.mydogapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydogapp.R;
import com.example.mydogapp.databinding.FragmentDetailBinding;
import com.example.mydogapp.model.DogBreed;

public class detailFragment extends Fragment {

    private DogBreed dog;
    FragmentDetailBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dog = (DogBreed) getArguments().getSerializable("dog");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_detail, null, false);
        View viewRoot = binding.getRoot();
        binding.setDog(dog);
        return viewRoot;
    }
}