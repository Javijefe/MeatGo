package com.example.meatgo.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.Backend.CestaResponse;
import com.example.meatgo.Backend.EliminarProductoCestaResponse;
import com.example.meatgo.Backend.ReservarCantidadRequest;
import com.example.meatgo.Models.Producto;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;
import com.example.meatgo.Backend.EliminarProductoCestaRequest;
import com.example.meatgo.R;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CestaAdapter extends RecyclerView.Adapter<CestaAdapter.ViewHolder> {
    private List<CestaResponse.ProductoCesta> productos;
    private Context context;

    public CestaAdapter(List<CestaResponse.ProductoCesta> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cesta_compras_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CestaResponse.ProductoCesta cesta = productos.get(position);

        holder.textViewNombre.setText(cesta.getProducto());
        holder.textViewCantidad.setText(String.format("Cantidad: %.0f", cesta.getCantidad()));
        holder.textViewSubtotal.setText(String.format("Subtotal: $%.2f", cesta.getSubtotal()));

        holder.btnEliminar.setOnClickListener(v -> {
          int Id_Producto = cesta.getId_detalle();
          eliminarProductoEnBackend(Id_Producto, holder);
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    private String getToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("user_token", null);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNombre;
        public TextView textViewCantidad;
        public TextView textViewSubtotal;
        public Button btnEliminar;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewCantidad = itemView.findViewById(R.id.textViewCantidad);
            textViewSubtotal = itemView.findViewById(R.id.textViewSubtotal);
            btnEliminar = itemView.findViewById(R.id.btnEliminarProducto);
        }
    }

    private void eliminarProductoEnBackend(int Id_Producto, CestaAdapter.ViewHolder holder) {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        String token = getToken();
        if (token == null) {
            Toast.makeText(context, "Token no disponible, por favor inicie sesión nuevamente", Toast.LENGTH_SHORT).show();
            return;
        }

        EliminarProductoCestaRequest request = new EliminarProductoCestaRequest(token, Id_Producto);

        // Llamada para eliminar
        apiService.eliminarProductoDeCesta(request).enqueue(new Callback<EliminarProductoCestaResponse>() {
            @Override
            public void onResponse(Call<EliminarProductoCestaResponse> call, Response<EliminarProductoCestaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EliminarProductoCestaResponse respuesta = response.body();
                    int position = holder.getAdapterPosition();
                    productos.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, respuesta.getMensaje(), Toast.LENGTH_SHORT).show();
                } else {
                    int code = response.code();
                    String errorBody = "";
                    try {
                        if (response.errorBody() != null) {
                            errorBody = response.errorBody().string();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "Error al eliminar: Código " + code, Toast.LENGTH_LONG).show();
                    System.out.println("Error al eliminar producto. Código: " + code + ", Cuerpo: " + errorBody);
                }
            }

            @Override
            public void onFailure(Call<EliminarProductoCestaResponse> call, Throwable t) {
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    }