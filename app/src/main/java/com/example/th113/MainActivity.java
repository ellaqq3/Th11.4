package com.example.th113;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import static com.example.th113.SettingsFragment.nameSelection;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    TextView textView;
    TextView teksti_muokkaa;
    TextView teksti_nimi;
    Button button;
    EditText edit;
    ArrayList<String> lista = new ArrayList<>();
    int allowed;

    //luokkamuuttuja fragmentista
    String writtenName = nameSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("TH11.4");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        textView = findViewById(R.id.textView2);
        teksti_muokkaa = findViewById(R.id.textView);
        teksti_nimi = findViewById(R.id.textView3);

        textView.setText("Malli tekstistä. Voit asetuksissa muuttaa tekstin ulkonäköä, kuten fonttikokoa, leveyttä, korkeutta sekä rivimäärää.");
        teksti_muokkaa.setText("Tämä teksti on muokattavissasi");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();
        try{
            System.out.println(writtenName);
            teksti_nimi.setText(writtenName);
        }catch (Exception e){

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, new SettingsFragment()).commit();

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void editTextView() {
        FragmentManager manager = getSupportFragmentManager();
        SettingsFragment fragment = (SettingsFragment) manager.findFragmentById(R.id.fragment_container);
        lista = fragment.sendInfo();

        int korkeus = Integer.parseInt(lista.get(3));
        int fontti = Integer.parseInt(lista.get(0));
        int leveys = Integer.parseInt(lista.get(1));
        int rivit = Integer.parseInt(lista.get(2));

        final FrameLayout.LayoutParams layoutparams = (FrameLayout.LayoutParams) textView.getLayoutParams();

        textView.setTextSize(fontti);
        textView.setLines(rivit);
        layoutparams.width = leveys;
        layoutparams.height = korkeus;
    }


    //// Fragmentista kutsutaan alla olevia metodeja ja tallennetaan, onko muokkaus käytettävissä

    public void isAllowed(){
        allowed = 1;
    }

    public void isntAllowed(){
        allowed = 0;
    }

    //////////////

    public void pushButton(View v){
        button = findViewById(R.id.button2);
        edit = (EditText) findViewById(R.id.editText);
        String teksti = edit.getText().toString();
        if (allowed == 1){
            teksti_muokkaa.setText(teksti);
            textView.setText("Malli tekstistä. Voit asetuksissa muuttaa tekstin ulkonäköä, kuten fonttikokoa, leveyttä, korkeutta sekä rivimäärää.");
        } else if (allowed == 0) {
            teksti_muokkaa.setText("Muokkaus pois käytöstä");
            textView.setText(teksti);
        }else{
            teksti_muokkaa.setText("Muokkaus pois käytöstä");
            textView.setText(teksti);
        }
    }



}
