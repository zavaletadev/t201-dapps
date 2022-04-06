package dev.zavaletazea.uteq.dapps.t201.proyectofinal.entities;

/*
En esta clase representamos la tabla de la
base de datos
 */
public class TipoProducto {

    private int idtp;
    private String descripcion;

    /*
    Generamos los Getters y setters
    de los atributos
     */

    public int getIdtp() {
        return idtp;
    }

    public void setIdtp(int idtp) {
        this.idtp = idtp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
