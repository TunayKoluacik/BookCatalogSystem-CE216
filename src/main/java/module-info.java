module com.example.book_catalog_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.desktop;


    opens com.example.book_catalog_system to javafx.fxml;
    exports com.example.book_catalog_system;
}