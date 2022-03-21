module com.amirali.jfxsplash.jfxsplashdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.amirali.jfxsplash;

    opens com.amirali.jfxsplash.jfxsplashdemo to javafx.fxml;
    exports com.amirali.jfxsplash.jfxsplashdemo;
}