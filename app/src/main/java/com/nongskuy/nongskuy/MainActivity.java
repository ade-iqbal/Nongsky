package com.nongskuy.nongskuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    static String userEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.BottomNavigationMenu);

        Intent intent = getIntent();
        String profil = intent.getStringExtra("TOPROFIL");
        userEmail = intent.getStringExtra("EMAIL");
        intent.removeExtra("EMAIL");

        if(profil != null){
            loadFragments(new ProfilFragment());
            bottomNavigationView.setSelectedItemId(R.id.menu_profil);
        }
        else{
            loadFragments(new BerandaFragment());
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
        }
    }

    public boolean loadFragments(Fragment fragment){

        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment,fragment)
                    .commit();
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch(item.getItemId()){
            case R.id.menu_beranda:
                fragment = new BerandaFragment();
                break;

            case R.id.menu_promo:
                fragment = new PromoFragment();
                break;

            case R.id.menu_terdekat:
                fragment = new TerdekatFragment();
                break;

            case R.id.menu_profil:
                fragment = new ProfilFragment();
                break;
        }

        return loadFragments(fragment);
    }

    @Override
    public void onBackPressed() {

        if (bottomNavigationView.getSelectedItemId() == R.id.menu_beranda){
            super.onBackPressed();

            if(userEmail != null){
                this.finishAffinity();
            }
            else{
                finish();
            }
        }
        else{
            bottomNavigationView.setSelectedItemId(R.id.menu_beranda);
        }

    }
}