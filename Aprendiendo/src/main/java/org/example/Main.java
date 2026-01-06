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
            int opcion = leerEnteroSeguro(scanner, "Elige una opción: ");

            if (opcion == 1){
                agregarPaciente(scanner,pacientesPorNombre);
            } else if(opcion == 2){
                buscarPaciente(scanner,pacientesPorNombre);
            } else if(opcion == 3){
                eliminarPaciente(scanner, pacientesPorNombre);
            } else if(opcion==4){
                listarPacientes(pacientesPorNombre);
            }
            else if(opcion==5){
                System.out.println("Saliendo del programa...");
                break;
            } else{
                System.out.println("Opción inválida. Por favor, elige una opción del 1 al 5.");
            }

        }
    }
    public static void agregarPaciente(Scanner scanner, Map<String, Paciente> pacientes){
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        int edad = leerEnteroSeguro(scanner, "Edad: ");

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

    public static Map<String, Paciente> listarPacientes(Map<String, Paciente> pacientes){
        if (pacientes.isEmpty()){
            System.out.println("No hay pacientes registrados.");
        }else{
            System.out.println("Lista de pacientes:");
            for (Paciente p : pacientes.values()){
                System.out.println("Nombre: " + p.getNombre() + ", Edad: " + p.getEdad());
            }
        }
        return pacientes;
    }

    public static String normalizarNombre(String nombre){
        return nombre.trim().toLowerCase();
    }

    public static int leerEnteroSeguro(Scanner scanner, String mensaje){
        while(true){
            System.out.println(mensaje);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch(NumberFormatException e){
                System.out.println("Entrada inválida. Por favor ingrese un número entero.");
            }
        }
    }
}