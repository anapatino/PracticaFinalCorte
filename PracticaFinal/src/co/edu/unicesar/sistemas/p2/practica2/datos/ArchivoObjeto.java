
package co.edu.unicesar.sistemas.p2.practica2.datos;

import co.edu.unicesar.sistemas.p2.practica2.dominio.Publicacion;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoObjeto implements IAccesoDatos {

    private File archivo;
    private FileInputStream aLectura;
    private FileOutputStream aEscritura;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ArchivoObjeto(String name) {
        this.archivo = new File(name);
    }
    

 
    private void guardar(List<Publicacion> lista) throws IOException {
        ObjectOutputStream oos = null;
        try {
            this.aEscritura = new FileOutputStream(this.archivo, false);
            oos = new ObjectOutputStream(this.aEscritura);
            oos.writeObject(lista);
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if (oos != null) {
                oos.close();
            }

            if (this.aEscritura != null) {
                this.aEscritura.close();
            }
        }
    }
    
    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        try {
           
            List<Publicacion> lis=leerPublicaciones();
            lis.add(p);
            guardar(lis);
        
        } catch (IOException ex) {
            throw new ExcepcionAccesoDatos("No se registro");
        }
      
   
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAccesoDatos {
       List<Publicacion> lista = null;
        ObjectInputStream ois = null;

        if (this.archivo.exists()) {
            try {
                this.aLectura = new FileInputStream(this.archivo);
                ois = new ObjectInputStream(this.aLectura);
                lista = (List<Publicacion>) ois.readObject();
                return lista;
            } 
             catch (IOException | ClassNotFoundException ex) {
                 
                throw new ExcepcionAccesoDatos("No se puede leer el archivo");
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                       
                    }
                }
                if (this.aLectura != null) {
                    try {
                        this.aLectura.close();
                    } catch (IOException ex) {
                        
                    }
                }
            }
        }
        else{
            lista = new ArrayList();
            return lista;
        }
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
      List<Publicacion> lista=leerPublicaciones();
      return encontrarPublicacionEn(lista,p);
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
           try {
               guardar(lista);
           } catch (IOException ex) {
              throw new ExcepcionAccesoDatos("No se pudo guardar"); 
           }
     }
      return buscar;
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
    
    
    
    
}
