package kertotaulu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Parent root;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./gui.fxml"));
        controller = new Controller(primaryStage);
//        root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        loader.setController(controller);
        root = loader.load();
        controller.bindEvents();
        primaryStage.setTitle("Kertotaulu");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 450, 80));
        primaryStage.show();
        primaryStage.setOnCloseRequest((event)-> System.exit(0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
