package com.example.meatgo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meatgo.AdminDetallesPedidos;
import com.example.meatgo.Models.Pedido;
import com.example.meatgo.R;

import java.util.List;

public class AdminPedidosAdapter extends RecyclerView.Adapter<AdminPedidosAdapter.ViewHolder> {

    private List<Pedido> pedidos;
    private Context context;

    public AdminPedidosAdapter(Context context, List<Pedido> pedidos) {
        this.context = context;
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public AdminPedidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_admin_pedidos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminPedidosAdapter.ViewHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        holder.tvPedidoID.setText("ID Pedido: " + pedido.getIdPedidos());
        holder.tvUsuarioNombre.setText("Usuario ID: " + pedido.getUsuarioId());
        holder.tvFecha.setText("Fecha: " + pedido.getFecha());
        holder.tvEstado.setText("Estado: " + pedido.getEstado());

        holder.buttonAccion.setOnClickListener(v -> {
            Intent intent = new Intent(context, AdminDetallesPedidos.class);
            // Cambié aquí el nombre del extra a "pedidoId"
            intent.putExtra("pedidoId", pedido.getIdPedidos());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPedidoID, tvUsuarioNombre, tvFecha, tvEstado;
        Button buttonAccion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPedidoID = itemView.findViewById(R.id.tvPedidoID);
            tvUsuarioNombre = itemView.findViewById(R.id.tvUsuarioNombre);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            buttonAccion = itemView.findViewById(R.id.buttonAccion);
        }
    }
}
