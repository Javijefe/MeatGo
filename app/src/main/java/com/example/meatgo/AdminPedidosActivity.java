package com.example.meatgo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.Adapter.AdminPedidosAdapter;
import com.example.meatgo.Backend.AdminTodosPedidosRequest;
import com.example.meatgo.Backend.AdminTodosPedidosResponse;
import com.example.meatgo.Models.Pedido;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPedidosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTodosPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_todos_pedidos);

        recyclerViewTodosPedidos = findViewById(R.id.recyclerViewTodosPedidos);
        recyclerViewTodosPedidos.setLayoutManager(new LinearLayoutManager(this));


    }
    @Override
    protected void onResume() {
        super.onResume();
        cargarPedidosDesdeBackend();
    }

    private void cargarPedidosDesdeBackend() {
        String token = obtenerToken();
        if (token == null) {
            Toast.makeText(this, "Token no disponible. Inicia sesión como admin.", Toast.LENGTH_SHORT).show();
            return;
        }

        AdminTodosPedidosRequest request = new AdminTodosPedidosRequest(token);
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        apiService.verTodosPedidos(request).enqueue(new Callback<AdminTodosPedidosResponse>() {
            @Override
            public void onResponse(Call<AdminTodosPedidosResponse> call, Response<AdminTodosPedidosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pedido> pedidos = response.body().getPedidos();
                    // Aquí se pasa el contexto al adapter
                    AdminPedidosAdapter adapter = new AdminPedidosAdapter(AdminPedidosActivity.this, pedidos);
                    recyclerViewTodosPedidos.setAdapter(adapter);
                } else {
                    Toast.makeText(AdminPedidosActivity.this, "Error al obtener pedidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdminTodosPedidosResponse> call, Throwable t) {
                Toast.makeText(AdminPedidosActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String obtenerToken() {
        SharedPreferences prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        return prefs.getString("admin_token", null);
    }
}
