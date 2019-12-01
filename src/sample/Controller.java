package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.game.Plant;
import sample.game.User;
import sample.game.Zombie;


import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

public class Controller {
    @FXML
    public Button exitButton;
    @FXML
    public AnchorPane root;

    public void startButtonHandler(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("chooselevel.fxml"));
        Main.window.setTitle("Scene 2");
        Main.window.getScene().setRoot(root);
    }

    public void loadButtonHabdler(){
        AtomicReference<String> username= new AtomicReference<>(new String());
        Label labelfirst= new Label();
        labelfirst.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        labelfirst.setText("Enter Name");
        labelfirst.setTextFill(Color.web("#000000"));
        Button button= new Button("Load");
        TextField text= new TextField();
        button.setOnAction(e ->
                {
                    String name=new String(text.getText());
                    ObjectInputStream in = null;
                     try {
                     in = new ObjectInputStream (
                             new FileInputStream(name));
                     User s1 = (User) in.readObject();
                         System.out.println("Object Desiralized");
                         System.out.println("Hello"+s1.getName());
                         // Setting up loaded game

                         Parent root = FXMLLoader.load(getClass().getResource("Loadedgame.fxml"));
                         Main.window.setTitle(s1.getName());
                         Main.window.getScene().setRoot(root);
                         AnchorPane backyard= (AnchorPane) root;
                         for(Node n: backyard.getChildren())
                         {
                             if(n.getId()!=null && n.getId().equals("counterLabel"))
                             {
                                 Label l= (Label) n;
                                 l.setText(String.valueOf(s1.getSuncollected()));
                             }
                         }
                         for(Plant p: s1.getGame().listOfPlants)
                         {
                             for(Node n: backyard.getChildren())
                             {
                                 if(n.getId()!=null)
                                 {
                                     if (n.getId().equals(p.getId()))
                                     {
                                       ImageView i=(ImageView) n;
                                     i.setImage(new Image("/resources/peashooterplanted.gif"));
                                     i.setFitHeight(70);
                                     i.setFitWidth(45);
                                     }
                                 }
                             }
//                             System.out.println(p.getId());
                         }
                         for(Zombie p: s1.getGame().listOfWalkingZombies)
                         {
                             ImageView i= new ImageView(new Image("resources/zombie_normal.gif"));
                             i.setLayoutX(p.getPositionX());
                             i.setLayoutY(p.getPositionY());
                             i.setFitWidth(56);
                             i.setFitHeight(99);
                             backyard.getChildren().add(i);
                         }

                     } catch (FileNotFoundException ex) {
                         System.out.println("notfound");
                         ex.printStackTrace();
                     } catch (IOException ex) {
                         ex.printStackTrace();
                     } catch (ClassNotFoundException ex) {
                         ex.printStackTrace();
                     } finally {
                         try {
                             in.close();
                         } catch (IOException ex) {
                             ex.printStackTrace();
                         }
                     }

                }
        );

        VBox layout= new VBox(5);
        layout.setLayoutX(600);
        layout.setLayoutY(280);
        layout.getChildren().addAll(labelfirst, text, button);
        root.getChildren().add(layout);
    }

    public void exitButtonHandler(ActionEvent e) {
        System.exit(0);
    }
}
