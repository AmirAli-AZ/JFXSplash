package com.amirali.jfxsplash.jfxsplashdemo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {

    @FXML
    private ProgressBar progress;

    private Duration duration;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }

    public void setProgress(Duration value){
        double currentValue = value.toMillis();
        double totalValue = duration.toMillis();
        progress.setProgress(currentValue / totalValue);
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }
}
