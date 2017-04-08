package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.MainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Views/kontrolMain.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setMainStage(primaryStage);
        primaryStage.setTitle("VCS");
        primaryStage.setScene(new Scene(root, 450, 350));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
