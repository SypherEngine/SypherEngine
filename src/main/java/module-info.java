module SypherEngine {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;

    exports dev.aurumbyte.sypherengine.core;
    exports dev.aurumbyte.sypherengine.core.audio;
    exports dev.aurumbyte.sypherengine.core.graphics;
    exports dev.aurumbyte.sypherengine.core.logic;
    exports dev.aurumbyte.sypherengine.core.event;
    exports dev.aurumbyte.sypherengine.config;
    exports dev.aurumbyte.sypherengine.ecs;
    exports dev.aurumbyte.sypherengine.components;
    exports dev.aurumbyte.sypherengine.components.scene;
    exports dev.aurumbyte.sypherengine.components.camera;
    exports dev.aurumbyte.sypherengine.math;
    exports dev.aurumbyte.sypherengine.logging;
    exports dev.aurumbyte.sypherengine.logging.logUtils;
    exports dev.aurumbyte.sypherengine.ui;
    exports dev.aurumbyte.sypherengine.core.graphics.gradients;
    exports dev.aurumbyte.sypherengine.core.graphics.tiles;
}
