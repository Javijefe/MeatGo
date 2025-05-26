package com.example.meatgo.Backend;

import com.example.meatgo.Models.Pedido;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AdminTodosPedidosResponse {
    @SerializedName("pedidos")
    private List<Pedido> pedidos;

    public AdminTodosPedidosResponse(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
