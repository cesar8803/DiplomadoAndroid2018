package com.example.adrian.conagua_almonaci.Parseo;

public class NombresListaSpinner{
    private String NombreLista;

    public NombresListaSpinner(String nombreLista) {
        this.NombreLista=nombreLista;
    }

    public String getNombreLista() {
        return NombreLista;
    }

    public void setNombreLista(String nombreLista) {
        NombreLista = nombreLista;
    }

    @Override
    public String toString(){
        return NombreLista;
    }
}
