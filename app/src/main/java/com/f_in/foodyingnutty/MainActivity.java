package com.f_in.foodyingnutty;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
//this is to create the user to view image slider and the image button to direct the product category
public class MainActivity extends AppCompatActivity {
    //declare variables
    private long pressedTime;
    ImageSlider mainSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //popup menu to access other activities
        ImageView leftIcon = findViewById(R.id.left_icon);
        TextView title = findViewById(R.id.toolbar_title);
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });

        title.setText("Foody IngNutty");
        //image slider via the firebase storage to the real-time database
        mainSlider = (ImageSlider) findViewById(R.id.image_slider);
        final List<SlideModel> remoteimages = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("ImageSlider")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data:snapshot.getChildren())
                            remoteimages.add(new SlideModel(data.child("url").getValue().toString(), data.child("title").getValue().toString(), ScaleTypes.FIT));
                        mainSlider.setImageList(remoteimages, ScaleTypes.FIT);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
    }
    //popup menu to access other activities cont.
    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.item_one)
                    startActivity(new Intent(MainActivity.this, NutritionPlan.class));
                if (menuItem.getItemId() == R.id.item_two)
                    startActivity(new Intent(MainActivity.this, HealthAdvice.class));
                if (menuItem.getItemId() == R.id.item_three)
                    startActivity(new Intent(MainActivity.this, Pedometer.class));
                if (menuItem.getItemId() == R.id.item_four)
                    startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:foodyingnutty@gmail.com")));
                if (menuItem.getItemId() == R.id.item_five)
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/foodyingnutty_f-s")));
                if (menuItem.getItemId() == R.id.item_six)
                    startActivity(new Intent(MainActivity.this, CommunityLogIn.class));
                return true;
            }
        });
        popupMenu.show();
    }
    //the image button to direct product category
    public void onClickImg(View view) {
        Intent intent = new Intent(MainActivity.this, ProductCategory.class);
        startActivity(intent);
    }
    //when the back button toast to press again if pressed twice within 2 sec. exit from the application
    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "press Back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}