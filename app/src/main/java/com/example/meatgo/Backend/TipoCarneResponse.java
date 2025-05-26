package com.example.meatgo.Backend;

import com.example.meatgo.Models.TipoCarne;
import java.util.List;

public class TipoCarneResponse {

    private List<TipoCarne> tipos_carne;

    public List<TipoCarne> getTipos_carne() {
        return tipos_carne;
    }

    public void setTipos_carne(List<TipoCarne> tipos_carne) {
        this.tipos_carne = tipos_carne;
    }

}
