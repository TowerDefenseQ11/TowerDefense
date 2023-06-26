module com.towerdefense {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.towerdefense to javafx.fxml;
    exports com.towerdefense;
    exports com.towerdefense.map;
    opens com.towerdefense.map to javafx.fxml;
    exports com.towerdefense.map.tile;
    opens com.towerdefense.map.tile to javafx.fxml;
    exports com.towerdefense.weapon.bullet;
    opens com.towerdefense.weapon.bullet to javafx.fxml;
    exports com.towerdefense.enemy;
    opens com.towerdefense.enemy to javafx.fxml;
    exports com.towerdefense.enemy.handler;
    opens com.towerdefense.enemy.handler to javafx.fxml;
    exports com.towerdefense.engine;
    opens com.towerdefense.engine to javafx.fxml;
    exports com.towerdefense.weapon;
    opens com.towerdefense.weapon to javafx.fxml;
    exports com.towerdefense.enemy.type;
    opens com.towerdefense.enemy.type to javafx.fxml;
}
