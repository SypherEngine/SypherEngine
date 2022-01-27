module dev.aurumbyte.sypherengine {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    exports dev.aurumbyte.sypherengine;
    opens dev.aurumbyte.sypherengine to javafx.fxml;

    exports dev.aurumbyte.sypherengine.core;
    opens dev.aurumbyte.sypherengine.core to javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.graphics;
    opens dev.aurumbyte.sypherengine.core.graphics to javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.audio;
    opens dev.aurumbyte.sypherengine.core.audio to javafx.fxml, javafx.media, javafx.base;

    exports dev.aurumbyte.sypherengine.core.logic;
    opens dev.aurumbyte.sypherengine.core.logic to javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.event;
    opens dev.aurumbyte.sypherengine.core.event to javafx.fxml;
    exports dev.aurumbyte.sypherengine.components.scene;
    opens dev.aurumbyte.sypherengine.components.scene to javafx.fxml;
}