/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.event;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.stage.WindowEvent;

public class WindowEventListener implements EventHandler<WindowEvent> {
    public boolean isShowing = false;
    public boolean isShown = false;
    public boolean windowCloseRequested = false;
    public boolean isHiding = true;

    @Override
    public void handle(WindowEvent event) {
        EventType<WindowEvent> eventType = event.getEventType();

        switch (eventType.getName()) {
            case "WINDOW_SHOWING", "WINDOW_SHOWN" -> {
                isShowing = true;
                isShown = true;
                isHiding = false;
            }
            case "WINDOW_CLOSE_REQUEST" -> {
                windowCloseRequested = true;
                isShowing = false;
                isShown = false;
                isHiding = true;
            }
            case "WINDOW_HIDDEN" -> {
                windowCloseRequested = false;
                isShown = false;
                isShowing = false;
                isHiding = true;
            }
        }
    }
}
