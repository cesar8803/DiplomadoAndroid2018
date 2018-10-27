package com.example.adrian.conagua_almonaci.Parseo;

public class ListaRespaldo {

    private String ListaRespald;
    private String ListaRespaldd;
    private String ListaRespalddd;

    public ListaRespaldo(String ListaRespalddd, String ListaRespaldd, String ListaRespald) {
        this.ListaRespalddd=ListaRespalddd;
        this.ListaRespaldd=ListaRespaldd;
        this.ListaRespald=ListaRespald;
    }

    public String getListaRespald() {
        return ListaRespald;
    }

    public void setListaRespald(String listaRespald) {
        ListaRespald = listaRespald;
    }

    public String getListaRespaldd() {
        return ListaRespaldd;
    }

    public void setListaRespaldd(String listaRespaldd) {
        ListaRespaldd = listaRespaldd;
    }

    public String getListaRespalddd() {
        return ListaRespalddd;
    }

    public void setListaRespalddd(String listaRespalddd) {
        ListaRespalddd = listaRespalddd;
    }

    @Override
    public String toString(){
        return ListaRespald;

    }



}
