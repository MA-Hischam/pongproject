module com.example.pongproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pongproject to javafx.fxml;
    exports com.example.pongproject;
}