package com.javierg.appcontactos;

public class Contacto {
    private String nombre;
    private String numero;
    private int edad;
    private String genero;
    private String rol;

    public Contacto(String nombre, String numero, int edad, String genero,String rol) {
        this.nombre = nombre;
        this.numero = numero;
        this.edad = edad;
        this.genero = genero;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}