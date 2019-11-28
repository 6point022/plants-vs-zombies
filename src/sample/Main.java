package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// I am Anmol
// I am naman
// I am Anmol2
//I am Naman2
// I am Naman3
public class Main extends Application {
    public static Stage window;
    public static int width = 839;//600;
    public static int height = 387;//336;


    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
