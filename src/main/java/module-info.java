module io.github.sypherengine {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    exports io.github.sypherengine;

    exports io.github.sypherengine.core;
    exports io.github.sypherengine.core.graphics;
    exports io.github.sypherengine.core.audio;
    exports io.github.sypherengine.core.logic;
    exports io.github.sypherengine.core.event;

    exports io.github.sypherengine.components.scene;

    exports io.github.sypherengine.ui;

    exports io.github.sypherengine.config;



    opens io.github.sypherengine to javafx.fxml;

    opens io.github.sypherengine.core to javafx.fxml;
    opens io.github.sypherengine.core.audio to javafx.fxml;
    opens io.github.sypherengine.core.logic to javafx.fxml;
    opens io.github.sypherengine.core.event to javafx.fxml;
    opens io.github.sypherengine.core.graphics to javafx.fxml;

    opens io.github.sypherengine.components.scene to javafx.fxml;

    opens io.github.sypherengine.ui to javafx.fxml;

    opens io.github.sypherengine.config to javafx.fxml;

}