module com.so {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens com.so.telas to javafx.fxml;
    exports com.so;
    exports com.so.telas;
    exports com.so.algoritmos;
}