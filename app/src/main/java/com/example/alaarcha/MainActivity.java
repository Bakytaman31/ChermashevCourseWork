package com.example.alaarcha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register, forgotPass;
    private EditText editEmailText, editPasswordText;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
        
        editEmailText = findViewById(R.id.email);
        editPasswordText = findViewById(R.id.password);

        forgotPass = findViewById(R.id.forgotPassword);
        forgotPass.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, registerUser.class));
                break;
            case R.id.signIn:
                userLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ResetPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = editEmailText.getText().toString().trim();
        String password = editPasswordText.getText().toString().trim();

        if (email.isEmpty()) {
            editEmailText.setError("Обязательно введите почту");
            editEmailText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmailText.setError("Введите почту правильно");
            editEmailText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editPasswordText.setError("Обязательно введите почту");
            editPasswordText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editPasswordText.setError("Слишком коороткий пароль");
            editPasswordText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user.isEmailVerified()) {
                    startActivity(new Intent(MainActivity.this, Profile.class));
                } else {
                    user.sendEmailVerification();
                    Toast.makeText(MainActivity.this, "Проверьте почту", Toast.LENGTH_LONG).show();
                }
            } else {
            Toast.makeText(MainActivity.this, "Неправильный логин или пароль", Toast.LENGTH_LONG).show();
        }
            progressBar.setVisibility(View.GONE);
        });

    }
}