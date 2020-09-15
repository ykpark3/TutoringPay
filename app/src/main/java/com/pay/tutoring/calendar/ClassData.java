package com.pay.tutoring.calendar;

public class ClassData {
    private String name;
    private String time;
    private String count;
    private String progress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }


    public ClassData(String name, String time, String count, String progress) {
        this.name = name;
        this.time = time;
        this.count = count;
        this.progress = progress;
    }
}
