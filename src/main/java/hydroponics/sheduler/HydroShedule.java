package hydroponics.sheduler;

public class HydroShedule {

    protected Thread shedule;

    public boolean isAlive(){
        return shedule.isAlive();
    }

    public void start(){
        shedule = new Thread(this::process);
        shedule.start();
    }

    public void process() {

        while(MyTime.getDayTime() <= this.startDayTime())
            pause(1000);

        pause(2000);

        this.setup();
        while (MyTime.getDayTime() < this.stopDayTime()) {
            this.loop();
        }
        this.end();

    }

    public void stop(){
        shedule.interrupt();
        this.end();
    }

    protected void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void setup(){}

    protected void loop(){}

    protected void end(){}

    protected long startDayTime(){return MyTime.getDayTime(0,0,0);}

    protected long stopDayTime(){return MyTime.getDayTime(23,59,59);}

}
