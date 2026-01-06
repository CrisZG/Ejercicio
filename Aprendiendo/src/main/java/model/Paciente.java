package model;

import java.util.List;
import java.util.Objects;

public class Paciente {

    private String nombre;
    private int edad;

    public Paciente(String nombre, int edad) {
        if (nombre == null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no debe estar vacio");
        }
        if (edad < 0 || edad > 120){
            throw new IllegalArgumentException("Edad invalida");
        }
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return edad == paciente.edad && Objects.equals(nombre, paciente.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad);
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean esMayorDeEdad(){
        return edad>=18;
    }

}
