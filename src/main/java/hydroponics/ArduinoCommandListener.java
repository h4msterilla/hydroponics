package hydroponics;

import java.util.EventListener;

public abstract class ArduinoCommandListener implements EventListener {
    protected abstract void processCommand(String command);
}
