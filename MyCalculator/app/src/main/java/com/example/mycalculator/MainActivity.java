package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mycalculator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean checkInit = false;
    boolean checkCal = false;
    boolean checkDot = false;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnNum0.setOnClickListener(view0 -> clickNum("0"));
        binding.btnNum1.setOnClickListener(view1 -> clickNum("1"));
        binding.btnNum2.setOnClickListener(view2 -> clickNum("2"));
        binding.btnNum3.setOnClickListener(view3 -> clickNum("3"));
        binding.btnNum4.setOnClickListener(view4 -> clickNum("4"));
        binding.btnNum5.setOnClickListener(view5 -> clickNum("5"));
        binding.btnNum6.setOnClickListener(view6 -> clickNum("6"));
        binding.btnNum7.setOnClickListener(view7 -> clickNum("7"));
        binding.btnNum8.setOnClickListener(view8 -> clickNum("8"));
        binding.btnNum9.setOnClickListener(view9 -> clickNum("9"));
        binding.btnCE.setOnClickListener(viewCE -> reset(true));
        binding.btnC.setOnClickListener(viewC -> reset(false));
        binding.btnDel.setOnClickListener(viewDel -> {
            try {
                String txt = binding.lvCal.getText().toString().trim();
                if (String.valueOf(txt.charAt(txt.length()-1)).equals(" ")){
                    binding.lvCal.setText(txt.substring(0, txt.length()-3).trim());
                } else
                    binding.lvCal.setText(txt.substring(0, txt.length()-1).trim());
            }catch(Exception e){
                binding.lvCal.setText("");
            }
        });
        binding.btnAdd.setOnClickListener(viewAdd -> clickCal("+"));
        binding.btnSub.setOnClickListener(viewSub -> clickCal("-"));
        binding.btnMul.setOnClickListener(viewMul -> clickCal("x"));
        binding.btnDiv.setOnClickListener(viewDiv -> clickCal("/"));
        binding.btnDot.setOnClickListener(viewDot -> clickDot());
        binding.btnResult.setOnClickListener(viewRes -> {
            if(checkCal) {
                List<String> L = new ArrayList<>();
                binding.lvCache.setText(binding.lvCal.getText().toString().trim());
                Collections.addAll(L, binding.lvCal.getText().toString().trim().split(" "));
                binding.lvCal.setText(String.valueOf((double)Math.round(calculating(L, 1) * 100)/100));
                checkInit = false;
            }
        });
    }

    private double calculating(List<String> x, int a){
        if(x.get(a).equals("+") || x.get(a).equals("-")) {
            if ((a + 2) < x.size()) {
                if (x.get(a).equals("-"))
                    x.set(a + 1, String.valueOf(-Double.parseDouble(x.get(a + 1))));
                return Double.parseDouble(x.get(a - 1)) + calculating(x, a + 2);
            } else {
                if (x.get(a).equals("+"))
                    return Double.parseDouble(x.get(a - 1)) + Double.parseDouble(x.get(a + 1));
                if (x.get(a).equals("-"))
                    return Double.parseDouble(x.get(a - 1)) - Double.parseDouble(x.get(a + 1));
            }
        }
        if (x.get(a).equals("x") || x.get(a).equals("/")) {
            if (x.get(a).equals("x"))
                x.set(a - 1, String.valueOf(Double.parseDouble(x.get(a - 1)) * Double.parseDouble(x.get(a + 1))));
            if (x.get(a).equals("/"))
                x.set(a - 1, String.valueOf(Double.parseDouble(x.get(a - 1)) / Double.parseDouble(x.get(a + 1))));
            if ((a + 2) < x.size()) {
                x.remove(a);
                x.remove(a);
                return calculating(x, a);
            }
            else
                return Double.parseDouble(x.get(a - 1));
        }
        return -1;
    }
    private void clickCal(String sign) {
        if(checkCal){
            String s = binding.lvCal.getText() + " " + sign + " ";
            binding.lvCal.setText(s);
            checkCal = false;
            checkDot = false;
            checkInit = true;
        }
    }
    private void clickDot(){
        if(!checkDot && checkCal){
            checkDot = true;
            String s = binding.lvCal.getText() + ".";
            binding.lvCal.setText(s);
        }
    }
    private void clickNum(String strNum){
        if(!checkInit){
            checkInit = true;
            checkCal = true;
            binding.lvCal.setText("");
        } else {
            checkCal = true;
        }
        String s = binding.lvCal.getText() + strNum;
        binding.lvCal.setText(s);
    }
    private void reset(boolean checkReset){
        if(checkReset){
            binding.lvCache.setText("");
        }
        checkInit = false;
        binding.lvCal.setText("0");
    }

}