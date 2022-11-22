module com.example.mycoffee {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.mycoffee to javafx.fxml;
    exports com.example.mycoffee;
}