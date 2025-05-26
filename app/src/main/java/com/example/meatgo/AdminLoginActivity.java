package com.example.meatgo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meatgo.R;
import androidx.appcompat.app.AppCompatActivity;

import com.example.meatgo.Backend.AdminLoginRequest;
import com.example.meatgo.Backend.AdminLoginResponse;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.btn_adm_login);

        // Hacer el método LoginAdmin
        loginButton.setOnClickListener(v -> loginAdmin());
    }

    //Método para iniciar sesión
    private void loginAdmin() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        AdminLoginRequest adminLoginRequest = new AdminLoginRequest(email, password);

        ApiService apiService = ApiClient.getApiService();

        Call<AdminLoginResponse> call = apiService.iniciarSesionAdmin(adminLoginRequest);
        call.enqueue(new Callback<AdminLoginResponse>() {
            @Override
            public void onResponse(Call<AdminLoginResponse> call, Response<AdminLoginResponse> response) {
                if (response.isSuccessful()) {
                    AdminLoginResponse adminLoginResponse = response.body();
                    if (adminLoginResponse != null) {
                        String token = adminLoginResponse.getToken();
                        Log.d("TOKEN", "Token recibido: " + token);

                        saveToken(token);
                        // Ir a AdminProductosActivity
                        Intent intent = new Intent(AdminLoginActivity.this, AdminProductosActivity.class);
                         startActivity(intent);
                         finish();
                    }
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Error en el inicio de sesión: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdminLoginResponse> call, Throwable t) {
                Toast.makeText(AdminLoginActivity.this, "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveToken(String token) {
        getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .putString("admin_token", token)
                .apply();
    }
}