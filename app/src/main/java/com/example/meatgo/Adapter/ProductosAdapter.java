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
import com.example.meatgo.Backend.ReservarCantidadRequest;
import com.example.meatgo.Models.Producto;
import com.example.meatgo.R;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {
    private List<Producto> productos;
    private Context context;

    public ProductosAdapter(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_productos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.textViewNombre.setText(producto.getNombre());
        holder.textViewPrecio.setText(String.format("$%.2f", producto.getPrecio()));
        holder.textViewStock.setText(String.format("Stock: %.0f", producto.getStock()));

        //Para cargar las imagenes
        Glide.with(holder.itemView.getContext())
                .load(producto.getFoto())
                .into(holder.imageViewProducto);


        holder.editTextModificarStock.setText(""); // Limpiar el campo al iniciar
        holder.buttonConfirmar.setOnClickListener(v -> {
            String stockInput = holder.editTextModificarStock.getText().toString();
            if (!stockInput.isEmpty()) {
                int cantidadModificar = Integer.parseInt(stockInput);
                if (cantidadModificar <= producto.getStock()) {
                    // Llamada a la API para reservar cantidad
                    reservarCantidadEnBackend(producto, cantidadModificar, holder);
                } else {
                    Toast.makeText(context, "Stock insuficiente", Toast.LENGTH_SHORT).show();
                }
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
        return sharedPreferences.getString("user_token", null);
    }

    private void reservarCantidadEnBackend(Producto producto, int cantidad, ViewHolder holder) {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        String token = getToken();
        if (token == null) {
            Toast.makeText(context, "Token no disponible, por favor inicie sesión nuevamente", Toast.LENGTH_SHORT).show();
            return;
        }

        ReservarCantidadRequest request = new ReservarCantidadRequest(producto.getIdProductos(), cantidad, token);

        // Llamada para actualizar el stock
        apiService.reservarCantidad(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Actualizar el stock local y en la UI
                    producto.setStock(producto.getStock() - cantidad);
                    holder.textViewStock.setText(String.format("Stock: %.0f", producto.getStock()));
                    Toast.makeText(context, "Cantidad reservada con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Error al reservar la cantidad", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
