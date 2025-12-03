module org.example.ecosimul {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.compiler;


    opens org.example.ecosimul to javafx.fxml;
    exports org.example.ecosimul;
    exports org.example.ecosimul.core;
    opens org.example.ecosimul.core to javafx.fxml;
    exports org.example.ecosimul.entity;
    opens org.example.ecosimul.entity to javafx.fxml;
    exports org.example.ecosimul.core.server;
    opens org.example.ecosimul.core.server to javafx.fxml;
}