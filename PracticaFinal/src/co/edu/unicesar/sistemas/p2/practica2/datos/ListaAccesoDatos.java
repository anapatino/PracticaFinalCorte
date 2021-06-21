
package co.edu.unicesar.sistemas.p2.practica2.datos;

import co.edu.unicesar.sistemas.p2.practica2.dominio.Publicacion;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import java.util.ArrayList;
import java.util.List;

public class ListaAccesoDatos implements IAccesoDatos {
private static final ListaAccesoDatos INSTANCIA=new ListaAccesoDatos();
private List<Publicacion> Datos;

    private ListaAccesoDatos() {
        this.Datos=new ArrayList();
    }
    
    
    public static ListaAccesoDatos getInstancia(){
      return INSTANCIA;
    }
          
    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        if(p==null){
            throw new ExcepcionAccesoDatos("La publicacion es invalida");
        }else if(p.getIsbn()==null){
            throw new ExcepcionAccesoDatos("El ISBN  es invalida");
        }      
        Datos.add(p);
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionAccesoDatos {
        if(Datos.size()>0){
            return Datos; 
        }else{
             throw new ExcepcionAccesoDatos("No hay publicaciones registradas");
        }
       
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        if(this.Datos.size()==0)
           throw new ExcepcionAccesoDatos("No hay publicaciones registradas"); 
        if(p.getIsbn()==null)
            throw new ExcepcionAccesoDatos("Parametro no permitido para la busqueda"); 
        if(p.getIsbn()==null)
            throw new ExcepcionAccesoDatos("ISB de busqueda,no registrado"); 
        
        Publicacion buscada=null;
        
        for(Publicacion pub:this.Datos){
           
           if(pub.getIsbn().equals(p.getIsbn())){
              buscada=pub;
              break;
            }
        
        }
     return buscada;    
    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionAccesoDatos {
        Publicacion buscar=buscarPublicacion(p);
     if(buscar==null){
         throw new ExcepcionAccesoDatos("No existe publicacion"); 
     }else{
         Datos.remove(buscar);
     }
      return buscar;
      
    }
    
}
