package com.example.layoutexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.layoutexample.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<String> listNums;
    ArrayAdapter<String> adpNums;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        listNums = new ArrayList<String>();
        adpNums = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNums);
        binding.lvHis.setAdapter(adpNums);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = Double.parseDouble(binding.edtNum1.getText().toString());
                    double b = Double.parseDouble(binding.edtNum2.getText().toString());
                    listNums.add(String.valueOf(a+b));
                    adpNums.notifyDataSetChanged();
                }catch (Exception e){

                }
            }
        });
        binding.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = Double.parseDouble(binding.edtNum1.getText().toString());
                    double b = Double.parseDouble(binding.edtNum2.getText().toString());
                    listNums.add(String.valueOf(a-b));
                    adpNums.notifyDataSetChanged();
                }catch (Exception e){

                }
            }
        });
        binding.btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = Double.parseDouble(binding.edtNum1.getText().toString());
                    double b = Double.parseDouble(binding.edtNum2.getText().toString());
                    listNums.add(String.valueOf(a*b));
                    adpNums.notifyDataSetChanged();
                }catch (Exception e){

                }
            }
        });
        binding.btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double a = Double.parseDouble(binding.edtNum1.getText().toString());
                    double b = Double.parseDouble(binding.edtNum2.getText().toString());
                    listNums.add(String.valueOf(a/b));
                    adpNums.notifyDataSetChanged();
                }catch (Exception e){

                }
            }
        });

    }
}