package proyecto.inicio;

import java.util.ArrayList;

public class Tarea {

    //Atributos generales en todas las tareas
    private String encargadoNombre;
    private String fechaVencimiento;
    private String tareaDescripcion;
    private String identificadorAuto;
    private int estadoTareas;

    
    private final ArrayList<String> listaNombres;
    private final ArrayList<String> listaFechas;
    private final ArrayList<String> listaTareas;
    private final ArrayList<String> listaId;
    private final ArrayList<Boolean> listaEstado;

    //Constructor vacio de Tarea
    public Tarea() {
        //Inicicializacion de los atributos de la clase Tarea
        this.encargadoNombre = "";
        this.fechaVencimiento = "";
        this.tareaDescripcion = "";
        this.identificadorAuto = "";
        this.estadoTareas = 0;

        //Inicializacion de las listas 
        this.listaNombres = new ArrayList<>();
        this.listaFechas = new ArrayList<>();
        this.listaTareas = new ArrayList<>();
        this.listaId = new ArrayList<>();
        this.listaEstado = new ArrayList<>();
    }//Fin constructor base

    //Creacion de los get's y set's
    //Establecer el encargadoNombre
    public void setEncargadoNombre(String encargadoNombre) {
        //Verificacion de la longitud del nombre sea mayor a tres antes de retornarlo
        if (3 > encargadoNombre.length()) {
            System.out.print(" ¡La extensión del nombre del encargado debe ser superior a tres carácteres!\n");
            this.encargadoNombre = "";
        } else {
            this.encargadoNombre = encargadoNombre;
        }
    }

    public String getEncargadoNombre() {
        return encargadoNombre;
    }

    //Establecer la fechaVencimiento
    public void setFechaVencimiento(String fechaVencimiento) {
        //Si la fecha esta vacia se coloca --- para que se de a entender que no se tiene ese dato
        if ("".equals(fechaVencimiento)) {
            this.fechaVencimiento = "---";
        } else {
            this.fechaVencimiento = fechaVencimiento;
        }
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    //Establecer tareasGestion
    public void setTareasDescripcion(String tareaDescripcion) {
        //Se verifica que si se este ingresando una tarea
        if ("".equals(tareaDescripcion)) {
            System.out.print(" ¡Debe ingresar una tarea!\n");
            this.tareaDescripcion = "";

        } else {
            this.tareaDescripcion = tareaDescripcion;
        }
    }

    public String getTareasDescripcion() {
        return tareaDescripcion;
    }

    //Establecer la identificacionAuto
    public void setIdentificadorAuto(String identificadorAuto) {
        this.identificadorAuto = identificadorAuto;
    }

    public String getIdentificadorAuto() {
        return identificadorAuto;
    }

    public void setEstadoTareas(int estadoTareas) {
        this.estadoTareas = estadoTareas;
    }

    public int getEstadoTareas() {
        return estadoTareas;
    }

    //Creacion de los metodos necesarios
    //Metodo con el que se va a agregar una nueva tarea
    public void agregarTarea() {
        
        //Solo se agrega la tarea y los demas datos si el atributo encargadoNombre no esta vacío
        if (!"".equals(encargadoNombre)) {
            String idAuto;
            //Para dar el formato de id de 3 numéros si cantidad de elementos en la listaNombres es menor a 10 posee un formato u otro
            if (listaNombres.size() < 10) {
                idAuto = "T00" + (listaNombres.size() + 1);
            } else {
                idAuto = "T0" + (listaNombres.size() + 1);
            }
            //Todas las tareas agregadas por default tienen el estado pendiente
            boolean estado = false;

            //Se agregan los valores de los atributos a las listas correspondientes
            listaEstado.add(estado);
            listaNombres.add(encargadoNombre);
            listaId.add(idAuto);
            listaTareas.add(tareaDescripcion);
            listaFechas.add(fechaVencimiento);
        }
    }

    //Metodo para la impresion
    //Se coloca como private porque solo es necesario que sea accedido en la clase Tarea
    private void ImprimirTareas(int indice) {
        String estadoActual = "";
        
        //Si el elemento en el indice dado de la listaEstado es false la variable estadoActual tiene un estado u otro
        if (listaEstado.get(indice) == false) {
            estadoActual = "Pendiente";
        } else {
            estadoActual = "Completado";
        }
        //Impresión de todos los datos de la tarea
        System.out.println("-------------------------------------------------------------------------\n" + listaId.get(indice) + ". NOMBRE : " + listaNombres.get(indice) + "\t  FECHA : " + listaFechas.get(indice) + "\tESTADO: " + estadoActual + "\n      TAREA : " + listaTareas.get(indice));

    }

    //Método para la visualizacion de las tareas 
    //Se coloca publico para que sea accedido desde otras clases
    //Aunque hace lo mismo que imprimir esto representaba menos conflictos al llamar este metodo en la clase Proyecto Inicio
    public void VisualizarTareas() {
        //Se imprimen todas las tareas y sus datos correspondientes
        for (int i = 0; i < listaNombres.size(); i++) {
            ImprimirTareas(i);
        }
    }

    //Metodo para filtrar tareas por estado(pendiente/completada)
    public void filtrarPorEstado() {
        Boolean encontrado = false;
        //Se recorre usando listaNombres ya que solo se registran tareas con un encargado
        for (int i = 0; i < listaNombres.size(); i++) {

            //Si atributo estadoTareas retorna un 1 y el elemento en el indice de listaEstado es false se imprimiran las tareas pendientes
            if ((listaEstado.get(i) == false) && (estadoTareas == 1)) {
                System.out.println("\t\t\t\t  TAREAS");
                encontrado = true;
                ImprimirTareas(i);

            } else if ((listaEstado.get(i) == true) && (estadoTareas == 2)) {
                //Si atributo estadoTareas  retorna un 2 y el elemento en el indice de listaEstado es true se imprimiran las tareas completadas
                System.out.println("\t\t\t\t  TAREAS");
                encontrado = true;
                ImprimirTareas(i);
            }
        }
        //Si no se encuentra ninguna tarea que cumpla con ambas caracteristicas del IF o el ELSE IF se indica que no se encontro ninguna tarea con esas caracteristicas
        if (!encontrado) {
            System.out.println("\t¡La fecha indicada no existe en las tareas registradas!");
        }
    }

    //Metodo para filtrar tareas por fecha
    public void filtrarPorFecha() {
        Boolean encontrado = false;
        
        for (int i = 0; i < listaNombres.size(); i++) {
            //Si el atributo fechaVencimiento retorna un valor que coincide con el valor del elemento de el indice de la listaFechas 
            if (listaFechas.get(i).equals(fechaVencimiento)) {
                System.out.println("\t\t\t\t  TAREAS");
                //Se indica que la fecha fue encontrada
                encontrado = true;
                ImprimirTareas(i);
            }
        }
        //Si no se encuentra ninguna tarea que cumpla la caracteristica del IF se indica que no se encontro ninguna tarea con esas caracteristicas
        if (!encontrado) {
            System.out.println("\t¡La fecha indicada no existe en las tareas registradas!");
        }
    }

    //Metodo para marcar como completada una tarea
    public void marcarCompletada() {
        Boolean encontrado = false;
        for (int i = 0; i < listaNombres.size(); i++) {
            //Si el atributo identificador es igual al contenido en el elemento del indice de la listaId se marca como completada la tarea
            if (identificadorAuto.equals(listaId.get(i))) {
                encontrado = true;
                //Referencia 1. Se modifica unicamente listaEstado estableciendo por true el elemento de el indice proporcionado 
                listaEstado.set(i, true);
                //Se indica que se modifico el estado de la tarea indicada
                System.out.println("La tarea con el código identificador " + identificadorAuto + " ha sido marcada como completada");
                System.out.println("\t\t\tCompruebelo en 'Ver Tareas'");
            }
        }
        //Si no se encuentra ninguna tarea que cumpla la caracteristica del IF se indica que no se encontro ninguna tarea con esas caracteristicas
        if (!encontrado) {
            System.out.println("\t  ¡El código ingresado no coincide con ninguna tarea!");
        }
    }

    //Metodo para eiminar tareas
    public void eliminarTareas() {
        Boolean encontrado = false;
        for (int i = 0; i < listaNombres.size(); i++) {

            //Si el atributo identificador es igual al contenido en el elemento del indice de la listaId se elimina la tarea
            if (identificadorAuto.equals(listaId.get(i))) {
                encontrado = true;
                
                //Se elimina la la tarea y cada elemento relacionada a esta
                listaNombres.remove(i);
                listaFechas.remove(i);
                listaTareas.remove(i);
                listaId.remove(i);
                listaEstado.remove(i);
                
                 //Se indica que se elimino la tarea indicada
                System.out.println("\t¡La tarea con el código identificador " + identificadorAuto + " ha sido eliminada!");
                System.out.println("\t\t\tCompruebelo en 'Ver Tareas'");
            }
        }
         //Si no se encuentra ninguna tarea que cumpla la caracteristica del IF se indica que no se encontro ninguna tarea con esas caracteristicas
        if (!encontrado) {
            System.out.println("\t  ¡El código ingresado no coincide con ninguna tarea!");
        }
    }

}
