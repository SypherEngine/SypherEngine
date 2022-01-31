module dev.aurumbyte.sypherengine {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    exports dev.aurumbyte.sypherengine;

    exports dev.aurumbyte.sypherengine.core;
    exports dev.aurumbyte.sypherengine.core.graphics;
    exports dev.aurumbyte.sypherengine.core.audio;
    exports dev.aurumbyte.sypherengine.core.logic;
    exports dev.aurumbyte.sypherengine.core.event;

    exports dev.aurumbyte.sypherengine.components.scene;

    opens dev.aurumbyte.sypherengine to javafx.fxml;

    opens dev.aurumbyte.sypherengine.core to javafx.fxml;
    opens dev.aurumbyte.sypherengine.core.audio to javafx.fxml;
    opens dev.aurumbyte.sypherengine.core.logic to javafx.fxml;
    opens dev.aurumbyte.sypherengine.core.event to javafx.fxml;
    opens dev.aurumbyte.sypherengine.core.graphics to javafx.fxml;

    opens dev.aurumbyte.sypherengine.components.scene to javafx.fxml;

}