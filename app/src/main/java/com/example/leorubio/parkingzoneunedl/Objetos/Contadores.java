package com.example.leorubio.parkingzoneunedl.Objetos;

public class Contadores {
    String numAlumno,numMaestro;

    public Contadores() {

    }

    public Contadores(String numAlumno, String numtMaestro) {
        this.numAlumno = numAlumno;
        this.numMaestro = numtMaestro;
    }

    public String getNumAlumno() {
        return numAlumno;
    }

    public void setNumAlumno(String numAlumno) {
        this.numAlumno = numAlumno;
    }

    public String getNumMaestro() {
        return numMaestro;
    }

    public void setNumtMaestro(String numtMaestro) {
        this.numMaestro = numtMaestro;
    }

    @Override
    public String toString() {
        return "Contadores{" +
                "numAlumno='" + numAlumno + '\'' +
                ", numMaestro='" +numMaestro + '\'' +
                '}';
    }
}
