package com.amirali.jfxsplash;

import com.amirali.jfxsplash.controllers.DefaultViewController;
import com.amirali.jfxsplash.listeners.EventListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JFXSplash {

    private final Duration duration;
    private final String title;
    private final double width , height;
    private final Parent root;
    private final Stage stage;
    private final Timeline timeline;
    private final Stage mainStage;
    private final List<EventListener> listeners;

    public JFXSplash(SplashBuilder builder){
        this.duration = builder.duration;
        this.title = builder.title;
        this.width = builder.width;
        this.height = builder.height;
        this.root = builder.root;
        this.stage = builder.stage;
        this.timeline = builder.timeline;
        this.mainStage = builder.mainStage;
        this.listeners = builder.listeners;
    }

    public String getTitle() {
        return title;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Parent getLayout(){
        return root;
    }

    public void stop(){
        if (mainStage.isShowing())
            throw new IllegalStateException("Cannot stop splash screen once stage has been set visible");
        if (timeline.getCurrentTime().lessThan(duration)){
            for (EventListener listener : listeners)
                listener.onFinish();
            timeline.stop();
            mainStage.show();
            stage.close();
        }
    }

    public List<EventListener> getListeners() {
        return listeners;
    }

    public ReadOnlyObjectProperty<Duration> currentTimeProperty(){
        return timeline.currentTimeProperty();
    }

    public Stage getStage() {
        return stage;
    }

    public static class SplashBuilder {
        private Stage mainStage;
        private Duration duration = Duration.millis(2000);
        private String title = "";
        private double width = 600 , height = 400;
        private Parent root;
        private final List<EventListener> listeners;
        private final Stage stage = new Stage();
        private Timeline timeline;

        public SplashBuilder(@NotNull Stage stage){
            this.mainStage = stage;
            this.listeners = new ArrayList<>();
        }

        public SplashBuilder setTitle(String title){
            this.title = title;
            return this;
        }

        public SplashBuilder setDuration(@NotNull Duration duration){
            this.duration = duration;
            return this;
        }

        public SplashBuilder setStage(@NotNull Stage stage){
            this.mainStage = stage;
            return this;
        }

        public SplashBuilder setWidth(double width) {
            this.width = width;
            return this;
        }

        public SplashBuilder setHeight(double height){
            this.height = height;
            return this;
        }

        public SplashBuilder addEventListeners(EventListener... listener){
            Collections.addAll(listeners , listener);
            return this;
        }

        public SplashBuilder setLayout(Parent root){
            this.root = root;
            return this;
        }

        public SplashBuilder addIcons(Image... images){
            stage.getIcons().addAll(images);
            return this;
        }

        private void showDefault() throws IOException {
            stage.centerOnScreen();
            stage.setTitle(title);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setMinHeight(height);
            stage.setMinWidth(width);
            var loader = new FXMLLoader(getClass().getResource("default-view.fxml"));
            stage.setScene(new Scene(loader.load() , width , height));
            DefaultViewController controller = loader.getController();
            controller.setTitle(title);
            stage.show();
            center(stage);

            if (!listeners.isEmpty()) {
                for (EventListener listener : listeners)
                    listener.onStart();
            }
            var keyFrame = new KeyFrame(duration , event -> {
                if (!listeners.isEmpty()) {
                    for (EventListener listener : listeners)
                        listener.onFinish();
                }
                mainStage.show();
                stage.close();
            });
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(1);
            timeline.play();
        }

        private void showCustom(){
            stage.setTitle(title);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setMinHeight(height);
            stage.setMinWidth(width);
            stage.setScene(new Scene(root , width , height));
            stage.show();
            center(stage);
            if (!listeners.isEmpty()) {
                for (EventListener listener : listeners)
                    listener.onStart();
            }
            var keyFrame = new KeyFrame(duration , event -> {
                if (!listeners.isEmpty()) {
                    for (EventListener listener : listeners)
                        listener.onFinish();
                }
                mainStage.show();
                stage.close();
            });
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(1);
            timeline.play();
        }

        private void center(Stage window){
            var screenBounds = Screen.getPrimary().getBounds();
            window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
            window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
        }

        public JFXSplash build() throws IOException {
            if (mainStage.isShowing())
                throw new IllegalStateException("Cannot set splash screen once stage has been set visible");
            if (root == null)
                showDefault();
            else
                showCustom();
            return new JFXSplash(this);
        }
    }
}
