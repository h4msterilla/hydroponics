package hydroponics;

import java.util.EventListener;

public abstract class ArduinoCommandListener implements EventListener {
    abstract void processCommand(String command);
}
