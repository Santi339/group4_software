module com.example.audiobookgenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires freetts;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.audiobookgenerator to javafx.fxml;
    exports com.example.audiobookgenerator;
}