package com.example.meatgo.RetroFit;

import com.example.meatgo.Backend.AdminCambiarEstadoCompletadoRequest;
import com.example.meatgo.Backend.AdminCambiarEstadoCompletadoResponse;
import com.example.meatgo.Backend.AdminCambiarEstadoPedidoRequest;
import com.example.meatgo.Backend.AdminCambiarEstadoPedidoResponse;
import com.example.meatgo.Backend.AdminLoginRequest;
import com.example.meatgo.Backend.AdminLoginResponse;
import com.example.meatgo.Backend.AdminTodosPedidosRequest;
import com.example.meatgo.Backend.AdminTodosPedidosResponse;
import com.example.meatgo.Backend.AumentarStockRequest;
import com.example.meatgo.Backend.AumentarStockResponse;
import com.example.meatgo.Backend.CestaRequest;
import com.example.meatgo.Backend.CestaResponse;
import com.example.meatgo.Backend.EliminarProductoCestaRequest;
import com.example.meatgo.Backend.LoginRequest;
import com.example.meatgo.Backend.LoginResponse;
import com.example.meatgo.Backend.MisPedidosAcabadosRequest;
import com.example.meatgo.Backend.MisPedidosAcabadosResponse;
import com.example.meatgo.Backend.MisPedidosRequest;
import com.example.meatgo.Backend.MisPedidosResponse;
import com.example.meatgo.Backend.ProductoResponse;
import com.example.meatgo.Backend.RegisterRequest;
import com.example.meatgo.Backend.ReservarCantidadRequest;
import com.example.meatgo.Backend.TipoCarneResponse;
import com.example.meatgo.Backend.VerDetallesPedidoRequest;
import com.example.meatgo.Backend.VerDetallesPedidoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("registrarse")
    Call<Void> registrarUsuario(@Body RegisterRequest request);

    @POST("iniciar_sesion")
    Call<LoginResponse> iniciarSesion(@Body LoginRequest request);

    @GET("tipos_carne")
    Call<TipoCarneResponse> obtenerTiposCarne();

    @GET("productos")
    Call<ProductoResponse> obtenerProductos();

    @POST("/reservar_cantidad")
    Call<Void> reservarCantidad(@Body ReservarCantidadRequest request);

    @POST("/cesta")
    Call<CestaResponse> verCesta(@Body CestaRequest request);

    @POST("/eliminar_producto_cesta")
    Call<Void> eliminarProductoDeCesta(@Body EliminarProductoCestaRequest request);

    @POST("/ver_solicitud")
    Call<MisPedidosResponse> verPedidoSolicitud(@Body MisPedidosRequest request);

    @POST("/ver_pedido_completado")
    Call<MisPedidosAcabadosResponse> verPedidoSolicitud(@Body MisPedidosAcabadosRequest request);

    @POST("iniciar_sesion_admin")
    Call<AdminLoginResponse> iniciarSesionAdmin(@Body AdminLoginRequest request);

    @POST("/aumentar_stock_producto")
    Call<AumentarStockResponse> aumentarStock(@Body AumentarStockRequest request);

    @POST("/ver_todos_pedidos")
    Call<AdminTodosPedidosResponse> verTodosPedidos(@Body AdminTodosPedidosRequest request);

    @POST("ver_detalles_pedidos")
    Call<VerDetallesPedidoResponse> verDetallesPedidos(@Body VerDetallesPedidoRequest request);

    @POST("/cambiar_estado_pedido_pendiente")
    Call<AdminCambiarEstadoPedidoResponse> cambiarEstadoPedidoPendiente(@Body AdminCambiarEstadoPedidoRequest request);

    @POST("cambiar_estado_pedido_completado")
    Call<AdminCambiarEstadoCompletadoResponse> cambiarEstadoPedidoCompletado(@Body AdminCambiarEstadoCompletadoRequest request);
}





