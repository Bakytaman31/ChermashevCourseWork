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
import com.google.firebase.database.FirebaseDatabase;

public class registerUser extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private TextView banner, registerUser;
    private EditText editTextFullName, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullName);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String name = editTextFullName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty()) {
            editTextFullName.setError("Обязательно введите имя");
            editTextFullName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Обязательно введите почту");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Обязательно введите пароль");
            editTextPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Введите почту правильно");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Слишком коороткий пароль");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if (task.isSuccessful()) {
                                               Toast.makeText(registerUser.this, "Регистрация успешна", Toast.LENGTH_LONG).show();
                                               startActivity(new Intent(registerUser.this, MainActivity.class));
                                           } else {
                                               Toast.makeText(registerUser.this, "Регистрация неуспешна", Toast.LENGTH_LONG).show();
                                           }
                                           progressBar.setVisibility(View.GONE);
                                       }
                                    });
                        } else {
                            Toast.makeText(registerUser.this, "Регистрация неуспешна", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}