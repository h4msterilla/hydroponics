package hydroponics;

import arduino.Arduino;

public class HydroCommand {

    private MySerialDataListener mySerialDataListener;

    public HydroCommand(String descriptor){
        Hydro.arduino = new Arduino(descriptor,9600);
        mySerialDataListener = new MySerialDataListener();
        Hydro.arduino.getSerialPort().addDataListener(mySerialDataListener);

        new Thread(() -> {
            System.out.println("connection open - " + Hydro.arduino.openConnection());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void addArduinoCommandListener(ArduinoCommandListener listener){
        mySerialDataListener.addCommandListener(listener);
    }

    public void waterStop(){
        Hydro.arduino.serialWrite('0');
    }

    public void waterUp(){
        Hydro.arduino.serialWrite('1');
    }

    public void waterDown(){
        Hydro.arduino.serialWrite('2');
    }

    public void LEDsOn(){
        Hydro.arduino.serialWrite('3');
    }

    public void LEDsOff(){
        Hydro.arduino.serialWrite('4');
    }
}
