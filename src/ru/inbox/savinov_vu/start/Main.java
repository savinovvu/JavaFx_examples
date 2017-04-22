package ru.inbox.savinov_vu.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.inbox.savinov_vu.controllers.MainController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Адресная книга");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(400);
        Scene scene = new Scene(fxmlMain, 400, 400);
        scene.getStylesheets().add(0, "ru/inbox/savinov_vu/styles/my.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
