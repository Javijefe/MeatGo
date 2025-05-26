package com.example.meatgo;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.meatgo.Adapter.AdminTiposCarneAdapter;
import com.example.meatgo.Backend.ProductoResponse;
import com.example.meatgo.Backend.TipoCarneResponse;
import com.example.meatgo.Models.Producto;
import com.example.meatgo.Models.TipoCarne;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class AdminProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTiposCarne;
    private AdminTiposCarneAdapter tiposCarneAdapter;
    private List<TipoCarne> tiposCarneList;
    private List<Producto> productosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tipos_carne);

        recyclerViewTiposCarne = findViewById(R.id.recyclerAdminViewProductos);
        recyclerViewTiposCarne.setLayoutManager(new LinearLayoutManager(this));

        tiposCarneList = new ArrayList<>();
        productosList = new ArrayList<>();

        cargarDatos();
    }

    private void cargarDatos() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        // Obtener tipos de carne
        apiService.obtenerTiposCarne().enqueue(new Callback<TipoCarneResponse>() {
            @Override
            public void onResponse(Call<TipoCarneResponse> call, Response<TipoCarneResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tiposCarneList.clear();
                    tiposCarneList.addAll(response.body().getTipos_carne());
                    tiposCarneAdapter = new AdminTiposCarneAdapter(tiposCarneList, productosList, AdminProductosActivity.this);
                    recyclerViewTiposCarne.setAdapter(tiposCarneAdapter);
                    tiposCarneAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Error al obtener tipos de carne: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TipoCarneResponse> call, Throwable t) {
                Log.e("API Error", "Fallo al obtener tipos de carne: " + t.getMessage());
            }
        });

        // Obtener productos
        apiService.obtenerProductos().enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productosList.clear();
                    productosList.addAll(response.body().getProductos());
                    // Notificar al adaptador que los productos han cambiado
                    if (tiposCarneAdapter != null) {
                        tiposCarneAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("API Error", "Error al obtener productos: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {
                Log.e("API Error", "Fallo al obtener productos: " + t.getMessage());
            }
        });
    }
}