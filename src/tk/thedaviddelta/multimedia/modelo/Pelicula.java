package tk.thedaviddelta.multimedia.modelo;

public class Pelicula extends Multimedia {
    private String actor;
    private String actriz;

    public Pelicula(String titulo, String autor, String formato, int duracion, String actor, String actriz) throws IllegalArgumentException {
        super(titulo, autor, formato, duracion);
        if ((actor.equals("") && actriz.equals(""))) 
            throw new IllegalArgumentException("Se debe indicar un actor o actriz principal");
        this.actor = actor;
        this.actriz = actriz;
    }

    public String getActor() {
        return actor;
    }

    public String getActriz() {
        return actriz;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nActor principal: %s\nActriz principal: %s",this.actor,this.actriz);
    }
    
}
