package tk.thedaviddelta.multimedia.modelo;

public class Disco extends Multimedia {
    private Genero genero;

    public Disco(String titulo, String autor, String formato, int duracion, String genero) {
        super(titulo, autor, formato, duracion);
        try{
            this.genero = Genero.valueOf(genero.toUpperCase());
        } catch (IllegalArgumentException e){
            this.genero = Genero.OTRO;
        }
    }

    public Genero getGenero() {
        return genero;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format("\nGénero: %s",this.genero);
    }
    
}
