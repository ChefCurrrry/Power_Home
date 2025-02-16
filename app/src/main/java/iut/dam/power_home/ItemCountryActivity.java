package iut.dam.power_home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ItemCountryActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_country);

        Spinner spinner = findViewById(R.id.spinnerFlag);
        List<Country> items = new ArrayList<>();
        items.add(new Country(R.drawable.uk, "United Kingdom"));
        items.add(new Country(R.drawable.sweden, "Sweden"));
        items.add(new Country(R.drawable.usa, "United States"));
        items.add(new Country(R.drawable.switzerland, "Switzerland"));
        items.add(new Country(R.drawable.france, "France"));
        items.add(new Country(R.drawable.japan, "Japan"));
        items.add(new Country(R.drawable.taiwan, "Taiwan"));

        // Utilisation du bon layout pour chaque élément du Spinner
        CountryAdapter adapter = new CountryAdapter(this, R.layout.item_country, items);
        spinner.setAdapter(adapter);
    }
}
