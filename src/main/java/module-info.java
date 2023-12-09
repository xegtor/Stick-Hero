module com.apassignment.stickhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;

    opens com.approject.stickhero to javafx.fxml;
    exports com.approject.stickhero;
}