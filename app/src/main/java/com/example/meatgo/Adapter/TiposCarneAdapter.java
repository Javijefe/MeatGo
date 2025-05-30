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
public class TiposCarneAdapter extends RecyclerView.Adapter<TiposCarneAdapter.TipoCarneViewHolder> {

    private List<TipoCarne> tiposCarneList;
    private List<Producto> productosList;
    private Context context;

    public TiposCarneAdapter(Context context, List<TipoCarne> tiposCarneList, List<Producto> productosList) {
        this.context = context;
        this.tiposCarneList = tiposCarneList;
        this.productosList = productosList;
    }

    @NonNull
    @Override
    public TipoCarneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tipos_carne, parent, false);
        return new TipoCarneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipoCarneViewHolder holder, int position) {
        TipoCarne tipoCarne = tiposCarneList.get(position);
        holder.textViewNombreTipo.setText(tipoCarne.getNombre());

        List<Producto> productosFiltrados = new ArrayList<>();
        for (Producto producto : productosList) {
            if (producto.getTipoId() == tipoCarne.getIdTiposCarne()) {
                productosFiltrados.add(producto);
            }
        }

        ProductosAdapter productosAdapter = new ProductosAdapter(productosFiltrados,context);
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
        return tiposCarneList.size();
    }

    static class TipoCarneViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreTipo;
        RecyclerView recyclerViewProductos;
        public Button btnCarrito;


        public TipoCarneViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombreTipo = itemView.findViewById(R.id.textViewNombre);
            recyclerViewProductos = itemView.findViewById(R.id.recyclerViewProductos);
            btnCarrito = itemView.findViewById(R.id.btn_Carrito);

        }
    }
}

