package com.example.meatgo.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meatgo.Backend.AumentarStockRequest;
import com.example.meatgo.Backend.AumentarStockResponse;
import com.example.meatgo.Models.Producto;
import com.example.meatgo.R;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class AdminProductosAdapter extends RecyclerView.Adapter<AdminProductosAdapter.ViewHolder> {
    private List<Producto> productos;
    private Context context;

    public AdminProductosAdapter(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_productos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.textViewNombre.setText(producto.getNombre());
        holder.textViewPrecio.setText(String.format("$%.2f", producto.getPrecio()));
        holder.textViewStock.setText(String.format("Stock: %.0f", producto.getStock()));

        // Cargar la imagen del producto
        Glide.with(holder.itemView.getContext())
                .load(producto.getFoto())
                .into(holder.imageViewProducto);

        // Limpiar el campo de entrada de stock
        holder.editTextModificarStock.setText("");

        // Boton para aumentar el stock
        holder.buttonConfirmar.setOnClickListener(v -> {
            String stockInput = holder.editTextModificarStock.getText().toString();
            if (!stockInput.isEmpty()) {
                int cantidadModificar = Integer.parseInt(stockInput);
                aumentarStockEnBackend(producto, cantidadModificar, holder);
            } else {
                Toast.makeText(context, "Ingrese un valor válido", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewProducto;
        public TextView textViewNombre;
        public TextView textViewPrecio;
        public TextView textViewStock;
        public EditText editTextModificarStock;
        public Button buttonConfirmar;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewProducto = itemView.findViewById(R.id.imageViewProducto);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewPrecio = itemView.findViewById(R.id.textViewPrecio);
            textViewStock = itemView.findViewById(R.id.textViewStock);
            editTextModificarStock = itemView.findViewById(R.id.editTextModificarStock);
            buttonConfirmar = itemView.findViewById(R.id.buttonConfirmar);
        }
    }

    private String getToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("admin_token", null);
    }

    private void aumentarStockEnBackend(Producto producto, int cantidad, ViewHolder holder) {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        String token = getToken();
        if (token == null) {
            Toast.makeText(context, "Token no disponible, por favor inicie sesión nuevamente", Toast.LENGTH_SHORT).show();
            return;
        }

        AumentarStockRequest request = new AumentarStockRequest(producto.getIdProductos(), cantidad, token);

        apiService.aumentarStock(request).enqueue(new Callback<AumentarStockResponse>() {
            @Override
            public void onResponse(Call<AumentarStockResponse> call, Response<AumentarStockResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Actualizar el stock local y la UI si la respuesta es exitosa
                    producto.setStock(producto.getStock() + cantidad);
                    holder.textViewStock.setText(String.format("Stock: %.0f", producto.getStock()));
                    Toast.makeText(context, "Stock aumentado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al aumentar stock: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AumentarStockResponse> call, Throwable t) {
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}