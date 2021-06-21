
package co.edu.unicesar.sistemas.p2.practica2.datos;

import co.edu.unicesar.sistemas.p2.practica2.dominio.*;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ArchivoTexto implements IAccesoDatos {
    
    
   private File archivo;
   private FileWriter aEscritura;
   private Scanner aLectura;
   
   
   public ArchivoTexto(String name) {
        this.archivo = new File(name);
    }

    public ArchivoTexto() {
        this("Publicaciones.dat");
    }
    
    
    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
     PrintWriter pw=null;
        
        try{
            this.aEscritura=new FileWriter(this.archivo,true);
            pw=new PrintWriter(this.aEscritura);
            pw.println(p.getEstructuraTexto());
            
        } catch(IOException ioe){
            throw new ExcepcionAccesoDatos("No se pudo escribrir en el archico");
        }
          finally{
              if(pw!=null){
                  pw.close();
              }
              if(this.aEscritura!=null){
                  try {
                      this.aEscritura.close();
                  } catch (IOException ex) {
                      
                  }
              }
          }   
    }
    
    private Publicacion crearPublicacion(String linea){
     String datos[]=linea.split(";");
     if(datos[0].equalsIgnoreCase("Libro")){
         Libro libro= new Libro();
         libro.setIsbn(datos[1]);
         libro.setTitulo(datos[2]);
         libro.setAutor(datos[3]);
         libro.setAnio(Integer.valueOf(datos[4]));
         libro.setCosto(Double.valueOf(datos[5]));
         libro.setnPaginas(Integer.valueOf(datos[6]));
         libro.setEdicion(Integer.valueOf(datos[7]));
         return libro;
  
     }else{
         AudioLibro audiolibro= new AudioLibro();
         audiolibro.setIsbn(datos[1]);
         audiolibro.setTitulo(datos[2]);
         audiolibro.setAutor(datos[3]);
         audiolibro.setAnio(Integer.valueOf(datos[4]));
         audiolibro.setCosto(Double.valueOf(datos[5]));
         audiolibro.setDuracion(Double.valueOf(datos[6]));
         audiolibro.setPeso(Double.valueOf(datos[7]));
         audiolibro.setFormato(datos[8]);
         return audiolibro;
     }
 
 }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAccesoDatos {
       List<Publicacion> lista=new ArrayList();
         try{
              this.aLectura=new Scanner(this.archivo);
              while(this.aLectura.hasNext()){
                  String linea=this.aLectura.nextLine();
                  Publicacion pub=this.crearPublicacion(linea);
                  lista.add(pub);
              }
              return lista;
         }catch(IOException ioe){
             throw new ExcepcionAccesoDatos("No se pudo leer el archico");
         }
         finally{
             if(this.aLectura!=null){
                 this.aLectura.close();
             }
         }
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        List<Publicacion> lista=leerPublicaciones();
      return encontrarPublicacionEn(lista,p);
    }
    
    private Publicacion encontrarPublicacionEn(List<Publicacion> lista,Publicacion p) throws ExcepcionAccesoDatos{
     if(lista.size()==0)
           throw new ExcepcionAccesoDatos("No hay publicaciones registradas"); 
        if(p.getIsbn()==null)
            throw new ExcepcionAccesoDatos("Parametro no permitido para la busqueda"); 
        if(p.getIsbn()==null)
            throw new ExcepcionAccesoDatos("ISB de busqueda,no registrado"); 
        
        Publicacion buscada=null;
        
        for(Publicacion pub:lista){
           
           if(pub.getIsbn().equals(p.getIsbn())){
              buscada=pub;
              break;
            }
        
        }
     return buscada;      
    }
    @Override
    public Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        List<Publicacion> lista=leerPublicaciones();
       Publicacion buscar=encontrarPublicacionEn(lista,p);
     if(buscar==null){
         throw new ExcepcionAccesoDatos("No existe publicacion"); 
     }else{
            this.archivo.delete();
           lista.remove(buscar);
          
            lista.forEach((p1) -> {
             try {
                 this.insertarPublicacion(p1);
             } catch (ExcepcionAccesoDatos ex) {
                 
             }
             }); 
     }
      return buscar;
      
    }
    
}
