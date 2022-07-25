package hydroponics.sheduler.shedules;

import hydroponics.Hydro;
import hydroponics.sheduler.HydroShedule;

public class testHydroSedule extends HydroShedule {

    @Override
    protected void setup(){
        System.out.println("setup");
    }

    int i = 0;

    @Override
    protected void loop(){
        System.out.println("Loop = " + i++);

        Hydro.hydroCommand.waterUp();

        this.pause(1000);

        Hydro.hydroCommand.waterDown();

        this.pause(1000);
    }
    @Override
    protected void end(){
        System.out.println("end");
    }

}
