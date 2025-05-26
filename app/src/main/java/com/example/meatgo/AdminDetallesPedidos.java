package com.example.meatgo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.Adapter.AdminDetallesPedidoAdapter;
import com.example.meatgo.Backend.AdminCambiarEstadoCompletadoRequest;
import com.example.meatgo.Backend.AdminCambiarEstadoCompletadoResponse;
import com.example.meatgo.Backend.AdminCambiarEstadoPedidoRequest;
import com.example.meatgo.Backend.AdminCambiarEstadoPedidoResponse;
import com.example.meatgo.Backend.VerDetallesPedidoRequest;
import com.example.meatgo.Backend.VerDetallesPedidoResponse;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDetallesPedidos extends AppCompatActivity {

    RecyclerView recyclerView;
    AdminDetallesPedidoAdapter adapter;
    String token;
    int pedidoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detalles_pedidos);

        recyclerView = findViewById(R.id.recycler_detalles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener pedidoId del intent
        pedidoId = getIntent().getIntExtra("pedidoId", -1);
        if (pedidoId == -1) {
            Toast.makeText(this, "Pedido no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Obtener token de SharedPreferences
        SharedPreferences prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        token = prefs.getString("admin_token", null);
        if (token == null) {
            Toast.makeText(this, "No autenticado, token no encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        adapter = new AdminDetallesPedidoAdapter(this, null);
        recyclerView.setAdapter(adapter);

        // Cargar detalles del pedido
        cargarDetallesDesdeBackend(pedidoId);

        // Botón Pendiente
        Button btnPendiente = findViewById(R.id.btn_pendiente);
        btnPendiente.setOnClickListener(v -> cambiarEstadoPedidoPendiente());

        // Botón Completado
        Button btnCompletado = findViewById(R.id.btn_completado);
        btnCompletado.setOnClickListener(v -> cambiarEstadoPedidoCompletado());
    }

    private void cargarDetallesDesdeBackend(int pedidoId) {
        VerDetallesPedidoRequest request = new VerDetallesPedidoRequest(token, pedidoId);

        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        apiService.verDetallesPedidos(request).enqueue(new Callback<VerDetallesPedidoResponse>() {
            @Override
            public void onResponse(Call<VerDetallesPedidoResponse> call, Response<VerDetallesPedidoResponse> response) {
                if (response.isSuccessful() && response.body() != null && "ok".equals(response.body().getStatus())) {
                    List<VerDetallesPedidoResponse.DetallePedido> detalles = response.body().getDetalles();
                    adapter.setDetalles(detalles);
                } else {
                    Toast.makeText(AdminDetallesPedidos.this, "Error al cargar detalles", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerDetallesPedidoResponse> call, Throwable t) {
                Toast.makeText(AdminDetallesPedidos.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cambiarEstadoPedidoPendiente() {
        AdminCambiarEstadoPedidoRequest request = new AdminCambiarEstadoPedidoRequest(token, pedidoId);

        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        apiService.cambiarEstadoPedidoPendiente(request).enqueue(new Callback<AdminCambiarEstadoPedidoResponse>() {
            @Override
            public void onResponse(Call<AdminCambiarEstadoPedidoResponse> call, Response<AdminCambiarEstadoPedidoResponse> response) {
                if (response.isSuccessful() && response.body() != null && "ok".equals(response.body().getStatus())) {
                    Toast.makeText(AdminDetallesPedidos.this, response.body().getMensaje(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminDetallesPedidos.this, "Error al cambiar estado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdminCambiarEstadoPedidoResponse> call, Throwable t) {
                Toast.makeText(AdminDetallesPedidos.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cambiarEstadoPedidoCompletado() {
        AdminCambiarEstadoCompletadoRequest request = new AdminCambiarEstadoCompletadoRequest(token, pedidoId);

        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        apiService.cambiarEstadoPedidoCompletado(request).enqueue(new Callback<AdminCambiarEstadoCompletadoResponse>() {
            @Override
            public void onResponse(Call<AdminCambiarEstadoCompletadoResponse> call, Response<AdminCambiarEstadoCompletadoResponse> response) {
                if (response.isSuccessful() && response.body() != null && "ok".equals(response.body().getStatus())) {
                    Toast.makeText(AdminDetallesPedidos.this, response.body().getMensaje(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminDetallesPedidos.this, "Error al cambiar a completado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdminCambiarEstadoCompletadoResponse> call, Throwable t) {
                Toast.makeText(AdminDetallesPedidos.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
