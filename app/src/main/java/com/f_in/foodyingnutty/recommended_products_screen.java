package com.f_in.foodyingnutty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class recommended_products_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_products_screen);

        ImageView leftIcon = findViewById(R.id.left_icon);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });
    }
    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(recommended_products_screen.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_one)
                    startActivity(new Intent(recommended_products_screen.this, daily_average_nutrition_screen.class));
                if (menuItem.getItemId() == R.id.item_two)
                    startActivity(new Intent(recommended_products_screen.this, recommended_products_screen.class));
                if (menuItem.getItemId() == R.id.item_three)
                    startActivity(new Intent(recommended_products_screen.this, health_advice_screen.class));
                if (menuItem.getItemId() == R.id.item_four)
                    startActivity(new Intent(recommended_products_screen.this, pedometer_screen.class));
                return true;
            }
        });
        popupMenu.show();
    }
}