package org.example;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Model implements Serializable {
    private double x, y, r;
    private boolean hit;
    private String currentTime;
    private long executionTime;

    public Model(double x, double y, double r, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        this.executionTime = new Date().getTime();
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getR() { return r; }
    public boolean isHit() { return hit; }
    public String getCurrentTime() { return currentTime; }
    public long getExecutionTime() { return executionTime; }

    public void setExecutionTime(long time){
        this.executionTime = time;
    }
    @Override
    public String toString() {
        return String.format("x=%.2f, y=%.2f, r=%.2f, hit=%s, time=%dms, executionTime=%dms",
                x, y, r, hit, executionTime);
    }
}

