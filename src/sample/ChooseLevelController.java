package sample;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ChooseLevelController {
    @FXML
    public ImageView zombieNormal;

    public void level1Handler() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("level1.fxml"));

        Main.window.setTitle("Level 1");
        Main.window.setScene(new Scene(root, Main.width, Main.height));
    }

    public void level2Handler() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("level2.fxml"));
        Main.window.setTitle("Level 2");
        Main.window.setScene(new Scene(root, Main.width, Main.height));
    }

    public void level3Handler() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("level3.fxml"));
        Main.window.setTitle("Level 3");
        Main.window.setScene(new Scene(root, Main.width, Main.height));
    }

    public void level4Handler() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("level4.fxml"));
        Main.window.setTitle("Level 4");
        Main.window.setScene(new Scene(root, Main.width, Main.height));
    }

    public void level5Handler() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("level5b.fxml"));
        Main.window.setTitle("Level 5");
        Main.window.setScene(new Scene(root, Main.width, Main.height));
    }
}
