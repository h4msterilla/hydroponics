package hydroponics.sheduler;

public class HydroShedule {

    protected Thread shedule;


    public void start(){
        shedule = new Thread(this::process);
        shedule.setName("Hydro-Thread");
        shedule.start();
    }

    public void process() {

        System.out.println("proccesing shedule");

        while(MyTime.getDayTime() <= this.startDayTime())
            pause(1000);

        pause(2000);

        this.setup();
        while (MyTime.getDayTime() < this.stopDayTime() && loopFlag) {
            this.loop();
        }
        this.end();

    }

    protected boolean loopFlag = true;

    public void stop(){
        loopFlag = false;
        //shedule.notify();
    }

    protected synchronized void pause(int millis){

        try {
            //shedule.wait(millis);
            //wait(millis);
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
