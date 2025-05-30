package com.example.meatgo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.meatgo.Adapter.MisPedidosAcabadosAdapter;
import com.example.meatgo.Backend.MisPedidosAcabadosRequest;
import com.example.meatgo.Backend.MisPedidosAcabadosResponse;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisPedidosAcabadosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MisPedidosAcabadosAdapter adapter;
    private List<MisPedidosAcabadosResponse> listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_mis_pedidos_acabados);

        listaPedidos = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewMisPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MisPedidosAcabadosAdapter(listaPedidos, this);
        recyclerView.setAdapter(adapter);

        obtenerMisPedidosAcabados();
    }

    private void obtenerMisPedidosAcabados() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        String token = getToken();
        if (token == null) {
            Toast.makeText(this, "Token no disponible, por favor inicie sesión nuevamente", Toast.LENGTH_SHORT).show();
            return;
        }

        MisPedidosAcabadosRequest request = new MisPedidosAcabadosRequest(token);

        // Llamada para obtener los pedidos acabados
        apiService.verPedidoSolicitud(request).enqueue(new Callback<MisPedidosAcabadosResponse>() {
            @Override
            public void onResponse(Call<MisPedidosAcabadosResponse> call, Response<MisPedidosAcabadosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MisPedidosAcabadosResponse pedido = response.body();

                    if (pedido.getId_pedido() != null) {
                        listaPedidos.clear();
                        listaPedidos.add(pedido);
                        adapter.notifyDataSetChanged();
                    } else {
                        listaPedidos.clear();
                        adapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void onFailure(Call<MisPedidosAcabadosResponse> call, Throwable t) {
                Toast.makeText(MisPedidosAcabadosActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE);
        return sharedPreferences.getString("user_token", null);
    }
}