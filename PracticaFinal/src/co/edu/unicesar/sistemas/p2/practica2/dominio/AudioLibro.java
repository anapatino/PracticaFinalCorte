
package co.edu.unicesar.sistemas.p2.practica2.dominio;

import java.io.Serializable;


public class AudioLibro extends Publicacion implements Serializable {
    private double duracion,peso;
    private String formato;

    public AudioLibro() {
    }

    public AudioLibro(String isbn) {
        super(isbn);
    }

    public AudioLibro(double duracion, double peso, String formato, String isbn, String titulo, String autor, int anio, double costo) {
        super(isbn, titulo, autor, anio, costo);
        this.duracion = duracion;
        this.peso = peso;
        this.formato = formato;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public String getInfo() {
       return this.duracion +","+this.peso+","+this.formato;
    }
    
     public String getTipo(){
       return "AudioLibro";
   } 
      @Override
    public String getEstructuraTexto(){
        return super.getEstructuraTexto()+ this.duracion +";"+this.peso+";"+this.formato;
    }
    

}
