package com.amirali.jfxsplash.jfxsplashdemo;

import com.amirali.jfxsplash.JFXSplash;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        var root = new StackPane(new Label("Hello, World!"));
        stage.setTitle("JFXSplashDemo");
        stage.setScene(new Scene(root , 400 , 250));

        var loader = new FXMLLoader(getClass().getResource("splash-view.fxml"));
        var splash = new JFXSplash.SplashBuilder(stage)
                .setDuration(Duration.seconds(3))
                .setLayout(loader.load())
                .setTitle("JFXSplashDemo")
                .setWidth(534)
                .setHeight(350)
                .build();
        SplashController controller = loader.getController();
        controller.setDuration(splash.getDuration());
        splash.currentTimeProperty().addListener((observableValue, oldValue, newValue) -> controller.setProgress(newValue));
    }
}
