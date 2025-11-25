module org.example.ecosimul {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ecosimul to javafx.fxml;
    exports org.example.ecosimul;
}