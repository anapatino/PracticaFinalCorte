
package co.edu.unicesar.sistemas.p2.practica2.dominio;

import java.io.Serializable;

public class Libro extends Publicacion implements Serializable{
    private int nPaginas,edicion;

    public Libro() {
    }

    public Libro(String isbn) {
        super(isbn);
    }

    public Libro(int nPaginas, int edicion, String isbn, String titulo, String autor, int anio, double costo) {
        super(isbn, titulo, autor, anio, costo);
        this.nPaginas = nPaginas;
        this.edicion = edicion;
    }

    public int getnPaginas() {
        return nPaginas;
    }

    public void setnPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
    }

    public int getEdicion() {
        return edicion;
    }

    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    @Override
    public String getInfo() {
        return this.nPaginas+" ; "+this.edicion;
    }
    
   public String getTipo(){
       return "Libro";
   }   
    @Override
    public String getEstructuraTexto(){
        return super.getEstructuraTexto()+this.nPaginas+";"+this.edicion;
    }
}
