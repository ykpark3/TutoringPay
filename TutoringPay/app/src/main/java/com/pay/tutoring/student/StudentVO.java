package com.pay.tutoring.student;

/**
 * user가 선생님일 때
 * 학생 테이블 VO
 */
public class StudentVO {
    String student_id;
    String name;
    String lectureDay;
    String startTime;
    String endTime;
    int repeatDay;
    int count;
    int totalCount;
    String progress;
    int pay;

    public StudentVO(String student_id,
                     String name,
                     String lectureDay,
                     String startTime,
                     String endTime,
                     int repeatDay,
                     int count,
                     int totalCount,
                     String progress,
                     int pay) {
        this.student_id = student_id;
        this.name = name;
        this.lectureDay = lectureDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeatDay = repeatDay;
        this.count = count;
        this.totalCount = totalCount;
        this.progress = progress;
        this.pay = pay;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getName() {
        return name;
    }

    public String getLectureDay() { return lectureDay; }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getRepeatDay() {
        return repeatDay;
    }

    public int getCount() {
        return count;
    }

    public int getTotalCount() {
        return  totalCount;
    }

    public String getProgress() {
        return progress;
    }

    public int getPay() {
        return pay;
    }

}