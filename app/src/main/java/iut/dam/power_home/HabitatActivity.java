package iut.dam.power_home;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import iut.dam.power_home.frag.HomeFragment;
import iut.dam.power_home.frag.SettingsFragment;


public class HabitatActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    String urlString = "http://10.125.132.41/power_home/getHabitats.php";

    ProgressDialog pDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.habitatLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getRemoteHabitats();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Navigation View
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Gérer les clics du menu
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.nav_settings) {
                selectedFragment = new SettingsFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            // Fermer le drawer après sélection
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Afficher HomeFragment par défaut
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Habitat> habitats = new ArrayList<>();
        habitats.add(new Habitat("Gaëtan Leclair", Arrays.asList(R.drawable.ic_aspirateur, R.drawable.ic_machine_a_laver, R.drawable.ic_fer_a_repasser, R.drawable.ic_climatiseur),1));
        habitats.add(new Habitat("Cédric Boudet",  Arrays.asList(R.drawable.ic_aspirateur),2));
        habitats.add(new Habitat("Gaylord Thibodeaux", Arrays.asList(R.drawable.ic_climatiseur, R.drawable.ic_machine_a_laver), 3));
        habitats.add(new Habitat("Adam Jacquinot", Arrays.asList(R.drawable.ic_aspirateur, R.drawable.ic_machine_a_laver, R.drawable.ic_fer_a_repasser), 4));
        habitats.add(new Habitat("Abel Fresnel", Arrays.asList(R.drawable.ic_machine_a_laver), 5));

        HabitatAdapter adapter = new HabitatAdapter(habitats);
        recyclerView.setAdapter(adapter);



    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void getRemoteHabitats() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Getting list of habitats...");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();

        Ion.with(this)
                .load(urlString)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        pDialog.dismiss();
                        if(result == null)
                            Log.d(TAG, "No response from the server!!!");
                        else {
                            Toast.makeText(HabitatActivity.this, result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}