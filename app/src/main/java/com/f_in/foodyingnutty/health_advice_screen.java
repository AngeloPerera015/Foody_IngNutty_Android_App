package com.f_in.foodyingnutty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class health_advice_screen extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4, cb5;
    Button btnEval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_advice_screen);

        ImageView leftIcon = findViewById(R.id.left_icon);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });

        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/foody-ingnutty-ed162.appspot.com/o/VideoSlider%2Fnutrition_for_a_healthy_life_720p.mp4?alt=media&token=4c6d403b-f06f-46a7-ad72-9d75024133e2"));
        videoView.start();

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        addListenerOnButtonClick();
    }
    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(health_advice_screen.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_one)
                    startActivity(new Intent(health_advice_screen.this, nutrition_plan_screen.class));
                if (menuItem.getItemId() == R.id.item_two)
                    startActivity(new Intent(health_advice_screen.this, health_advice_screen.class));
                if (menuItem.getItemId() == R.id.item_three)
                    startActivity(new Intent(health_advice_screen.this, pedometer_screen.class));
                if (menuItem.getItemId() == R.id.item_four)
                    startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:foodyingnutty@gmail.com")));
                if (menuItem.getItemId() == R.id.item_five)
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/foodyingnutty_f-s")));
                if (menuItem.getItemId() == R.id.item_six)
                    startActivity(new Intent(health_advice_screen.this, community_log_in.class));
                return true;
            }
        });
        popupMenu.show();
    }

    public void addListenerOnButtonClick(){
        cb1=(CheckBox)findViewById(R.id.CheckBox1);
        cb2=(CheckBox)findViewById(R.id.CheckBox2);
        cb3=(CheckBox)findViewById(R.id.CheckBox3);
        cb4=(CheckBox)findViewById(R.id.CheckBox4);
        cb5=(CheckBox)findViewById(R.id.CheckBox5);
        btnEval=(Button)findViewById(R.id.btnEvaluate);

        btnEval.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int totalscore=0;
                StringBuilder result=new StringBuilder();
                result.append("Your Score:");
                if(cb1.isChecked()){
                    totalscore+=20;
                }
                if(cb2.isChecked()){
                    totalscore+=20;
                }
                if(cb3.isChecked()){
                    totalscore+=20;
                }
                if(cb4.isChecked()){
                    totalscore+=20;
                }
                if(cb5.isChecked()){
                    totalscore+=20;
                }
                result.append("\n"+totalscore+" out of 100");

                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}