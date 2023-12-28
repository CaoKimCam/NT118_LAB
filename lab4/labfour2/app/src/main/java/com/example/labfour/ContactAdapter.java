package com.example.labfour;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Contact> listcontacts;
    private int layoutID;
    private Activity context;
    private RecyclerViewItemClickListener itemClickListener;
    public interface RecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setItemClickListener(RecyclerViewItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    public ContactAdapter(Activity context, int layoutID, ArrayList<Contact> contacts){
        this.listcontacts=contacts;
        this.context=context;
        this.layoutID=layoutID;
    }


    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context1=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context1);
        View itemView= inflater.inflate(R.layout.item_contact, null, false);
        ViewHolder viewHolder= new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact= listcontacts.get(position);

        holder.name.setText(contact.getName());
        holder.phonenumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return listcontacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private TextView name;
        private  TextView phonenumber;
        public ViewHolder(View itemView){
            super(itemView);
            itemView=itemView;
            name=itemView.findViewById(R.id.tv_it_name);
            phonenumber= itemView.findViewById(R.id.tv_it_phone);
        }
        public void onClick(View view){
            if(itemClickListener!=null){
                int position = getAdapterPosition();
                itemClickListener.onItemClick(view, position);
            }
        }
    }
}
