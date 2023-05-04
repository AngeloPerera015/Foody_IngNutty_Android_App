package com.f_in.foodyingnutty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class daily_average_nutrition_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_average_nutrition_screen);

        ImageView leftIcon = findViewById(R.id.left_icon);

        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });
    }
    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(daily_average_nutrition_screen.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_one)
                    startActivity(new Intent(daily_average_nutrition_screen.this, daily_average_nutrition_screen.class));
                if (menuItem.getItemId() == R.id.item_two)
                    startActivity(new Intent(daily_average_nutrition_screen.this, recommended_products_screen.class));
                if (menuItem.getItemId() == R.id.item_three)
                    startActivity(new Intent(daily_average_nutrition_screen.this, health_advice_screen.class));
                if (menuItem.getItemId() == R.id.item_four)
                    startActivity(new Intent(daily_average_nutrition_screen.this, pedometer_screen.class));
                if (menuItem.getItemId() == R.id.item_five)
                    startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:foodyingnutty@gmail.com")));
                if (menuItem.getItemId() == R.id.item_six)
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/foodyingnutty_f-s")));
                if (menuItem.getItemId() == R.id.item_seven)
                    startActivity(new Intent(daily_average_nutrition_screen.this, community_log_in.class));
                return true;
            }
        });
        popupMenu.show();
    }
}