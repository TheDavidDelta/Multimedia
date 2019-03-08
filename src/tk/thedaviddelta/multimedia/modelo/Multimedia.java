package tk.thedaviddelta.multimedia.modelo;

import java.util.Objects;

public class Multimedia {
    protected String titulo;
    protected String autor;
    protected Formato formato;
    protected int duracion;

    public Multimedia(String titulo, String autor, String formato, int duracion) throws IllegalArgumentException {
        this.titulo = titulo;
        this.autor = autor;
        this.formato = Formato.valueOf(formato.toUpperCase());
        this.duracion = duracion;
    }
    
    public Multimedia(String titulo, String autor, Formato formato, int duracion) {
        this.titulo = titulo;
        this.autor = autor;
        this.formato = formato;
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return String.format("Título: %s\nAutor: %s\nFormato: %s\nDuración: %d",titulo,autor,formato,duracion);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null || getClass() != obj.getClass()) 
            return false;
        final Multimedia other = (Multimedia) obj;
        if (!Objects.equals(this.titulo, other.titulo) || !Objects.equals(this.autor, other.autor)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.titulo);
        hash = 59 * hash + Objects.hashCode(this.autor);
        return hash;
    }
    
}
