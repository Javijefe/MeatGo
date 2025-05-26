package com.example.meatgo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.AdminPedidosActivity;
import com.example.meatgo.Models.TipoCarne;
import com.example.meatgo.Models.Producto;
import com.example.meatgo.R;

import java.util.ArrayList;
import java.util.List;

public class AdminTiposCarneAdapter extends RecyclerView.Adapter<AdminTiposCarneAdapter.ViewHolder> {
    private List<TipoCarne> tiposCarne;
    private List<Producto> productos;
    private Context context;

    public AdminTiposCarneAdapter(List<TipoCarne> tiposCarne, List<Producto> productos, Context context) {
        this.tiposCarne = tiposCarne;
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Asegúrate de que el layout que estás inflando sea el correcto
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_tipos_carne, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TipoCarne tipoCarne = tiposCarne.get(position);
        holder.textViewNombre.setText(tipoCarne.getNombre());

        // Filtrar productos por tipo de carne
        List<Producto> productosPorTipo = obtenerProductosPorTipo(tipoCarne.getIdTiposCarne());

        // Usar el adapter para admin
        AdminProductosAdapter adminProductosAdapter = new AdminProductosAdapter(productosPorTipo, holder.itemView.getContext());
        holder.recyclerViewProductos.setAdapter(adminProductosAdapter);
        holder.recyclerViewProductos.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        // Botón para ir a pedidos
        holder.btnPedidos.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, AdminPedidosActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tiposCarne.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNombre;
        public RecyclerView recyclerViewProductos;
        public Button btnPedidos;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            recyclerViewProductos = itemView.findViewById(R.id.recyclerAdminViewProductos);
            btnPedidos = itemView.findViewById(R.id.btn_pedidos);

        }
    }

    private List<Producto> obtenerProductosPorTipo(int idTipoCarne) {
        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getTipoId() == idTipoCarne) {
                productosFiltrados.add(producto);
            }
        }
        return productosFiltrados;
    }
}