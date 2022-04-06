package dev.zavaletazea.uteq.dapps.t201.proyectofinal.entities;

public class Producto {

    private int idProducto;
    private String nomProducto;
    private TipoProducto idtp;
    private double precio;
    private int existencia;

    public int getIdProducto() {
        return idProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public TipoProducto getIdtp() {
        return idtp;
    }

    public double getPrecio() {
        return precio;
    }

    public int getExistencia() {
        return existencia;
    }
}
