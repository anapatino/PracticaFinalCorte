
package co.edu.unicesar.sistemas.p2.practica2.dominio;

import java.io.Serializable;


public abstract class Publicacion implements Serializable {
    private String isbn,titulo,autor;
    private int anio;
    private double costo;

    public Publicacion() {
    }

    public Publicacion(String isbn) {
        this.isbn = isbn;
    }

    public Publicacion(String isbn, String titulo, String autor, int anio, double costo) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.costo = costo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
   public abstract String getInfo() ;

    @Override
    public String toString() {
        return isbn + "," + titulo + "," + autor + "," + anio + "," + costo+","+this.getInfo() ;
    }
    
    public abstract String getTipo();

    public String getEstructuraTexto(){
        return getTipo()+";"+isbn + ";" + titulo + ";" + autor + ";" + anio + ";" + costo+ ";" ;
    }
    
    
   
    
}
