package utils;

public class TimeManager {
    private long startTime;
    private long totalPauseTime;
    private long pauseInit;
    private float chronometer;
    private boolean started = false;
    public TimeManager(){
        pauseInit = 0;
        totalPauseTime = 0;
        startTime = 0;
    }
    public void start(){
        //
        startTime = System.nanoTime();
        started = true;
    }
    public float getTime(){
        //
        return (System.nanoTime() - startTime - totalPauseTime)/1000000000f;
    }

    public void pause(){
        pauseInit = System.nanoTime();
    }
    public void unpause(){
        totalPauseTime+= System.nanoTime()- pauseInit;
    }
    public void reset(){
        startTime = 0;
        pauseInit = 0;
        totalPauseTime = 0;
        started = false;
    }
    public void setChronometer(float seconds){
        this.chronometer = seconds;
    }
    public boolean ring(){
        if(getTime()>= chronometer) return true;
        return false;
    }
    public boolean started(){
        return started;
    }
}
