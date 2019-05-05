package com.example.leorubio.parkingzoneunedl.Objetos;

import android.util.Half;

public class Visitantes {

    String nombre;
    String motivo;
    String placas;
    String horaSalida;
    String horaEntrada;

    public Visitantes() {
    }


    public Visitantes(String nombre, String motivo, String placas, String horaSalida, String horaEntrada) {

        this.nombre = nombre;
        this.motivo = motivo;
        this.placas = placas;
        this.horaSalida = horaSalida;
        this.horaEntrada = horaEntrada;




    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {  return horaSalida; }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }





    @Override
    public String toString() {
        return "Visitantes{" +
                "nombre='" + nombre + '\'' +
                ", motivo='" + motivo + '\'' +
                ", placas='" + placas + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", horaEntrada='" + horaEntrada + '\'' +
                '}';
    }


}


