package com.f_in.foodyingnutty.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.f_in.foodyingnutty.Models.AllMethods;
import com.f_in.foodyingnutty.Models.Message;
import com.f_in.foodyingnutty.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
//this is to create the user messages and hold values
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {
    //declare variables
    Context context;
    DatabaseReference messageDb;
    List<Message> messages;
    public MessageAdapter(Context context, List<Message> messages, DatabaseReference messageDb) {
        this.context = context;
        this.messageDb = messageDb;
        this.messages = messages;
    }
    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message,parent,false);
        return new MessageAdapterViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MessageAdapterViewHolder holder, int position) {
        Message message = messages.get(position);
        if (message.getName().equals(AllMethods.name)) {
            holder.tvTitle.setText("You: " + message.getMessage());
            holder.tvTitle.setGravity(Gravity.START);
            holder.l1.setBackgroundColor(Color.parseColor("#EF9E73"));
        }
        else {
            holder.tvTitle.setText(message.getName() + ":" +message.getMessage());
            holder.btnDelete.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return messages.size();
    }
    //declare variables
    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageButton btnDelete;
        LinearLayout l1;
        public MessageAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDelete);
            l1 = (LinearLayout) itemView.findViewById(R.id.l1Message);
            //button to delete the message
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageDb.child(messages.get(getAbsoluteAdapterPosition()).getKey()).removeValue();
                }
            });
        }
    }
}