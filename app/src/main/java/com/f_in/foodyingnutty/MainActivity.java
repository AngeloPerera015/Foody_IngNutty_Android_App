package com.f_in.foodyingnutty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView leftIcon = findViewById(R.id.left_icon);
        TextView title = findViewById(R.id.toolbar_title);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });

        title.setText("Foody IngNutty");
    }

    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_one)
                    startActivity(new Intent(MainActivity.this, daily_average_nutrition_screen.class));
                if (menuItem.getItemId() == R.id.item_two)
                    startActivity(new Intent(MainActivity.this, recommended_products_screen.class));
                if (menuItem.getItemId() == R.id.item_three)
                    startActivity(new Intent(MainActivity.this, health_advice_screen.class));
                if (menuItem.getItemId() == R.id.item_four)
                    startActivity(new Intent(MainActivity.this, pedometer_screen.class));
                return true;
            }
        });
        popupMenu.show();
    }
}