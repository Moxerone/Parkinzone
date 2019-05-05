package com.example.leorubio.parkingzoneunedl.Objetos;

public class Maestros {
    String nombre,tipo, carrera, placas, entrada,salida,activo;
    public Maestros() {
    }

    public Maestros(String nombre, String tipo, String carrera, String placas, String entrada, String salida, String activo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.carrera = carrera;
        this.placas = placas;
        this.entrada = entrada;
        this.salida = salida;
        this.activo = activo;

    }



    public String getNombre() { return nombre;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Maestros{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", carrera='" + carrera + '\'' +
                ", placas='" + placas + '\'' +
                ", entrada='" + entrada + '\'' +
                ", salida='" + salida + '\'' +
                ", activo='" + activo + '\'' +
                '}';
    }
}
