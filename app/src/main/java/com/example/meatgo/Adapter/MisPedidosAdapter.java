package com.example.meatgo.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.meatgo.Backend.CestaResponse;
import com.example.meatgo.Backend.MisPedidosRequest;
import com.example.meatgo.Backend.MisPedidosResponse;
import com.example.meatgo.R;
import com.example.meatgo.RetroFit.ApiClient;
import com.example.meatgo.RetroFit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MisPedidosAdapter extends RecyclerView.Adapter<MisPedidosAdapter.ViewHolder> {

    private List<CestaResponse.ProductoCesta> productos;
    private Context context;

    public MisPedidosAdapter(List<CestaResponse.ProductoCesta> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mis_pedidos_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CestaResponse.ProductoCesta producto = productos.get(position);
        holder.tvProductoNombre.setText(producto.getProducto());
        holder.tvCantidad.setText("Cantidad: " + producto.getCantidad());
        holder.tvSubtotal.setText("Subtotal: $" + producto.getSubtotal());
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }
    private void obtenerMisPedidos() {
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);

        String token = getToken();
        if (token == null) {
            Toast.makeText(context, "Token no disponible, por favor inicie sesión nuevamente", Toast.LENGTH_SHORT).show();
            return;
        }

        MisPedidosRequest request = new MisPedidosRequest(token);

        // Llamada para obtener los pedidos pendientes
        apiService.verPedidoSolicitud(request).enqueue(new Callback<MisPedidosResponse>() {
            @Override
            public void onResponse(Call<MisPedidosResponse> call, Response<MisPedidosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Actualiza la lista de productos
                    productos.clear();

                    // Verifica si la respuesta contiene productos
                    if (response.body().getProductos() != null) {
                        productos.addAll(response.body().getProductos());
                    } else {
                        Toast.makeText(context, "No hay productos en el pedido", Toast.LENGTH_SHORT).show();
                    }

                    notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
                } else {
                    Toast.makeText(context, "Error al obtener los pedidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MisPedidosResponse> call, Throwable t) {
                Toast.makeText(context, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        return sharedPreferences.getString("user_token", null);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductoNombre;
        TextView tvCantidad;
        TextView tvSubtotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductoNombre = itemView.findViewById(R.id.tvProductoNombre);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvSubtotal = itemView.findViewById(R.id.tvSubtotal);
        }
    }
}