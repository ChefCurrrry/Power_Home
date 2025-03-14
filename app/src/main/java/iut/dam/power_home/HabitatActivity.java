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
    //ne pas oublier de changer l'adresse ip
    String urlString = "http://10.125.134.64/power_home/getHabitats_v2.php?token=754d330b0871a361f20ffc981d03f6de";

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
        Appliance aspi = new Appliance(1, "aspirateur", "ic_aspirateur", 10);
        Appliance machineALaver = new Appliance(1, "machine à laver", "ic_machine_a_laver", 10);
        Appliance fer = new Appliance(1, "fer à repasser", "ic_fer_a_repasser", 10);
        Appliance climatiseur = new Appliance(1, "climatiseur", "ic_climatiseur", 10);
        Habitat h1 = new Habitat(1,1,28);
        List<Appliance> a1 = new ArrayList<>();

        a1.add(aspi);
        a1.add(machineALaver);
        a1.add(fer);
        a1.add(climatiseur);
        h1.appliances=a1;
        h1.user = new User(1, "Gaëtan", "Leclair", "gaetanleclair@gmail.com", "test", null, null);
        habitats.add(h1);

        Habitat h2 = new Habitat(2,2,28);
        List<Appliance> a2 = new ArrayList<>();
        a2.add(aspi);
        h2.appliances=a2;
        h2.user = new User(2, "Cédric", "Boudet", "cedricthibodeaux@gmail.com", "test", null, null);
        habitats.add(h2);

        Habitat h3 = new Habitat(3,3,28);
        List<Appliance> a3 = new ArrayList<>();
        a3.add(climatiseur);
        a3.add(machineALaver);
        h3.appliances = a3;
        h3.user = new User(3, "Gaylord", "Thibodeaux", "gaylordthibodeaux@gmail.com", "test", null, null);
        habitats.add(h3);

        Habitat h4 = new Habitat(4,4,28);
        List<Appliance> a4 = new ArrayList<>();
        a4.add(aspi);
        a4.add(machineALaver);
        a4.add(fer);
        h4.appliances = a4;
        h4.user = new User(4, "Adam", "Jacquinot", "adamjacquinot@gmail.com", "test", null, null);
        habitats.add(h4);

        Habitat h5 = new Habitat(5,4,28);
        List<Appliance> a5 = new ArrayList<>();
        a5.add(machineALaver);
        h5.appliances = a5;
        h5.user = new User(5, "Adam", "Jacquinot", "adamjacquinot@gmail.com", "test", null, null);
        habitats.add(h5);
//        List<Habitat> habitats = new ArrayList<>();
//        habitats.add(new Habitat(1, 2,1));
//        habitats.add(new Habitat(2,  Arrays.asList(R.drawable.ic_aspirateur),2));
//        habitats.add(new Habitat(3, Arrays.asList(R.drawable.ic_climatiseur, R.drawable.ic_machine_a_laver), 3));
//        habitats.add(new Habitat(4, Arrays.asList(R.drawable.ic_aspirateur, R.drawable.ic_machine_a_laver, R.drawable.ic_fer_a_repasser), 4));
//        habitats.add(new Habitat(5, Arrays.asList(R.drawable.ic_machine_a_laver), 5));

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