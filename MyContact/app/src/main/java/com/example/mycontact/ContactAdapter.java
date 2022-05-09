package com.example.mycontact;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Contact> contacts;
    private Context context;

    public ContactAdapter(ArrayList<Contact> contacts, Context context){
        this.contacts = contacts;
        this.context = context;
    }
    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item,parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceType"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvName.setText(String.format("%s %s", contacts.get(position).getFirstName(), contacts.get(position).getLastName()));
//        holder.ivAvatar.setImageBitmap(contacts.get(position).getAvatar());
        holder.ivAvatar.setImageURI(Uri.parse(contacts.get(position).getAvatar()));
        final Contact contact = contacts.get(position);
        if(contact == null) return;
        holder.cvItem.setOnClickListener(view -> onClickGoToDetail(contact, position));
    }
    private void onClickGoToDetail(Contact contact, int position) {
        Intent intent = new Intent(this.context, DetailContact.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj", contact);
        bundle.putInt("index", position);
        bundle.putInt("id", contact.getId());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void release(){
        context = null;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(ArrayList<Contact> filterList) {
        contacts = filterList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivAvatar;
        public TextView tvName;
        public CardView cvItem;
        public ViewHolder(View view){
            super(view);
            ivAvatar = view.findViewById(R.id.iv_ava);
            tvName = view.findViewById(R.id.tv_name);
            cvItem = view.findViewById(R.id.cv_item);
        }
    }
}
