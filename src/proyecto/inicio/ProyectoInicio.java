/*Autora: María Celeste Umaña Castro
Fecha: 16 de febrero del 2024
Proyecto: Crear un programa con el cual se puedan gestionar tareas ya sea agregando, filtrando, mostrando, eliminando y marcando, esto haciendo uso de principios de la POO para el ordenamiento del código

REFERENCIAS BIBLIOGRÁFICAS

1.Aprendiendo hoy con Gonzales. (7 de mar del 2021). 154 Collection List ArrayList MODIFICAR Y ELIMINAR ELEMENTOS PARTE 5. https://www.youtube.com/watch?v=dVLbxP4M7XU&t=1s

 */

package proyecto.inicio;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProyectoInicio {

    //Atributos de la clase ProyectoInicio
    private String resp = "";
    private String respNueva = "";
    private boolean salir = false;
    private String temaVariable = "";

    private Tarea tarea1 = new Tarea();//Instancia de la clase tarea

    public static void main(String[] args) {
        //Declaracion e iniciacion de las variables del main
        int opcion = 0;

        Scanner sc = new Scanner(System.in);
        ProyectoInicio mainProyecto = new ProyectoInicio();//Instancia de la clase ProyectoInicio

        do {

            System.out.println("------GESTION DE TAREAS------\n");
            System.out.println("[1] Agregar tareas");
            System.out.println("[2] Ver tareas");
            System.out.println("[3] Filtrar tareas");
            System.out.println("[4] Marcar como completada");
            System.out.println("[5] Eliminar tareas");
            System.out.println("[6] Salir\n");

            //Manejo de la excepcion en caso de que el tipo de dato que se espera leer no coincida
            //https://ibmcsr.udemy.com/course/aprender-a-programar-con-java-de-cero-hasta-avanzado/learn/lecture/5649286#overview
            try {
                System.out.print("Digite la opción: ");
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("\nSe produjo una excepcion: " + e);
                sc.nextLine();
            }

            System.out.println("\n");

            switch (opcion) {
                case 1:
                    mainProyecto.AgregarTareas(opcion, sc);
                    break;

                case 2:
                    mainProyecto.VisualizacionDeTareas(opcion, sc);
                    break;

                case 3:
                    mainProyecto.FiltrarTareasPorEstadoYFecha(opcion, sc);
                    break;

                case 4:
                    mainProyecto.MarcarComoCompletadaUnaTarea(opcion, sc);
                    break;

                case 5:
                    mainProyecto.EliminarTareas(opcion, sc);
                    break;

                case 6:
                    System.out.println("\n\nSaliendo de la gestión de tareas...");
                    break;

                default:
                    System.out.println("La opción seleccionada no es válida, intentelo de nuevo.\n\n");
                    break;
            }
        } while (opcion != 6);
        sc.close();
    }

    //Metodo para agregar una tarea en la clase Proyecto inicio
    public void AgregarTareas(int opcion, Scanner sc) {
        do {

            System.out.println("_________________________________________________________________________");
            System.out.println("\t\t\t\tAGREGAR TAREAS");
            System.out.println("-------------------------------------------------------------------------\n");

            System.out.print("Fecha de vencimiento(Opcional) : ");
            tarea1.setFechaVencimiento(sc.nextLine());

            System.out.print("\nEncargado(Nombre y apellido) : ");
            tarea1.setEncargadoNombre(sc.nextLine());

            System.out.print("\nTarea : ");
            tarea1.setTareasDescripcion(sc.nextLine());

            if (("".equals(tarea1.getEncargadoNombre()) && ("".equals(tarea1.getTareasDescripcion())))) {
                break;
            } else {
                tarea1.agregarTarea();
            }

            VolverAlMenu(opcion, sc);
        } while (!salir);
    }

    //Metodo para visualizar las tarea en la clase Proyecto inicio
    public void VisualizacionDeTareas(int opcion, Scanner sc) {
        do {
            System.out.println("\n\n_________________________________________________________________________");
            System.out.println("\t\t\t\tLISTA DE TAREAS");
            tarea1.VisualizarTareas();

            VolverAlMenu(opcion, sc);
        } while (!salir);
    }

    //Metodo para marcar una tarea como completa en la clase Proyecto inicio
    public void MarcarComoCompletadaUnaTarea(int opcion, Scanner sc) {
        do {
            System.out.println("\n\n_________________________________________________________________________");
            System.out.println("\t\t\tMARCAR COMO COMPLETADA");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\n   (El código de las tareas se identifica de la siguiente forma 'Txxx')\n");

            System.out.print("   Inserte el código de la tarea que desea marcar como completada: ");
            tarea1.setIdentificadorAuto(sc.nextLine().toUpperCase());

            System.out.println("\n-------------------------------------------------------------------------\n");
            tarea1.marcarCompletada();

            VolverAlMenu(opcion, sc);
        } while (!salir);
    }

    //Metodo para filtrar una tarea por fecha o por estado en la clase Proyecto inicio
    public void FiltrarTareasPorEstadoYFecha(int opcion, Scanner sc) {
        do {
            System.out.println("\n\n_________________________________________________________________________");
            System.out.println("\t\t\t\tFILTRAR TAREAS");
            System.out.println("-------------------------------------------------------------------------\n");
            System.out.println("[1] Filtrar por estado (Pendiente/Completada)");
            System.out.println("[2] Filtrar por fecha\n");

            System.out.print("\t\t\tDigite la opcion que desea: ");
            int filtrarDatos = sc.nextInt();
            sc.nextLine();
            System.out.println("-------------------------------------------------------------------------\n");

            if (filtrarDatos == 1) {
                System.out.println("\t\t\t-----FILTRAR POR ESTADO-------");
                System.out.println("\t\t\t\t[1] Pendientes");
                System.out.println("\t\t\t\t[2] Completado\n");

                System.out.print("\t\t\tPor que estado desea filtrar: ");
                tarea1.setEstadoTareas(sc.nextInt());

                System.out.println("-------------------------------------------------------------------------");
                tarea1.filtrarPorEstado();
                sc.nextLine();

            } else {
                System.out.println("\t\t\t-------FILTRAR POR FECHA-------");
                System.out.print("\t\t\t    Fecha : ");
                tarea1.setFechaVencimiento(sc.nextLine());

                System.out.println("-------------------------------------------------------------------------");
                tarea1.filtrarPorFecha();
            }

            VolverAlMenu(opcion, sc);
        } while (!salir);
    }

    //Metodo para eliminar una tarea en la clase Proyecto inicio
    public void EliminarTareas(int opcion, Scanner sc) {
        do {
            System.out.println("\n\n_________________________________________________________________________");
            System.out.println("\n\t\t\tEIMINAR TAREAS");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("\n   (El código de las tareas se identifica de la siguiente forma 'Txxx')\n");

            System.out.print("   Inserte el código de la tarea que desea eliminar: ");
            tarea1.setIdentificadorAuto(sc.nextLine().toUpperCase());

            System.out.println("\n-------------------------------------------------------------------------");
            tarea1.eliminarTareas();

            VolverAlMenu(opcion, sc);

        } while (!salir);
    }

    //Metodo para volver al menu dentro de cada case
    public void VolverAlMenu(int opcion, Scanner sc) {

        do {
            System.out.println("-------------------------------------------------------------------------");
            System.out.print("\n\t\t¿Desea volver al menú principal?(S/N): ");
            resp = sc.next().toUpperCase();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    temaVariable = "agregar otra tarea";
                    VolverAlMenuReutilizable(opcion, sc);
                    break;

                case 2:
                    salir = false;
                    if ("N".equals(resp)) {
                        System.out.println("\t\tEs necesario volver al menú para continuar...");
                    } else if (!"N".equals(resp) && !"S".equals(resp)) {
                        System.out.println("\tLa respuesta ingresada no es válida, intente nuevamente...");
                    } else {
                        salir = true;
                        System.out.println("\t\tVolviendo al menú principal...");
                        System.out.println("_________________________________________________________________________\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    }
                    break;

                case 3:
                    temaVariable = "filtrar más tareas";
                    VolverAlMenuReutilizable(opcion, sc);
                    break;

                case 4:
                    temaVariable = "marcar como completada otra tarea";
                    VolverAlMenuReutilizable(opcion, sc);
                    break;

                case 5:
                    temaVariable = "eliminar otra tarea";
                    VolverAlMenuReutilizable(opcion, sc);
                    break;
            }
        } while (!"S".equals(resp) && !"N".equals(resp) && !"N".equals(respNueva) && !"S".equals(respNueva));

    }

    //Metodo de codigo reutilizado en los diferentes cases del Metodo VolverAlMenu 
    public void VolverAlMenuReutilizable(int opcion, Scanner sc) {
        salir = false;
        if ("N".equals(resp)) {
            System.out.print("\t\t¿Desea " + temaVariable + " ?(S/N): ");
            respNueva = sc.next().toUpperCase();
            sc.nextLine();
            if ("N".equals(respNueva)) {
                salir = true;
                System.out.println("\t\tEs necesario volver al menú para continuar...");
                System.out.println("_________________________________________________________________________\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            } else if (!"N".equals(respNueva) && !"S".equals(respNueva)) {
                System.out.println("\t\tLa respuesta ingresada no es válida, intente nuevamente...");
            }
        } else if (!"N".equals(resp) && !"S".equals(resp)) {
            System.out.println("\tLa respuesta ingresada no es válida, intente nuevamente...");
        } else {
            salir = true;
            System.out.println("\t\tVolviendo al menú principal...");
            System.out.println("_________________________________________________________________________\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
    }
}
