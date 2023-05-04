package com.f_in.foodyingnutty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.f_in.foodyingnutty.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class community_sign_up extends AppCompatActivity {

    EditText textEmail, textPassword, textName;
    ProgressBar progressBar;

    FirebaseAuth auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_sign_up);

        textEmail = (EditText) findViewById(R.id.signup_email);
        textPassword = (EditText) findViewById(R.id.signup_password);
        textName = (EditText) findViewById(R.id.signup_name);
        progressBar = (ProgressBar) findViewById(R.id.signup_progressbar);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public void SignupUser(View view) {
        progressBar.setVisibility(View.VISIBLE);
        final String email = textEmail.getText().toString();
        final String password = textPassword.getText().toString();
        final String name = textName.getText().toString();

        if (!email.equals("") && !password.equals("") && password.length()>6) {
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                User u = new User();
                                u.setEmail(email);
                                u.setName(name);

                                reference.child(firebaseUser.getUid()).setValue(u)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(community_sign_up.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                    finish();
                                                    Intent intent = new Intent(community_sign_up.this, community_screen.class);
                                                    startActivity(intent);
                                                }
                                                else {
                                                    progressBar.setVisibility(View.GONE);
                                                    Toast.makeText(community_sign_up.this, "Sign Up Failed, Try again!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        }
                    });
        }
    }

    public void gotoLogIn(View view) {
        Intent intent = new Intent(community_sign_up.this, community_log_in.class);
        startActivity(intent);
    }
}