
package co.edu.unicesar.sistemas.p2.practica2.vista;

import co.edu.unicesar.sistemas.p2.practica2.datos.*;
import co.edu.unicesar.sistemas.p2.practica2.dominio.*;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import co.edu.unicesar.sistemas.p2.practica2.negocio.*;
import java.util.List;
import java.util.Scanner;


public class VistaConsola {
    
    private String[]titulos={"1.Insertar ","2.Mostrar ","3.Buscar ","4.Eliminar","0.Salir"};
    private int opcion;
    private Scanner lector;
    private RegistroPublicacion registro;
    private final IAccesoDatos datos;
    
    public VistaConsola() {
        this.lector = new Scanner(System.in);
        this.registro=new RegistroPublicacion();
        this.datos=new ArchivoObjeto("PublicObjeto.obj");
        //this.datos=new ArchivoTexto();
    }
    
    public void ejecutarMenu() throws ExcepcionAccesoDatos{
        do{
            this.imprimirMenu();
            this.leerOpcion();
            this.evaluarOpcion();
        }while(this.opcion!=0);
    }
    
    public void imprimirMenu(){
        System.out.println("______________________________________");
        System.out.println("");
        System.out.println("        MENU DE APLICACIONES");  
        for(String t: this.titulos){
            System.out.println(t);
        }
    }
    
    public void leerOpcion(){
        boolean excepcion=true;
        do{
            
       
        try{
            System.out.println("");
            System.out.print("      Seleccione su opcion->");
            this.opcion=this.lector.nextInt();
            excepcion=false;
        }catch(java.util.InputMismatchException ime){
            System.out.println("La opcion debe ser valor entero,ingrese nuevamente");
            excepcion=true;
            this.lector.nextLine();
        }
         }while(excepcion);
    }
    
    
    public void evaluarOpcion() throws ExcepcionAccesoDatos{
        switch(this.opcion){
            case 1: this.vistaInsertar();
                    break;
            case 2: this.vistaConsultar();
                   break;
            case 3: this.vistaBuscar();
                   break;
            case 4: this.vistaEliminar();
                    break;
            case 0: System.out.println("La aplicacion ha terminado");
                    break;
            default:System.out.println("Opcion no valida");
        }
    }
    
    public void vistaInsertar() throws ExcepcionAccesoDatos{
       System.out.println("______________________________________");
       System.out.println(this.titulos[this.opcion-1]);
      
       Publicacion andrea =new Libro(345, 17,"CDMX", "AFTER", "ANNA FLORIDA", 2020, 15000);
       Publicacion Federico=new Libro(654, 6,"EEUU", "ANTES DE TI", "HARDIN SCOTT", 1987, 24000);
       Publicacion jose =new AudioLibro(315, 154, "MP3","CDMX", "LOS CAMINOS DE LA VIDA", "JOSE ARAGON", 2010,16000);
       Publicacion luna=new AudioLibro(400, 72, "MP4","NY", "ORGULLO Y PREJUICIO", "ELIZABETH BENET", 2001,25000);
        
        try{
             datos.insertarPublicacion(andrea);
             datos.insertarPublicacion(Federico);
             datos.insertarPublicacion(jose);
             datos.insertarPublicacion(luna);
             System.out.println("");
             System.out.println("Se han registrados los datos correctamente");
             
        }catch(ExcepcionAccesoDatos ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("");
       
    }
    
     public void vistaConsultar() throws ExcepcionAccesoDatos{
       System.out.println("______________________________________");
        System.out.println(this.titulos[this.opcion-1]);
      
        
        try{
           
             List<Publicacion>consulta=datos.leerPublicaciones();
             for(Publicacion pub:consulta){
                 System.out.println(pub); 
               }
            
        }catch(ExcepcionAccesoDatos ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("");
       
    }
     
      public void vistaBuscar(){
        System.out.println("______________________________________");
        System.out.println(this.titulos[this.opcion-1]);
        
         try{
             Publicacion pub=datos.buscarPublicacion(new Libro("EEUU"));
             if(pub==null){
                 System.out.println("La publicacion no esta registrada");
                
             }else{
                 System.out.println(pub);
               
             }
             
        }catch(ExcepcionAccesoDatos ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("");
        
    }
      
       public void vistaEliminar(){
        System.out.println("______________________________________");
        System.out.println(this.titulos[this.opcion-1]);
        try{
            Publicacion pub=datos.eliminarPublicacion(new Libro("CDMX"));
       
             if(pub==null){
                 System.out.println("La publicacion no esta registrada");
           
             }else{
                 System.out.println(pub);
               
             }
             
        }catch(ExcepcionAccesoDatos ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("");
        
    }
}
