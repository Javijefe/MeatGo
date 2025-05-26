package com.example.meatgo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.Backend.CestaResponse;
import com.example.meatgo.Backend.MisPedidosAcabadosResponse;
import com.example.meatgo.R;

import java.util.List;

public class MisPedidosAcabadosAdapter extends RecyclerView.Adapter<MisPedidosAcabadosAdapter.ViewHolder> {

    private List<MisPedidosAcabadosResponse> pedidos;
    private Context context;

    public MisPedidosAcabadosAdapter(List<MisPedidosAcabadosResponse> pedidos, Context context) {
        this.pedidos = pedidos;
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
        MisPedidosAcabadosResponse pedido = pedidos.get(position);
        holder.tvProductoNombre.setText("Pedido ID: " + pedido.getId_pedido());
        holder.tvSubtotal.setText("Total: $" + pedido.getTotal());

        // Mostrar los productos en un formato adecuado
        StringBuilder productosString = new StringBuilder();
        for (CestaResponse.ProductoCesta producto : pedido.getProductos()) {
            productosString.append(producto.getProducto())
                    .append(" (Cantidad: ")
                    .append(producto.getCantidad())
                    .append(")\n");
        }
        holder.tvCantidad.setText(productosString.toString().trim());
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
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