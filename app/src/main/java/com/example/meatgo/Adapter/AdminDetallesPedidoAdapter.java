package com.example.meatgo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.Backend.VerDetallesPedidoResponse;
import com.example.meatgo.R;

import java.util.List;

public class AdminDetallesPedidoAdapter extends RecyclerView.Adapter<AdminDetallesPedidoAdapter.ViewHolder> {

    private List<VerDetallesPedidoResponse.DetallePedido> detalles;
    private Context context;

    public AdminDetallesPedidoAdapter(Context context, List<VerDetallesPedidoResponse.DetallePedido> detalles) {
        this.context = context;
        this.detalles = detalles;
    }

    public void setDetalles(List<VerDetallesPedidoResponse.DetallePedido> detalles) {
        this.detalles = detalles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdminDetallesPedidoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_admin_item_detalle_pedido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminDetallesPedidoAdapter.ViewHolder holder, int position) {
        VerDetallesPedidoResponse.DetallePedido detalle = detalles.get( position);

        holder.tvProductoId.setText("Producto ID: " + detalle.getProducto_id());
        holder.tvCantidad.setText("Cantidad: " + detalle.getCantidad());
        holder.tvPrecio.setText("Precio: $" + detalle.getPrecio());
        holder.tvSubtotal.setText("Subtotal: $" + detalle.getSubtotal());
    }

    @Override
    public int getItemCount() {
        return detalles == null ? 0 : detalles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductoId, tvCantidad, tvPrecio, tvSubtotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductoId = itemView.findViewById(R.id.text_producto_id);
            tvCantidad = itemView.findViewById(R.id.text_cantidad);
            tvPrecio = itemView.findViewById(R.id.text_precio);
            tvSubtotal = itemView.findViewById(R.id.text_subtotal);
        }
    }
}
