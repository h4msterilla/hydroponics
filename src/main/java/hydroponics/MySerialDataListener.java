package hydroponics;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

import java.util.ArrayList;

public class MySerialDataListener implements SerialPortDataListener , SerialPortPacketListener {

    private StringBuilder buffer;
    private ArrayList<ArduinoCommandListener> commands;


    public MySerialDataListener(){
        buffer = new StringBuilder("");
        commands = new ArrayList<>();
    }

    public void addCommandListener(ArduinoCommandListener listener){
        commands.add(listener);
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
        //return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        buffer.append((char) event.getReceivedData()[0]);

        if (buffer.indexOf("\n") != -1) {
            buffer.deleteCharAt(buffer.length()-1);//delete \n
            buffer.deleteCharAt(buffer.length()-1);
            processCommandListeners(buffer.toString());
            buffer.delete(0,buffer.length());
        }
    }

    @Override
    public int getPacketSize() {
        return 1;
    }

    private void processCommandListeners(String command){
        if (command.length()>0){
            for(ArduinoCommandListener cl:commands)
                cl.processCommand(command);
        }
    }
}
