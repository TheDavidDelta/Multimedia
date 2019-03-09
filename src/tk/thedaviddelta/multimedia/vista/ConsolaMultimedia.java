package tk.thedaviddelta.multimedia.vista;

import tk.thedaviddelta.multimedia.control.GestionMultimedia;
import java.util.Scanner;

public class ConsolaMultimedia {
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);
        welcome();
        int op;
        do {
            System.out.println("1.- Añadir película\n2.- Añadir disco\n3.- Buscar multimedia\n4.- Listar\n5.- Salir");
            op = Integer.parseInt(tec.nextLine());
            while (op < 1 || op > 5) {
                System.out.println("Opción no válida, inténtelo de nuevo");
                op = Integer.parseInt(tec.nextLine());
            }
            menu(op);
        } while (op != 5);
    }
    
    private static void welcome(){
        try{
            System.out.print("*");
            Thread.sleep(500);
            System.out.print("*");
            Thread.sleep(500);
            System.out.print("* ");
            Thread.sleep(500);
            typer("Bienvenido");
            System.out.print(" *");
            Thread.sleep(500);
            System.out.print("*");
            Thread.sleep(500);
            System.out.print("*\n");
            Thread.sleep(500);
        }catch (InterruptedException e){
            System.err.println("Error inesperado");
        }
    }
    
    private static void typer(String s){
        try{
            for (int i = 0; i < s.length(); i++) {
                System.out.print(s.charAt(i));
                Thread.sleep(250);
            }
        }catch (InterruptedException e){
            System.err.println("Error inesperado");
        }
    }
    
    private static void menu(int op){
        switch (op) {
            case 1: // película
                if (! GestionMultimedia.llena()) {
                    System.out.println("Introduce los datos de la película:");
                    addMultimedia('p');
                } else
                    System.out.println("No hay hueco");
                break;
            case 2: // disco
                if (! GestionMultimedia.llena()) {
                    System.out.println("Introduce los datos del disco:");
                    addMultimedia('d');
                } else
                    System.out.println("No hay hueco");
                break;
            case 3: // buscar
                buscar();
                break;
            case 4: // listar
                if (GestionMultimedia.info().equals("")) 
                    System.out.println("La lista está vacía");
                else
                    System.out.println(GestionMultimedia.info());
                break;
            case 5: // salir
                System.out.println("Se saldrá del programa");
                break;
        }
        System.out.println();
    }
    
    private static void addMultimedia(char tipo) {
        Scanner tec = new Scanner(System.in);
        System.out.print("Título: ");
        String titulo = tec.nextLine();
        System.out.print("Autor: ");
        String autor = tec.nextLine();
        System.out.print("Formato: ");
        String formato = tec.nextLine();
        System.out.print("Duración en minutos: ");
        try{
            int duracion = Integer.parseInt(tec.nextLine());
            if (tipo == 'p') {
                System.out.println("Actor y actriz principales (min. 1 de ellos): ");
                String actor = tec.nextLine();
                String actriz = tec.nextLine();
                GestionMultimedia.addPelicula(titulo, autor, formato, duracion, actor, actriz);
            }
            if (tipo == 'd') {
                System.out.print("Género: ");
                GestionMultimedia.addDisco(titulo, autor, formato, duracion, tec.nextLine());
            }
            System.out.println("Elemento añadido correctamente");
        } catch (NumberFormatException e) {
            System.err.println("Se ha producido un error al añadir el elemento:\nLa duración debe ser un entero");
        } catch (IllegalArgumentException e) {
            System.err.println("Se ha producido un error al añadir el elemento:");
            System.err.println(e.getMessage());
        }
    }
    
    private static void buscar(){
        Scanner tec = new Scanner(System.in);
        System.out.print("Introduce el título del elemento a buscar: ");
        String titulo = tec.nextLine();
        System.out.print("Introduce el autor del elemento a buscar: ");
        String autor = tec.nextLine();
        int pos = GestionMultimedia.buscar(titulo, autor);
        if (pos == -1) 
            System.out.println("El elemento no se encuentra en la lista");
        else 
            System.out.printf("Posición: %d\n%s\n", pos, GestionMultimedia.info(pos));
    }
}
