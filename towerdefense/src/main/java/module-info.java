module com.towerdefense {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.towerdefense to javafx.fxml;
    exports com.towerdefense;
}
