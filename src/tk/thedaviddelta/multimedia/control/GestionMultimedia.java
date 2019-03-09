package tk.thedaviddelta.multimedia.control;

import tk.thedaviddelta.multimedia.modelo.*;

public class GestionMultimedia {
    static final int MAX = 10;
    static ListaMultimedia lista = new ListaMultimedia(MAX);
    
    public static boolean llena(){
        if (lista.size() >= MAX) 
            return true;
        return false;
    }
    
    public static void addPelicula(String titulo, String autor, String formato, int duracion, String actor, String actriz){
        lista.add(new Pelicula (titulo, autor, formato, duracion, actor, actriz));
    }
    
    public static void addDisco(String titulo, String autor, String formato, int duracion, String genero){
        lista.add(new Disco (titulo, autor, formato, duracion, genero));
    }
    
    public static int buscar(String titulo, String autor){
        int pos = lista.indexOf(new Pelicula(titulo, autor, "dvd", 120, "Will Smith", ""));
        if (pos != -1) 
            return pos;
        return lista.indexOf(new Disco(titulo, autor, "mp3", 120, "rock"));
    }
    
    public static String info(){
        return lista.toString();
    }
    
    public static String info(int pos){
        return lista.get(pos).toString();
    }
}
