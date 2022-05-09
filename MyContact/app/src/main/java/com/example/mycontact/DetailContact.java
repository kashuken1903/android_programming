package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.mycontact.databinding.ActivityDetailContactBinding;

import java.io.ByteArrayOutputStream;


public class DetailContact extends AppCompatActivity {

    ActivityDetailContactBinding binding;
    Contact contact;
    int RESULT = 200;
    int index, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null) return;

        contact = (Contact) bundle.get("obj");
        index = bundle.getInt("index");
        id = bundle.getInt("id");
        binding.tvName.setText(contact.getFullName());
        binding.tvPhone.setText(contact.getPhone());
        binding.ivAva.setImageURI(Uri.parse(contact.getAvatar()));
    }
    private Uri getBytesToUri(String s){
        byte[] data = s.getBytes();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(this, CreateNewContact.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("obj", contact);
            bundle.putInt("index", index);
            bundle.putInt("id", id);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }


}