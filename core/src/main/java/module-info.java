module io.github.sypherengine.core {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires io.github.sypherengine.util;
    requires io.github.sypherengine.ecs;
    requires io.github.sypherengine.physics;

    exports dev.aurumbyte.sypherengine.core;

    opens dev.aurumbyte.sypherengine.core to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.audio;

    opens dev.aurumbyte.sypherengine.core.audio to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.graphics;

    opens dev.aurumbyte.sypherengine.core.graphics to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.event;

    opens dev.aurumbyte.sypherengine.core.event to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.config;

    opens dev.aurumbyte.sypherengine.core.config to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.components.scene;

    opens dev.aurumbyte.sypherengine.core.components.scene to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.components.camera;

    opens dev.aurumbyte.sypherengine.core.components.camera to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.ui;

    opens dev.aurumbyte.sypherengine.core.ui to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.graphics.gradients;

    opens dev.aurumbyte.sypherengine.core.graphics.gradients to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.graphics.tiles;

    opens dev.aurumbyte.sypherengine.core.graphics.tiles to
            javafx.fxml;

    exports dev.aurumbyte.sypherengine.core.gameObject;
    exports dev.aurumbyte.sypherengine.core.graphics.animation;
    opens dev.aurumbyte.sypherengine.core.graphics.animation to javafx.fxml;
}
