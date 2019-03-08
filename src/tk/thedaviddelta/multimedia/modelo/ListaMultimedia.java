package tk.thedaviddelta.multimedia.modelo;

public class ListaMultimedia {
    private Multimedia[] multimedias;
    private int num;

    public ListaMultimedia(int n) {
        this.multimedias = new Multimedia[n];
        this.num = 0;
    }
    
    public int size() {
        return this.num;
    }
    
    public boolean add (Multimedia m){
        if (multimedias.length <= num) 
            return false;
        multimedias[num] = m;
        num++;
        return true;
    }
    
    public Multimedia get(int pos){
        return multimedias[pos];
    }
    
    public int indexOf(Multimedia m){
        for (int i = 0; i < this.num; i++) {
            if (this.multimedias[i].equals(m)) 
                return i;
        }
        return -1;
    }
    
    @Override
    public String toString(){
        String s = "";
        for (int i = 0; i < this.num; i++) {
            s += multimedias[i];
            if (i < num-1) 
                s += "\n-------------------\n";
        }
        return s;
    }
}
