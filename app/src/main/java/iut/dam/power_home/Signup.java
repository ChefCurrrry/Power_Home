package iut.dam.power_home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Signup extends AppCompatActivity {
    private EditText editTextPrenom, editTextNom, editTextEmail, editTextPassword;
    private Button buttonValidate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signUp), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
                });
            // Récupération des éléments de l'interface
            editTextPrenom = findViewById(R.id.editTextPrenom);
            editTextNom = findViewById(R.id.editTextNom);
            editTextEmail = findViewById(R.id.editTextEmail);
            editTextPassword = findViewById(R.id.editTextPassword);
            buttonValidate = findViewById(R.id.buttonValidate);


            // Action lorsque l'utilisateur appuie sur le bouton
        buttonValidate.setOnClickListener(v -> {
            String prenom = editTextPrenom.getText().toString();
            String nom = editTextNom.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if (!InputValidator.isValidName(prenom)) {
                Toast.makeText(this, "Prénom Invalide", Toast.LENGTH_SHORT).show();
            }
            else if (!InputValidator.isValidName(nom)) {
                Toast.makeText(this, "Nom Invalide", Toast.LENGTH_SHORT).show();
            }
            else if (!InputValidator.isValidEmail(email)) {
                Toast.makeText(this, "Email Invalide", Toast.LENGTH_SHORT).show();
            }
            else if (!InputValidator.isValidPassword(password)) {
                Toast.makeText(this, "Mot de Passe Invalide", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "✅ Inscription réussie !", Toast.LENGTH_SHORT).show();
                saveUserCredentials(email, password, prenom, nom);
                goToResult();
            }
        });

    }
    private void saveUserCredentials(String email, String password, String prenom, String nom) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("prenom", prenom);
        editor.putString("nom", nom);
        editor.apply();
    }


    private void goToResult() {
        Log.d("DEBUG", "goToResult() appelée !");
        Intent intent = new Intent(this, ResultatActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        // Récupérer les données stockées
        String prenom = sharedPreferences.getString("prenom", "Inconnu");
        String nom = sharedPreferences.getString("nom", "Inconnu");
        String password = sharedPreferences.getString("password", "Non défini");

        // Ajouter les données à l'Intent
        intent.putExtra("Prenom", editTextPrenom.getText().toString());
        intent.putExtra("Nom", editTextNom.getText().toString());
        intent.putExtra("MotDePasse", editTextPassword.getText().toString());

        startActivity(intent);
    }


}
