package com.pay.tutoring.data;

/**
 * user가 선생님일 때
 * 학생 테이블 VO
 */
public class StudentVO {
    String student_id;
    String name;
    String startDay;
    String startTime;
    String endTime;
    String repeatDay;
    int count;
    int totalCount;
    int progress;

    public StudentVO(String student_id,
            String name,
            String startDay,
            String startTime,
            String endTime,
            String repeatDay,
            int count,
            int totalCount,
            int progress){
        this.student_id = student_id;
        this.name = name;
        this.startDay = startDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeatDay = repeatDay;
        this.count = count;
        this.totalCount = totalCount;
        this.progress = progress;


    }

    public String getName()
    {
        return name;
    }

}
