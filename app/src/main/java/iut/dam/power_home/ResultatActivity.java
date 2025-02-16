package iut.dam.power_home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultatActivity extends AppCompatActivity {
    private TextView textViewInfo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_inscription);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.resultInscription), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Associer la TextView
        this.textViewInfo = findViewById(R.id.result);

        // Récupérer l'Intent et le Bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String nom = "Inconnu";
        String prenom = "Inconnu";
        String mdp = "Non défini";

        if (bundle != null) {
            nom = bundle.getString("Nom", "Inconnu");
            prenom = bundle.getString("Prenom", "Inconnu");
            mdp = bundle.getString("MotDePasse", "Non défini");
        }

// Affichage des informations dans la TextView
        textViewInfo.setText("Nom: " + nom + "\nPrénom: " + prenom + "\nMot de passe: " + mdp);

    }
}