package com.f_in.foodyingnutty;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.f_in.foodyingnutty.Adapters.MessageAdapter;
import com.f_in.foodyingnutty.Models.AllMethods;
import com.f_in.foodyingnutty.Models.Message;
import com.f_in.foodyingnutty.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class community_screen extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference messagedb;
    MessageAdapter messageAdapter;
    User u;
    List<Message> messages;
    RecyclerView rvMessage;
    EditText etmessage;
    ImageButton imgButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_screen);

        init();

        ImageView leftIcon = findViewById(R.id.left_icon);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });
    }

    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(community_screen.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.logout_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menulogout)
                    auth.signOut();
                    finish();
                    startActivity(new Intent(community_screen.this, community_log_in.class));
                return true;
            }
        });
        popupMenu.show();
    }

    private void init() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        u = new User();

        rvMessage = (RecyclerView) findViewById(R.id.rvMessage);
        etmessage = (EditText) findViewById(R.id.etMessage);
        imgButton = (ImageButton) findViewById(R.id.btnSend);

        imgButton.setOnClickListener(this);
        messages = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        if (!TextUtils.isEmpty(etmessage.getText().toString())) {
            Message message = new Message(etmessage.getText().toString(),u.getName());
            etmessage.setText("");
            messagedb.push().setValue(message);
        }
        else {
            Toast.makeText(this, "You can't send blank message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentUser = auth.getCurrentUser();

        u.setUid(currentUser.getUid());
        u.setName(currentUser.getEmail());

        database.getReference("Users").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                u = snapshot.getValue(User.class);
                u.setUid(currentUser.getUid());
                AllMethods.name = u.getName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        messagedb = database.getReference("messages");
        messagedb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                message.setKey(snapshot.getKey());
                messages.add(message);
                displayMessages(messages);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Message message = snapshot.getValue(Message.class);
                message.setKey(snapshot.getKey());

                List<Message> newMessages = new ArrayList<Message>();

                for (Message m: messages) {
                    if (m.getKey().equals(message.getKey())) {
                        newMessages.add(message);
                    }
                    else {
                        newMessages.add(m);
                    }
                }
                messages = newMessages;
                displayMessages(messages);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Message message = snapshot.getValue(Message.class);
                message.setKey(snapshot.getKey());

                List<Message> newMessages = new ArrayList<Message>();

                for (Message m:messages) {
                    if (!m.getKey().equals(message.getKey())) {
                        newMessages.add(m);
                    }
                }
                messages = newMessages;
                displayMessages(messages);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        messages = new ArrayList<>();
    }

    private void displayMessages(List<Message> messages) {
        rvMessage.setLayoutManager(new LinearLayoutManager(community_screen.this));
        messageAdapter = new MessageAdapter(community_screen.this,messages,messagedb);
        rvMessage.setAdapter(messageAdapter);
    }
}