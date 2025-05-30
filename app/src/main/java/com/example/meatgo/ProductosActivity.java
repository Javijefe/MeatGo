package com.example.meatgo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.Adapter.TiposCarneAdapter;
import com.example.meatgo.Backend.ProductoResponse;
import com.example.meatgo.Backend.TipoCarneResponse;
import com.example.meatgo.Models.Producto;
import com.example.meatgo.Models.TipoCarne;
import com.example.meatgo.R;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTiposCarne;
    private TiposCarneAdapter tiposCarneAdapter;

    private ApiService apiService;

    private List<TipoCarne> tiposCarneList = new ArrayList<>();
    private List<Producto> productosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_carne);

        recyclerViewTiposCarne = findViewById(R.id.recyclerViewProductos);
        recyclerViewTiposCarne.setLayoutManager(new LinearLayoutManager(this));

        apiService = ApiClient.getApiService();

        // Cargar tipos de carne primero
        cargarTiposCarneDesdeApi();
    }

    private void cargarTiposCarneDesdeApi() {
        Call<TipoCarneResponse> callTipos = apiService.obtenerTiposCarne();

        callTipos.enqueue(new Callback<TipoCarneResponse>() {
            @Override
            public void onResponse(Call<TipoCarneResponse> call, Response<TipoCarneResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tiposCarneList = response.body().getTipos_carne();

                    // Luego de cargar los tipos, cargar productos
                    cargarProductosDesdeApi();

                } else {
                    Toast.makeText(ProductosActivity.this, "Error al cargar tipos de carne", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TipoCarneResponse> call, Throwable t) {
                Toast.makeText(ProductosActivity.this, "Fallo en conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarProductosDesdeApi() {
        Call<ProductoResponse> callProductos = apiService.obtenerProductos();

        callProductos.enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productosList = response.body().getProductos();

                    tiposCarneAdapter = new TiposCarneAdapter(ProductosActivity.this, tiposCarneList, productosList);
                    recyclerViewTiposCarne.setAdapter(tiposCarneAdapter);

                } else {
                    Toast.makeText(ProductosActivity.this, "Error al cargar productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {
                Toast.makeText(ProductosActivity.this, "Fallo en conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

