package com.amirali.jfxsplash.jfxsplashdemo;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class SplashController {

    @FXML
    private ProgressBar progress;

    private Duration duration;

    public void setProgress(Duration value){
        double currentValue = value.toMillis();
        double totalValue = duration.toMillis();
        progress.setProgress(currentValue / totalValue);
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }
}
