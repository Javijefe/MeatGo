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

import com.example.meatgo.CarritoActivity;
import com.example.meatgo.Models.TipoCarne;
import com.example.meatgo.Models.Producto;
import com.example.meatgo.R;

import java.util.ArrayList;
import java.util.List;

public class TiposCarneAdapter extends RecyclerView.Adapter<TiposCarneAdapter.ViewHolder> {
    private List<TipoCarne> tiposCarne;
    private List<Producto> productos;

    public TiposCarneAdapter(List<TipoCarne> tiposCarne, List<Producto> productos) {
        this.tiposCarne = tiposCarne;
        this.productos = productos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tipos_carne, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TipoCarne tipoCarne = tiposCarne.get(position);
        holder.textViewNombre.setText(tipoCarne.getNombre());

        // Filtramos los productos por tipo de carne
        List<Producto> productosPorTipo = obtenerProductosPorTipo(tipoCarne.getIdTiposCarne());


        ProductosAdapter productosAdapter = new ProductosAdapter(productosPorTipo, holder.itemView.getContext());
        holder.recyclerViewProductos.setAdapter(productosAdapter);
        holder.recyclerViewProductos.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        //Ir al carrito
        holder.btnCarrito.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, CarritoActivity.class);
            context.startActivity(intent);
            //No ponemos el finish para poder volver
        });
    }

    @Override
    public int getItemCount() {
        return tiposCarne.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNombre;
        public RecyclerView recyclerViewProductos;
        public Button btnCarrito;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            recyclerViewProductos = itemView.findViewById(R.id.recyclerViewProductos);
            btnCarrito = itemView.findViewById(R.id.btn_Carrito);
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
