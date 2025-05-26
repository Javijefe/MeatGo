package com.example.meatgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.Adapter.CestaAdapter;
import com.example.meatgo.Backend.CestaRequest;
import com.example.meatgo.Backend.CestaResponse;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCesta;
    private CestaAdapter cestaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesta_compras);

        recyclerViewCesta = findViewById(R.id.recyclerViewCesta);
        recyclerViewCesta.setLayoutManager(new LinearLayoutManager(this));

        Button btnMisPedidos = findViewById(R.id.btnMisPedidos);
        btnMisPedidos.setOnClickListener(v -> {
            // Ir a  MisPedidosActivity
            Intent intent = new Intent(CarritoActivity.this, MisPedidosActivity.class);
            startActivity(intent);
        });

        obtenerProductosReservados();
    }

    private void obtenerProductosReservados() {
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        String token = prefs.getString("user_token", null);

        if (token == null) {
            Toast.makeText(this, "No hay sesión activa. Por favor, inicia sesión.", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d("TokenDebug", "Token recuperado: " + token);

        ApiService apiService = ApiClient.getApiService();

        // Llamamos
        Call<CestaResponse> call = apiService.verCesta(new CestaRequest(0, 0, token));

        call.enqueue(new Callback<CestaResponse>() {
            @Override
            public void onResponse(Call<CestaResponse> call, Response<CestaResponse> response) {
                if (response.isSuccessful()) {
                    // Obtener la lista de productos de la respuesta
                    List<CestaResponse.ProductoCesta> productos = response.body().getProductos();

                    // Verificar si la lista de productos no está vacía
                    if (productos != null && !productos.isEmpty()) {
                        cestaAdapter = new CestaAdapter(productos, CarritoActivity.this);
                        recyclerViewCesta.setAdapter(cestaAdapter);
                    } else {
                        Toast.makeText(CarritoActivity.this, "No hay productos en la cesta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("Carrito", "Error en la respuesta de la API: " + response.message());
                    Toast.makeText(CarritoActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CestaResponse> call, Throwable t) {
                Log.e("Carrito", "Error en la llamada a la API: " + t.getMessage());
                Toast.makeText(CarritoActivity.this, "Error en la llamada a la API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
