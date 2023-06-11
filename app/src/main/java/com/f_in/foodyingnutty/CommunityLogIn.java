package com.f_in.foodyingnutty;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//this is to create the user to login into the community which runs on the firebase
public class CommunityLogIn extends AppCompatActivity {
    //declare variables
    EditText textEmail, textPassword;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        //check authentication is successful for community chat
        if (auth.getCurrentUser()!=null) {
            Intent intent = new Intent(CommunityLogIn.this, Community.class);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_community_login);
            textEmail = (EditText) findViewById(R.id.login_email);
            textPassword = (EditText) findViewById(R.id.login_password);
            progressBar = (ProgressBar) findViewById(R.id.login_progressbar);
        }
    }
    //request sign up credentials and check the credentials are correct
    public void LoginUser(View view) {
        progressBar.setVisibility(View.VISIBLE);

        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();

        if (!email.equals("") && !password.equals("")) {
            auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(CommunityLogIn.this, "Log In Successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CommunityLogIn.this, Community.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(CommunityLogIn.this, "Invalid Credentials, Try again!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
        }
    }
    //button to direct to the signup
    public void gotoSignUp(View view) {
        Intent intent = new Intent(CommunityLogIn.this, CommunitySignUp.class);
        startActivity(intent);
    }
    //button to the user to update the password
    public void forgotPassword(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(CommunityLogIn.this);
        LinearLayout container = new LinearLayout(CommunityLogIn.this);
        container.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ip.setMargins(50,0,0,100);

        final EditText input = new EditText(CommunityLogIn.this);
        input.setLayoutParams(ip);
        input.setGravity(Gravity.TOP|Gravity.START);
        input.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        input.setLines(1);
        input.setMaxLines(1);
        container.addView(input,ip);
        builder.setMessage("Enter your Sign Up E-mail address");
        builder.setTitle("Forgot Password");
        builder.setView(container);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {
                String entered_email = input.getText().toString();

                auth.sendPasswordResetEmail(entered_email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dialogInterface.dismiss();
                                    Toast.makeText(CommunityLogIn.this, "Password reset E-mail sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }).show();
    }
}