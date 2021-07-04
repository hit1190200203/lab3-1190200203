package entity;


import java.util.Objects;

/**
 * 课程类
 */
public class Course {

    /**
     * 课程 ID
     */
    private String courseID;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 教师名字
     */
    private String teacherName;

    /**
     * 地点A
     */
    private String courseAddress;

    private int week=0;

    public Course() {
        super();
    }

    public Course(String id, String name, String teacherName, String address) {
        courseID = id;
        courseName = name;
        this.teacherName = teacherName;
        courseAddress = address;

    }

    public String getID() {
        return this.courseID;
    }

    public void setID(String ID) {
        courseID = ID;
    }

    public String getName() {
        return this.courseName;
    }

    public void setName(String name) {
        courseName = name;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAddress() {
        return this.courseAddress;
    }

    public void setAddress(String address) {
        courseAddress = address;
    }

    public int getWeek() {
        return this.week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Course course = (Course) object;
        return Objects.equals(this.courseID, course.courseID) && Objects.equals(this.courseName, course.courseName) && Objects.equals(teacherName, course.teacherName) && Objects.equals(this.courseAddress, course.courseAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.courseID, this.courseName, this.teacherName, this.courseAddress);
    }

    @Override
    public String toString() {
        return
                "[id='" + this.courseID + '\'' +
                ", name='" + this.courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", address='" + this.courseAddress + '\''+"]" ;
    }
}
