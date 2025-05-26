package com.example.meatgo;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.meatgo.Adapter.TiposCarneAdapter;
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

public class ProductosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTiposCarne;
    private TiposCarneAdapter tiposCarneAdapter;
    private List<TipoCarne> tiposCarneList;
    private List<Producto> productosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_carne);

        recyclerViewTiposCarne = findViewById(R.id.recyclerViewProductos);
        recyclerViewTiposCarne.setLayoutManager(new LinearLayoutManager(this));

        tiposCarneList = new ArrayList<>();
        productosList = new ArrayList<>();

        cargarDatos();

        tiposCarneAdapter = new TiposCarneAdapter(tiposCarneList, productosList);
        recyclerViewTiposCarne.setAdapter(tiposCarneAdapter);

    }

    // Método para cargar los tipos de carne y productos
    private void cargarDatos() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        //Llamada para obtener los tipos de carne
        apiService.obtenerTiposCarne().enqueue(new Callback<TipoCarneResponse>() {
            @Override
            public void onResponse(Call<TipoCarneResponse> call, Response<TipoCarneResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    tiposCarneList.clear();
                    tiposCarneList.addAll(response.body().getTipos_carne());

                    // Ahora que tenemos los tipos de carne, notificar al adaptador de tipos de carne
                    tiposCarneAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Error en la respuesta de tipos de carne: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TipoCarneResponse> call, Throwable t) {
                Log.e("API Error", "Error al obtener tipos de carne: " + t.getMessage());
            }
        });

        // Llamada para obtener productos
        apiService.obtenerProductos().enqueue(new Callback<ProductoResponse>() {
            @Override
            public void onResponse(Call<ProductoResponse> call, Response<ProductoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productosList.clear();
                    productosList.addAll(response.body().getProductos());

                    // Ahora que tenemos los productos, actualizar el adaptador de tipos de carne
                    tiposCarneAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API Error", "Error en la respuesta de productos: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProductoResponse> call, Throwable t) {
                Log.e("API Error", "Error al obtener productos: " + t.getMessage());
            }
        });
    }


}