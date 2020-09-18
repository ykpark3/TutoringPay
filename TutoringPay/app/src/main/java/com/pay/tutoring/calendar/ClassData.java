package com.pay.tutoring.calendar;

import com.prolificinteractive.materialcalendarview.CalendarDay;

public class ClassData {

    private String name;
    private CalendarDay date;
    private String time;
    private String count;
    private String progress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CalendarDay getDate() { return date;}

    public void setDate(CalendarDay date) { this.date = date;}

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



    public ClassData(String name, CalendarDay date, String time, String count, String progress) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.count = count;
        this.progress = progress;
    }

}
