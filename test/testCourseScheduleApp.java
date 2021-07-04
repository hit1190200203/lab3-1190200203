package test;

import entity.Course;
import app.CourseScheduleApp;
import org.junit.Test;

/**
 * 测试CourseScheduleApp类
 */
public class testCourseScheduleApp {

    /**
     * 测试方法test
     */
    @Test
    public void test() throws Exception {
        CourseScheduleApp courseScheduleAppliaction = new CourseScheduleApp("2021-03-02",18);

        Course course1 = new Course();
        course1.setID("1");
        course1.setName("线性代数");
        course1.setTeacherName("张三");
        course1.setAddress("A教201");
        courseScheduleAppliaction.addCourse(course1);

        Course course2 = new Course();
        course2.setID("2");
        course2.setName("离散数学");
        course2.setTeacherName("王德芳");
        course2.setAddress("B教201");
        courseScheduleAppliaction.addCourse(course2);

        Course course3 = new Course();
        course3.setID("3");
        course3.setName("运筹学");
        course3.setTeacherName("刘花生");
        course3.setAddress("C教201");
        courseScheduleAppliaction.addCourse(course3);

        Course course4 = new Course();
        course4.setID("4");
        course4.setName("计算机图像");
        course4.setTeacherName("刘困");
        course4.setAddress("D教201");
        courseScheduleAppliaction.addCourse(course4);

        Course course5 = new Course();
        course5.setID("5");
        course5.setName("工业工程");
        course5.setTeacherName("魏合尚");
        course5.setAddress("C教201");
        courseScheduleAppliaction.addCourse(course5);


        courseScheduleAppliaction.lookNoScheduleCourse();

        System.out.println("安排课程");
        courseScheduleAppliaction.insertSchedule(0,5,3);
        courseScheduleAppliaction.insertSchedule(1,2,4);
        courseScheduleAppliaction.insertSchedule(1,3,3);
        courseScheduleAppliaction.insertSchedule(2,3,9);
        courseScheduleAppliaction.insertSchedule(2,1,7);
        courseScheduleAppliaction.insertSchedule(3,4,5);

        courseScheduleAppliaction.lookCourseSchedule();
        courseScheduleAppliaction.lookNoScheduleCourse();
        System.out.println(courseScheduleAppliaction.calFree());

        System.out.println(courseScheduleAppliaction.calConflict());
    }

}
