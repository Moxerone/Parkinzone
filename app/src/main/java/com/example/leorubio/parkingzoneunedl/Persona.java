package com.example.leorubio.parkingzoneunedl;

public class Persona {
    public String nombre,placas,carrera,activo,tipo,salida,entrada;

    public Persona(){
    }

    public Persona(String nombre, String placas, String carrera, String activo, String tipo, String salida, String entrada) {
        this.nombre = nombre;
        this.placas = placas;
        this.carrera = carrera;
        this.activo = activo;
        this.tipo = tipo;
        this.salida = salida;
        this.entrada = entrada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", placas='" + placas + '\'' +
                ", carrera='" + carrera + '\'' +
                ", activo='" + activo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", salida='" + salida + '\'' +
                ", entrada='" + entrada + '\'' +
                '}';
    }
}
