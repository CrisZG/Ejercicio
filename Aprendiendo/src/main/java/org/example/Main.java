package org.example;

import model.Paciente;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<String, Paciente> pacientesPorNombre = new HashMap<>();
        String seguir = "s";

        while(true){
            System.out.println("\n ----MENU----");
            System.out.println("1) Agregar paciente");
            System.out.println("2) Buscar paciente");
            System.out.println("3) Eliminar paciente");
            System.out.println("4) Listar pacientes");
            System.out.println("5) Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (opcion == 1){
                agregarPaciente(scanner,pacientesPorNombre);
            } else if(opcion == 2){
                buscarPaciente(scanner,pacientesPorNombre);
            } else if(opcion == 3){
                eliminarPaciente(scanner, pacientesPorNombre);
            }
            else if(opcion==5){
                System.out.println("Saliendo del programa...");
                break;
            }

        }
    }
    public static void agregarPaciente(Scanner scanner, Map<String, Paciente> pacientes){
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la edad del paciente: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        try {
            String nombreNormalizado = normalizarNombre(nombre);
            if(pacientes.containsKey(nombreNormalizado)){
                System.out.println("Error: Ya existe un paciente con ese nombre.");
                return;
            }
            pacientes.put(nombreNormalizado, new Paciente(nombre, edad));
            System.out.println("Paciente agregado exitosamente.");
        }catch(Exception e){
            System.out.println("Error al agregar paciente: " + e.getMessage());
        }

    }

    public static void buscarPaciente(Scanner scanner, Map<String, Paciente> pacientes){
        System.out.println("Ingrese el nombre del paciente a buscar: ");
        String nombre = scanner.nextLine();
        String nombreNormalizado = normalizarNombre(nombre);

        Paciente pacienteEncontrado = pacientes.get(nombreNormalizado);

        if (pacienteEncontrado == null){
            System.out.println("Paciente no encontrado.");
            return;
        } else {
            System.out.println("Paciente encontrado: " + pacienteEncontrado.getNombre() + ", Edad: " + pacienteEncontrado.getEdad());
            System.out.println("Tipo de paciente: " + (pacienteEncontrado.esMayorDeEdad() ? "Adulto" : "Pediatrico"));
        }
    }

    public static void eliminarPaciente(Scanner scanner, Map<String, Paciente> pacientes){
        System.out.println("Ingrese el nombre del paciente a eliminar");
        String nombre = scanner.nextLine();
        String nombreNormalizado = normalizarNombre(nombre);

        Paciente pacienteAEliminar = pacientes.get(nombreNormalizado);

        if (pacienteAEliminar == null){
            System.out.println("Paciente no encontrado.");
            return;
        } else {
            pacientes.remove(nombreNormalizado);
            System.out.println("Paciente eliminado exitosamente.");
        }
    }

    public static String normalizarNombre(String nombre){
        return nombre.trim().toLowerCase();
    }
}