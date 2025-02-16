package iut.dam.power_home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView emailText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);

        // Récupérer les données de l'intent
        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        String password = intent.getStringExtra("PASSWORD");

        // Affichage des informations
        emailText.setText("Email : " + email);
        passwordText.setText("Mot de passe : " + password);
    }
}