package com.example.meatgo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.meatgo.Backend.LoginRequest;
import com.example.meatgo.Backend.LoginResponse;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private Button adminButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_login);
        registerButton = findViewById(R.id.btn_register);
        adminButton = findViewById(R.id.btn_admin);

        loginButton.setOnClickListener(v -> loginUser ());


        registerButton.setOnClickListener(v -> openRegisterActivity());

        adminButton.setOnClickListener(v -> openAdminLoginActivity());
    }

    private void loginUser () {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Por favor, ingrese su correo electrónico");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Por favor, ingrese su contraseña");
            passwordEditText.requestFocus();
            return;
        }

        LoginRequest loginRequest = new LoginRequest(email, password);

        ApiService apiService = ApiClient.getApiService();

        Call<LoginResponse> call = apiService.iniciarSesion(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        String token = loginResponse.getToken();
                        Log.d("TOKEN", "Token recibido: " + token);

                        saveToken(token);

                        Intent intent = new Intent(LoginActivity.this, ProductosActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Log.e("LOGIN", "Error en login: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LOGIN", "Fallo de red: " + t.getMessage());
            }
        });
    }


    private void saveToken(String token) {
        getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .putString("user_token", token)
                .apply();
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void openAdminLoginActivity() {
        Intent intent = new Intent(LoginActivity.this, AdminLoginActivity.class);
        startActivity(intent);
    }
}