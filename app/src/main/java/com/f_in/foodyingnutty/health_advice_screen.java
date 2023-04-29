package com.f_in.foodyingnutty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.PopupMenu;
import android.widget.VideoView;

public class health_advice_screen extends AppCompatActivity {

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
        videoView.setVideoPath("android.resource://"+ getPackageName() + "/" + R.raw.nutrition_for_a_healthy_life_720p);
        videoView.start();

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }
    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(health_advice_screen.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_one)
                    startActivity(new Intent(health_advice_screen.this, daily_average_nutrition_screen.class));
                if (menuItem.getItemId() == R.id.item_two)
                    startActivity(new Intent(health_advice_screen.this, recommended_products_screen.class));
                if (menuItem.getItemId() == R.id.item_three)
                    startActivity(new Intent(health_advice_screen.this, health_advice_screen.class));
                if (menuItem.getItemId() == R.id.item_four)
                    startActivity(new Intent(health_advice_screen.this, pedometer_screen.class));
                return true;
            }
        });
        popupMenu.show();
    }
}