module com.amirali.jfxsplash {
    requires javafx.controls;
    requires javafx.fxml;
    requires annotations;

    opens com.amirali.jfxsplash.controllers to javafx.fxml;
    exports com.amirali.jfxsplash;
    exports com.amirali.jfxsplash.listeners;
}