module SypherEngine {
    requires transitive javafx.fxml;

    requires javafx.controls;
    requires javafx.media;
    requires java.desktop;

    exports dev.aurumbyte.sypherengine.core;
    exports dev.aurumbyte.sypherengine.core.graphics;
    exports dev.aurumbyte.sypherengine.core.graphics.gradients;
    exports dev.aurumbyte.sypherengine.core.audio;
    exports dev.aurumbyte.sypherengine.core.logic;
    exports dev.aurumbyte.sypherengine.core.event;

    exports dev.aurumbyte.sypherengine.components.scene;
    exports dev.aurumbyte.sypherengine.components.camera;

    exports dev.aurumbyte.sypherengine.ui;

    exports dev.aurumbyte.sypherengine.config;

    exports dev.aurumbyte.sypherengine.math;

    exports dev.aurumbyte.sypherengine.logging;
    exports dev.aurumbyte.sypherengine.logging.logUtils;
}