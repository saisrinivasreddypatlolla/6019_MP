package com.example.projecttemp.Model;

public class CourseDetails {

  private String CourseName,CourseGrade,Attendence, Status;

    public CourseDetails() {
    }

    public CourseDetails(String courseName, String courseGrade, String attendence, String status) {
        CourseName = courseName;
        CourseGrade = courseGrade;
        Attendence = attendence;
        Status = status;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseGrade() {
        return CourseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        CourseGrade = courseGrade;
    }

    public String getAttendence() {
        return Attendence;
    }

    public void setAttendence(String attendence) {
        Attendence = attendence;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
