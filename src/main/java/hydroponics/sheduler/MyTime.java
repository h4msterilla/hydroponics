package hydroponics.sheduler;

import java.time.Clock;

public class MyTime {

    static private Clock clock = Clock.systemDefaultZone();

    public static long getTime(){
        return clock.millis();
    }

    public static long getDayTime(){
        return getDayTime(getTime());
    }

    public static long getSec(){
        return clock.millis()/1000%60;
    }

    public static long getSec(long time){
        return time/1000%60;
    }

    public static long getMin(){
        return clock.millis()/1000/60%60;
    }

    public static long getMin(long time){
        return time/1000/60%60;
    }

    public static long getHour(){
        return clock.millis()/1000/60/60%24;
    }

    public static long getHour(long time){
        return time/1000/60/60%24;
    }

    public static long getHour(int timeZone){
        long time = (getHour() + timeZone) % 24;
        if(time<0) time+=24;
        return time;
    }

    public static long getDayTime(long time){
        return time%(1000*60*60*24);
    }

    public static long getDayTime(long hours,long minutes,long seconds){
        return (seconds*1000)+(minutes*1000*60)+(hours*1000*60*60);
    }

}
