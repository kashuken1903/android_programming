package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mycontact.databinding.ActivityNewContactBinding;

import java.util.Objects;

public class CreateNewContact extends AppCompatActivity {

    ActivityNewContactBinding binding;
    Contact contactGet, contactPost;

    AppDatabase appDatabase;
    ContactDao contactDao;
    Uri selectedImageUri;
    int SELECT_PICTURE = 200;
    boolean isCreateOrEdit;
    int index;
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Create new contact");
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        isCreateOrEdit = true;
        binding.ibPhoto.setOnClickListener(view1 -> imageChooser());

        Bundle bundle = getIntent().getExtras();
        if(bundle == null) return;
        Objects.requireNonNull(getSupportActionBar()).setTitle("Edit contact");
        contactGet = (Contact) bundle.get("obj");
        binding.etFirstname.setText(contactGet.getFirstName());
        binding.etLastname.setText(contactGet.getLastName());
        binding.etPhone.setText(contactGet.getPhone());
        binding.etEmail.setText(contactGet.getEmail());
        binding.ivAva.setImageURI(Uri.parse(contactGet.getAvatar()));
        index = bundle.getInt("index");
        id = bundle.getInt("id");
        isCreateOrEdit = false;
    }
    private void imageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        appDatabase = AppDatabase.getInstance(this);
        contactDao = appDatabase.contactDao();
        Bundle bundle = new Bundle();
        Intent intentOK = new Intent(CreateNewContact.this, MainActivity.class);
        if (item.getItemId() == R.id.action_save) {
            if (selectedImageUri != null){
                contactPost = new Contact(binding.etFirstname.getText().toString(),
                        binding.etLastname.getText().toString(),
                        binding.etEmail.getText().toString(),
                        binding.etPhone.getText().toString(),
                        selectedImageUri.toString());
            } else {
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.ic_baseline_person_300);
                contactPost = new Contact(binding.etFirstname.getText().toString(),
                        binding.etLastname.getText().toString(),
                        binding.etEmail.getText().toString(),
                        binding.etPhone.getText().toString(),
                        uri.toString());
            }
            if (isCreateOrEdit) { // new
                AsyncTask.execute(() -> contactDao.insertAll(contactPost));
                bundle.putSerializable("obj", contactPost);
            } else { // edit
                contactPost.setId(id);
                AsyncTask.execute(() -> contactDao.update(contactPost));
                bundle.putSerializable("obj", contactPost);
                bundle.putInt("index", index);
            }
            intentOK.putExtras(bundle);
            startActivity(intentOK);
            return true;
        } else if(item.getItemId() == android.R.id.home){
            this.finish();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                binding.ivAva.setImageURI(selectedImageUri);
            }
        }
    }

}