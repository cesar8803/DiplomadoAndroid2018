package com.example.adrian.conagua_almonaci.Parseo;

public class EstadoListaSpinner {
    private String EstadoLista;

    public EstadoListaSpinner(String estadoLista) {
        this.EstadoLista = estadoLista;
    }

    public String getEstadoLista() {
        return EstadoLista;
    }

    public void setEstadoLista(String estadoLista) {
        EstadoLista = estadoLista;
    }

    @Override
    public String toString(){
        return EstadoLista;
    }
}
