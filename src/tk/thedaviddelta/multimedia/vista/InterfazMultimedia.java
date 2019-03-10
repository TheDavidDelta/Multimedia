package tk.thedaviddelta.multimedia.vista;

import tk.thedaviddelta.multimedia.control.GestionMultimedia;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;

public class InterfazMultimedia extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        mainScene(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private static void mainScene(Stage primaryStage){
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        //vb.setFillWidth(true);
        //vb.setPadding(new Insets(25, 25, 25, 25));
        vb.setSpacing(10);
        
        Scene scene = new Scene(vb, 640, 480);
        primaryStage.setTitle("Menú principal");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(InterfazMultimedia.class.getResourceAsStream("IconoMultimedia.png")));
        primaryStage.show();
        
        Text welcome = new Text("Bienvenido");
        welcome.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 46));
        welcome.setTextAlignment(TextAlignment.CENTER);
        vb.getChildren().add(welcome);
        
        Region r = new Region();
        vb.getChildren().add(r);

        Button[] op = new Button[5];
        
        op[0] = new Button("Añadir película");
        op[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addPelicula(primaryStage);
            }
        });
        
        op[1] = new Button("Añadir disco");
        op[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addDisco(primaryStage);
            }
        });
        
        op[2] = new Button("Buscar elemento");
        op[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buscar(primaryStage);
            }
        });
        
        op[3] = new Button("Listar");
        op[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listar(primaryStage);
            }
        });
        
        op[4] = new Button("Salir");
        op[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        /*for (int i = 0; i < op.length; i++) {
            op[i].setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 26));
            grid.add(op[i], 0, i+1);
        }*/
        
        for (Button option : op) {
            option.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 24));
            vb.getChildren().add(option);
        }
    }
    
    private static void addPelicula(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(25);
        
        Scene scene = new Scene(grid, 640, 480);
        primaryStage.setTitle("Añadiendo película");
        primaryStage.setScene(scene);
        
        Text txt = new Text("Introduce los datos de la película:");
        txt.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        //txt.setTextAlignment(TextAlignment.CENTER);
        grid.add(txt, 0, 0, 2, 1);
        
        Label[] labs = new Label[6];
        TextField[] texts = new TextField[6];
        
        labs[0] = new Label("\tTítulo");
        texts[0] = new TextField();
        
        labs[1] = new Label("Autor");
        texts[1] = new TextField();
        
        labs[2] = new Label("Formato");
        texts[2] = new TextField();
        
        labs[3] = new Label("Duración");
        texts[3] = new TextField();
        
        labs[4] = new Label("Actor");
        texts[4] = new TextField();
        
        labs[5] = new Label("Actriz");
        texts[5] = new TextField();
        
        for (int i = 0; i < labs.length; i++) {
            labs[i].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 22));
            labs[i].setAlignment(Pos.CENTER_RIGHT);
            labs[i].setMaxWidth(250);
            grid.add(labs[i], 0, i+2);
            texts[i].setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20));
            texts[i].setAlignment(Pos.CENTER);
            texts[i].setMaxWidth(250);
            grid.add(texts[i], 1, i+2);
        }
        
        Text target = new Text();
        target.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 18));
        //target.setTextAlignment(TextAlignment.RIGHT);
        grid.add(target, 1, 8);
        
        HBox hbBtn = new HBox();
        hbBtn.setSpacing(30);
        Button back = new Button("Volver");
        back.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(back);
        Button enter = new Button("Crear");
        enter.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(enter);
        grid.add(hbBtn, 1, 9);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainScene(primaryStage);
            }
        });
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    if (GestionMultimedia.llena()) {
                        target.setFill(Color.FIREBRICK);
                        target.setText("La lista está llena");
                    }else{
                        GestionMultimedia.addPelicula(texts[0].getText(), texts[1].getText(), texts[2].getText(), Integer.parseInt(texts[3].getText()), texts[4].getText(), texts[5].getText());
                        target.setFill(Color.GREEN);
                        target.setText("Película añadida correctamente");
                    }
                } catch (NumberFormatException e) {
                    target.setFill(Color.FIREBRICK);
                    target.setText("La duración debe ser un entero");
                } catch (IllegalArgumentException e) {
                    target.setFill(Color.FIREBRICK);
                    if (e.getMessage().contains("No enum constant")) 
                        target.setText(String.format("El formato '%s' no existe", texts[2].getText().toUpperCase()));
                    else if (e.getMessage().equals("Se debe indicar un actor o actriz principal"))
                        target.setText("Debe haber un actor o actriz principal");
                    else
                        target.setText(e.getMessage());
                }
            }
        });
    }
    
    private static void addDisco(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(25);
        
        Scene scene = new Scene(grid, 640, 480);
        primaryStage.setTitle("Añadiendo disco");
        primaryStage.setScene(scene);
        
        Text txt = new Text("Introduce los datos del disco:");
        txt.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        //txt.setTextAlignment(TextAlignment.CENTER);
        grid.add(txt, 0, 0, 2, 1);
        
        Label[] labs = new Label[5];
        TextField[] texts = new TextField[5];
        
        labs[0] = new Label("\tTítulo");
        texts[0] = new TextField();
        
        labs[1] = new Label("Autor");
        texts[1] = new TextField();
        
        labs[2] = new Label("Formato");
        texts[2] = new TextField();
        
        labs[3] = new Label("Duración");
        texts[3] = new TextField();
        
        labs[4] = new Label("Género");
        texts[4] = new TextField();
        
        for (int i = 0; i < labs.length; i++) {
            labs[i].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 22));
            labs[i].setAlignment(Pos.CENTER_RIGHT);
            labs[i].setMaxWidth(250);
            grid.add(labs[i], 0, i+2);
            texts[i].setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20));
            texts[i].setAlignment(Pos.CENTER);
            texts[i].setMaxWidth(250);
            grid.add(texts[i], 1, i+2);
        }
        
        Text target = new Text();
        target.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 18));
        //target.setTextAlignment(TextAlignment.RIGHT);
        grid.add(target, 1, 7);
        
        HBox hbBtn = new HBox();
        hbBtn.setSpacing(30);
        Button back = new Button("Volver");
        back.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(back);
        Button enter = new Button("Crear");
        enter.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(enter);
        grid.add(hbBtn, 1, 8);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainScene(primaryStage);
            }
        });
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    if (GestionMultimedia.llena()) {
                        target.setFill(Color.FIREBRICK);
                        target.setText("La lista está llena");
                    }else{
                        GestionMultimedia.addDisco(texts[0].getText(), texts[1].getText(), texts[2].getText(), Integer.parseInt(texts[3].getText()), texts[4].getText());
                        target.setFill(Color.GREEN);
                        target.setText("Disco añadido correctamente");
                    }
                } catch (NumberFormatException e) {
                    target.setFill(Color.FIREBRICK);
                    target.setText("La duración debe ser un entero");
                } catch (IllegalArgumentException e) {
                    target.setFill(Color.FIREBRICK);
                    if (e.getMessage().contains("No enum constant")) 
                        target.setText(String.format("El formato '%s' no existe", texts[2].getText().toUpperCase()));
                    else
                        target.setText(e.getMessage());
                }
            }
        });
    }
    
    private static void buscar(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(25);
        
        Scene scene = new Scene(grid, 640, 480);
        primaryStage.setTitle("Buscando elemento");
        primaryStage.setScene(scene);
        
        Text txt = new Text("Introduce los datos del elemento a buscar:");
        txt.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 26));
        txt.setTextAlignment(TextAlignment.CENTER);
        grid.add(txt, 0, 0, 2, 1);
        
        Label[] labs = new Label[2];
        TextField[] texts = new TextField[2];
        
        labs[0] = new Label("\tTítulo");
        texts[0] = new TextField();
        
        labs[1] = new Label("Autor");
        texts[1] = new TextField();
        
        for (int i = 0; i < labs.length; i++) {
            labs[i].setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 22));
            labs[i].setAlignment(Pos.CENTER_RIGHT);
            labs[i].setMaxWidth(250);
            grid.add(labs[i], 0, i+2);
            texts[i].setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20));
            texts[i].setAlignment(Pos.CENTER);
            texts[i].setMaxWidth(250);
            grid.add(texts[i], 1, i+2);
        }
        
        Text target1 = new Text();
        target1.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20));
        //target1.setTextAlignment(TextAlignment.RIGHT);
        grid.add(target1, 1, 4);
        
        Text target2 = new Text("\n\n\n\n\n");
        target2.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 18));
        //target2.setTextAlignment(TextAlignment.RIGHT);
        ScrollPane sp = new ScrollPane(target2);
        sp.setPadding(new Insets(10));
        grid.add(sp, 1, 5);
        
        HBox hbBtn = new HBox();
        hbBtn.setSpacing(30);
        Button back = new Button("Volver");
        back.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(back);
        Button enter = new Button("Buscar");
        enter.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        hbBtn.getChildren().add(enter);
        grid.add(hbBtn, 1, 7);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainScene(primaryStage);
            }
        });
        
        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int pos = GestionMultimedia.buscar(texts[0].getText(), texts[1].getText());
                if (pos == -1) {
                    target1.setFill(Color.FIREBRICK);
                    target1.setText("El elemento no se encuentra en la lista");
                    target2.setFill(Color.FIREBRICK);
                    target2.setText("\n\n\n\n\n");
                    primaryStage.setTitle("El elemento no existe");
                }else{
                    target1.setFill(Color.GREEN);
                    target1.setText(String.format("Posición: %d", pos));
                    target2.setFill(Color.GREEN);
                    target2.setText(GestionMultimedia.info(pos));
                    primaryStage.setTitle(texts[0].getText());
                }
            }
        });
    }
    
    private static void listar(Stage primaryStage){
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.setPadding(new Insets(25, 75, 25, 75));
        
        Scene scene = new Scene(vb, 640, 480);
        primaryStage.setTitle("Lista de elementos");
        primaryStage.setScene(scene);
        
        Text target = new Text();
        target.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 20));
        target.setTextAlignment(TextAlignment.CENTER);
        if (GestionMultimedia.info().equals("")) {
            target.setFill(Color.FIREBRICK);
            target.setText("\nLa lista está vacía\n");
        }else{
            target.setFill(Color.GREEN);
            target.setText(GestionMultimedia.info());
        }
        StackPane paneSp = new StackPane(target);
        paneSp.setAlignment(Pos.CENTER);
        ScrollPane sp = new ScrollPane(paneSp);
        //sp.setPadding(new Insets(1, 75, 1, 125));
        //sp.setCenterShape(true);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        vb.getChildren().add(sp);
        
        Button back = new Button("Volver");
        back.setFont(Font.font("Comic Sans MS", FontWeight.MEDIUM, 22));
        vb.getChildren().add(back);
        
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainScene(primaryStage);
            }
        });
    }
    
}
