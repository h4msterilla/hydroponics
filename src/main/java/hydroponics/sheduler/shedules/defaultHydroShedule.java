package hydroponics.sheduler.shedules;

import hydroponics.ArduinoCommandListener;
import hydroponics.Hydro;
import hydroponics.sheduler.HydroShedule;
import hydroponics.sheduler.MyTime;

public class defaultHydroShedule extends HydroShedule {

    @Override
    protected void setup(){
        Hydro.hydroCommand.addArduinoCommandListener(waterOverLevel);
        System.out.println("Start default shedule");
        Hydro.hydroCommand.LEDsOn();
    }

    private long waterUpTiming = 0;

    boolean waterOverLevelFlag = false;

    @Override
    protected void loop(){
        long waterUpTimingStart = MyTime.getDayTime();
        waterOverLevelFlag = false;
        Hydro.hydroCommand.waterUp();
        while(!waterOverLevelFlag)
            pause(100);

        waterUpTiming = MyTime.getDayTime() - waterUpTimingStart;
        Hydro.hydroCommand.waterStop();

        System.out.println(MyTime.getHour(waterUpTiming) + ":" + MyTime.getMin(waterUpTiming) + ":" + MyTime.getSec(waterUpTiming));

        pause(1000*10);

        Hydro.hydroCommand.waterDown();
        pause((int)waterUpTiming);
        Hydro.hydroCommand.waterStop();

        pause(1000*10);

    }

    @Override
    protected void end(){
        System.out.println("Stop default shedule");
        Hydro.hydroCommand.LEDsOff();
    }

    ArduinoCommandListener waterOverLevel = new ArduinoCommandListener() {
        @Override
        protected void processCommand(String command) {
            if(command.contains("Maximum water level reached!"))
                waterOverLevelFlag = true;
        }
    };

}
