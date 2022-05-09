package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mycontact.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;
    private AppDatabase appDatabase;
    private ContactDao contactDao;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        appDatabase = AppDatabase.getInstance(this);
        contactList = new ArrayList<>();
        contactDao = appDatabase.contactDao();

        contactAdapter = new ContactAdapter(contactList, this);
        binding.rvContact.setAdapter(contactAdapter);
        binding.rvContact.setLayoutManager(new LinearLayoutManager(this));
        AsyncTask.execute(() -> {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.ic_baseline_person_300);
            Contact ct1 = new Contact("Doan Chi", "Hoang", "a@gmail.com", "123456", uri.toString());
            Contact ct2 = new Contact("Le Thanh", "Nhan", "b@gmail.com", "123456", uri.toString());
            Contact ct3 = new Contact("Tran Tan", "Phat", "c@gmail.com", "123456", uri.toString());
//            contactDao.insertAll(ct1, ct2, ct3);
        });

        contactDao.getAllContacts().observe(this, contacts -> {
            contactList.clear();
            contactList.addAll(contacts);
            contactAdapter.notifyDataSetChanged();
        });

        binding.btnAdd.setOnClickListener(view1 -> {
            Intent intent = new Intent(MainActivity.this, CreateNewContact.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
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
        return super.onCreateOptionsMenu(menu);
    }
    private void filter(String text) {
        ArrayList<Contact> filteredList = new ArrayList<>();
        for (Contact item : contactList) {
            String fullName = item.getFirstName() + " " + item.getLastName();
            if (fullName.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            contactAdapter.filterList(filteredList);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(contactAdapter != null) contactAdapter.release();
    }

}