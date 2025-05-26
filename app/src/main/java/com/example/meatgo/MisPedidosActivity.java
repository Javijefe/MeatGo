package com.example.meatgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.meatgo.Adapter.MisPedidosAdapter;
import com.example.meatgo.Backend.CestaResponse;
import com.example.meatgo.Backend.MisPedidosRequest;
import com.example.meatgo.Backend.MisPedidosResponse;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisPedidosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MisPedidosAdapter adapter;
    private List<CestaResponse.ProductoCesta> listaProductos;
    private Button btnMisPedidosAcabados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_pedidos);

        listaProductos = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewMisPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MisPedidosAdapter(listaProductos, this);
        recyclerView.setAdapter(adapter);

        btnMisPedidosAcabados = findViewById(R.id.btnMisPedidosAcabados);
        btnMisPedidosAcabados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a MisPedidosAcabadosActivity
                Intent intent = new Intent(MisPedidosActivity.this, MisPedidosAcabadosActivity.class);
                startActivity(intent);
            }
        });

        obtenerMisPedidos();
    }

    private void obtenerMisPedidos() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        String token = getToken();
        if (token == null) {
            Toast.makeText(this, "Token no disponible, por favor inicie sesión nuevamente", Toast.LENGTH_SHORT).show();
            return;
        }

        MisPedidosRequest request = new MisPedidosRequest(token);

        // Llamada para obtener los pedidos
        apiService.verPedidoSolicitud(request).enqueue(new Callback<MisPedidosResponse>() {
            @Override
            public void onResponse(Call<MisPedidosResponse> call, Response<MisPedidosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Actualiza la lista de productos
                    listaProductos.clear();
                    listaProductos.addAll(response.body().getProductos());
                    adapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
                } else {
                    Toast.makeText(MisPedidosActivity.this, "Error al obtener los pedidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MisPedidosResponse> call, Throwable t) {
                Toast.makeText(MisPedidosActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        return sharedPreferences.getString("user_token", null);
    }
}