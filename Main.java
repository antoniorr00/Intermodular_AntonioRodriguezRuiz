//Antonio Rodríguez Ruiz
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //creamos una lista de pacientes
    private static ArrayList<Paciente> listaPacientes = new ArrayList<>();

    //hacemos el scanner para poder usarlo en toda la clase
    private static Scanner scanner = new Scanner(System.in);

    //validamos la fecha
    public static boolean validacionFechaNac(String fecha) {
        if (fecha == null || fecha.length() != 10) return false;
        String[] partes = fecha.split("/");
        if (partes.length != 3) return false;
        try {
            int dia = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int anio = Integer.parseInt(partes[2]);
            if (mes < 1 || mes > 12) return false;
            int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            return dia >= 1 && dia <= diasPorMes[mes - 1];
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //metodo imprimir menu
    public static void imprimirMenu(){
        System.out.println();
        System.out.println("Menú:");
        System.out.println("1. Insertar paciente");
        System.out.println("2. Eliminar paciente");
        System.out.println("3. Actualizar paciente");
        System.out.println("4. Consultar paciente");
        System.out.println("5. Imprimir pacientes");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
    }

    //validacion de telefono
    public static boolean validacionTelefono(String numeroTelefono) {
        return numeroTelefono.matches("\\d{9}"); //utilizo .matches() para que compare con numero de telefono, comprobando que se han introducido 9 digitos
    }                                            // devolvera true si se cumple, no haciendo falta el uso de un if-else

    //validacion de sip que seran 8 digitos numericos
    public static boolean validacionSIP(String sip) {
        return sip.matches("\\d{8}");
    }

    //validacion nombre
    public static boolean validacionNombrePaciente(String nombre) {
        return nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"); //solo dejaremos introducir letras y espacios
    }

    //metodo de añadir paciente
    public static void addPaciente(Paciente paciente) {
        if (findPaciente(paciente.getSIP_paciente()) == null) { //si el paciente no se encuentra ya en la lista
            listaPacientes.add(paciente); // lo añadimos
            System.out.println("Paciente agregado correctamente.");
        } else { //si se encuentra ya en la lista
            System.out.println("Error: SIP ya registrado.");
        }
    }

    //metodo para devolver un paciente buscando por su sip
    public static Paciente findPaciente(String sip) {
        for (Paciente p : listaPacientes) { //buscamos en la lista de pacientes por su sip
            if (p.getSIP_paciente().equals(sip)) {
                return p;
            }
        }
        return null;
    }

    //metodo para eliminar un paciente buscando por su sip
    public static void removePaciente(String sip) {
        Paciente paciente = findPaciente(sip);
        if (paciente != null) {
            listaPacientes.remove(paciente);
            System.out.println("Paciente eliminado correctamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    //metodo para actualizar un paciente
    public static void updatePaciente(String sip, String nuevoNombre, String nuevoTelefono, String nuevaFecha) {
        Paciente paciente = findPaciente(sip);
        if (paciente != null) {
            if (validacionNombrePaciente(nuevoNombre) && validacionTelefono(nuevoTelefono) && validacionFechaNac(nuevaFecha)) {
                paciente.setNombrePac(nuevoNombre); //actualizamos los nuevos datos
                paciente.setTelefono(nuevoTelefono);
                paciente.setFechaNac(nuevaFecha);
                System.out.println("Paciente actualizado correctamente.");
            } else {
                System.out.println("Error: Alguno de los datos es inválido."); 
            }
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    //metodo para buscar un paciente por su sip e imprimir su nombre
    public static void queryPaciente(String sip) {
        Paciente paciente = findPaciente(sip);
        if (paciente != null) {
            System.out.println("Paciente: " + paciente.getNombrePac());
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    //metodo para imprimir la lista de pacientes
    public static void printPacientes() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Lista de Pacientes:");
            for (Paciente p : listaPacientes) {
                System.out.println("SIP: " + p.getSIP_paciente() + ", Nombre: " + p.getNombrePac());
            }
        }
    }

    public static void main(String[] args) {
        boolean continuar = true;
        while (continuar) {
            imprimirMenu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        String sip, nombre, telefono, fecha;
                        System.out.println();
                        do {
                            System.out.print("Introduce el SIP: ");
                            sip = scanner.nextLine();
                            if (!validacionSIP(sip)) {
                                System.out.println("Error: SIP debe contener 8 dígitos.");
                            }
                        } while (!validacionSIP(sip));

                        do {
                            System.out.print("Ingrese el nombre del paciente: ");
                            nombre = scanner.nextLine();
                            if (!validacionNombrePaciente(nombre)) {
                                System.out.println("Error: Nombre del paciente no es correcto. Solo puedes introducir letras y espacios");
                            }
                        } while (!validacionNombrePaciente(nombre));


                        do {
                            System.out.print("Introduce el teléfono (9 dígitos): ");
                            telefono = scanner.nextLine();
                            if (!validacionTelefono(telefono)) {
                                System.out.println("Error: el telefono no es correcto. Solo puedes introducir numeros y hasta 9 dígitos.");
                            }
                        } while (!validacionTelefono(telefono));

                        do {
                            System.out.print("Introduce la fecha de nacimiento (dd/MM/yyyy): ");
                            fecha = scanner.nextLine();
                            if (!validacionFechaNac(fecha)) {
                                System.out.println("Error: la fecha es incorrecta. Este es el formato que debes introducir (dd/MM/yyyy).");
                            }
                        } while (!validacionFechaNac(fecha));

                        addPaciente(new Paciente(fecha, telefono, nombre, sip));
                        break;

                    case 2:
                        System.out.println();
                        System.out.print("Introduce SIP a eliminar: ");
                        sip = scanner.nextLine();
                        removePaciente(sip);
                        break;

                    case 3:
                        System.out.println();
                        System.out.print("Introduce SIP a actualizar: ");
                        sip = scanner.nextLine();
                        System.out.print("Nuevo Nombre: ");
                        nombre = scanner.nextLine();
                        System.out.print("Nuevo Teléfono: ");
                        telefono = scanner.nextLine();
                        System.out.print("Nueva Fecha: ");
                        fecha = scanner.nextLine();
                        updatePaciente(sip, nombre, telefono, fecha);
                        break;

                    case 4:
                        System.out.println();
                        System.out.print("Introduce SIP a consultar: ");
                        sip = scanner.nextLine();
                        queryPaciente(sip);
                        break;

                    case 5:
                        System.out.println();
                        printPacientes();
                        break;
                    case 6:
                        System.out.println();
                        System.out.println("Has salido del programa.");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Introduce un número válido.");
            }
        }
        scanner.close();
    }
}
