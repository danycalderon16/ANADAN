/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

/**
 *
 * @author Francisco Daniel Hernández Rodríguez
 * Empresa: ServIT México
 * Descripción: Implementación de una tabla de símbolos para compilador
 *              con Java Cup y JFlex.
 */
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

class Nodo
{
    String nombre;//Nombre de la estructura (for, while, if)
    int repeticiones;//No. de Variables asociadas
    Nodo siguiente; //Instancia a una clase nodo              
    
    Nodo (int edad, String nombre)
    {        
        //Skype: fdaniel@servitmexico.com                    
        this(edad, null, null);       
    }
    
    Nodo (int repeticiones, String nombre, Nodo siguiente)
    {
        this.repeticiones = repeticiones;
        this.nombre = nombre;        
        this.siguiente = siguiente;
    }
}

class Simbolo
{  
    String nombre;
    String tipo;
    Object valor;
    String clase;
    
    public Simbolo(String nombre, String tipo ,Object valor, String clase)
    {
        this.nombre = nombre;       
        this.tipo = tipo;
        this.valor = valor;
        this.clase = clase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Simbolo{" + "nombre=" + nombre + ", tipo=" + tipo + ", valor=" + valor + ", clase=" + clase + '}';
    }
    
    
}

public class TablaSimbolos {
    static Map<String, Simbolo> tablaSimbolos;
    public static Stack<String> lista;
    static ArrayList<Nodo> repeticiones; 
    public static String errores;
    
    public static Logger log = Logger.getLogger(TablaSimbolos.class.getName());            
    
    public TablaSimbolos()
    {        
//        for (Simbolo s : tablaSimbolos.values()) {
//             tablaSimbolos.remove(s.nombre);
//        }
        tablaSimbolos = new HashMap<String, Simbolo>();                                   
        lista = new Stack<String>();
        errores = "";
    }
    
    static public String verificarTipo(String nombre){
        Simbolo s = tablaSimbolos.get(nombre);
        return s.tipo;
    }
    
    static public String eliminar(String nombre)
    {             
        
        System.out.println("Eliminando variable: " + nombre);
        try
        {
            tablaSimbolos.remove(nombre);
            return nombre;
        }
        catch(Exception e)
        {
            System.out.println("Error al eliminar una variable de la tabla de simbolos");
            return nombre;
        }                
    }
            
    static public Simbolo crear(String nombre, String tipo, String clase)
    {         
        Simbolo simbolo = buscar(nombre);                            
        if(simbolo == null) // La variable no existe
        {
            simbolo = new Simbolo(nombre, tipo, null, clase);
          
            System.out.println("Agregando a tabla de simbolos con nombre: " + nombre);
            tablaSimbolos.put(nombre, simbolo);            
            //System.out.println("Variable creada exitosamente!!!");
              
            imprimir();                
            System.out.println(" ");
            return simbolo;
        }
        else
        {
            log.log(Level.SEVERE, "Redefinición de la variable: " + nombre);
            return null; // La variable ya existía                
        }
    }
        
    static public Simbolo insertar(String nombre, Object valor){
//        System.out.println("\nIngreso a insertar valor a variable.");
        Simbolo simbolo = buscar(nombre);
        if(simbolo != null) //La variable existe
        {
            //Actualizar el valor
            simbolo.valor = valor;
            tablaSimbolos.remove(nombre);//Elimino para actualizar
            tablaSimbolos.put(nombre, simbolo);
            
            System.out.println("Variable '"+nombre+"' actualizada" );
//            imprimir();
//            System.out.println("Saliendo de insertar de TablaSimbolos\n");
            return simbolo;
        }
        else
            return null;
    }
    
    static public Simbolo buscar(String nombre)
    {
        return (Simbolo)tablaSimbolos.get(nombre);
    }
    
    static public void imprimir()
    {
//        System.out.println("\nIngresando a imprimir de TablaSimbolos");
//        System.out.println("    Valores de la tabla de simbolos:");
        for (Simbolo s : tablaSimbolos.values())
            System.out.println(String.format("      "
                    + "Nombre: %s, valor: %s, tipo: %s",s.nombre, s.valor, s.tipo));        
//        System.out.println("Saliendo de imprimir en TablaSimbolos\n ");        
    }
    
    static public void mostrar(){
        try {
            for (Simbolo s  : tablaSimbolos.values()) {
                System.out.println("Tipo: "+s.tipo+" | Nombre: "+s.nombre+" | Valor: "+s.valor.toString());
            }            
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public static Collection<Simbolo> enviarLista(){
        return tablaSimbolos.values();
    }
    
    public static void logErrores(String error){
         errores += error;
    }      
    public static String getLogErrores(){
        return errores;
    }
    public static Logger getLog(){
        return log;
    }
    public static int limpiar(){
        if(tablaSimbolos != null)            
            tablaSimbolos.clear();
                  
        return 0;
    }
}
