module org.example.visualscripting {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires com.fasterxml.jackson.databind;
    requires com.squareup.javapoet;
    requires java.compiler;

    opens org.example.visualscripting to javafx.fxml;
    exports org.example.visualscripting;
}