package app;

import entity.Course;
import intervalSet.CourseIntervalSet;
import commonAPI.APIs;
import utils.ParserDateUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * 课程管理类
 */
public class CourseScheduleApp {
    /**
     * 课程列表
     */
    private List<Course> courseList;

    /**
     * 课程集合
     */
    private CourseIntervalSet courseIntervalSet;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 周学时数
     */
    private int week;

    /**
     * 构造函数
     * @param startDate 开始时间
     * @param week 课程周数
     */
    public CourseScheduleApp(String startDate, int week) {
        this.week = week;
        this.startDate=startDate;
        courseList = new LinkedList<>();
        courseIntervalSet=new CourseIntervalSet();
    }

    /**
     * 添加课程方法
     * @param course 课程
     * @return 返回结果：是否成功
     */
    public boolean addCourse(Course course){
        for(Course course1 : courseList){
            if(course.equals(course1)){
                System.out.println("课程已经存在");
                return false;
            }
        }

        courseList.add(course);
        return true;

    }

    /**
     * 删除课程方法
     * @param course 课程
     * @return 是否成功删除课程
     */
    public boolean removeCourse(Course course){
        if (courseList.contains(course)){
            if (courseIntervalSet.labels().contains(course)){
                System.out.println("时间有课程安排，暂时不能删除！");
                return false;
            }
            courseList.remove(course);
            return true;
        }else {
            System.out.println("没有此课程");
            return false;
        }
    }

    /**
     * 手动安排课程方法
     * @param index 下标
     * @param day 周几
     * @param hour 时间段
     * @return 返回是否添加成功
     */
    public boolean insertSchedule(int index, long day, long hour){

        Course course = courseList.get(index);
        if (course.getWeek() < week){
            courseIntervalSet.insert(day,hour,course);
        }else {
            System.out.println("课程已经完毕");
            return false;
        }
        course.setWeek(course.getWeek() + 1);
        courseList.set(index,course);
        return true;
    }

    /**
     * 查看没有安排的课程方法
     */
    public void lookNoScheduleCourse(){
        System.out.println("未安排的课程：");
        Iterator<Course> iter = courseList.iterator();
        while (iter.hasNext()) {
            Course course = iter.next();
            if (course.getWeek()==0){
                System.out.println(course.toString());
            }
        }
    }

    /**
     * 空闲比方法
     * @return 空闲比
     */
    public double calFree() throws Exception {
        return new APIs<Course>().calcFreeTimeRatio(courseIntervalSet);

    }

    /**
     * 冲突比方法
     * @return 冲突比
     */
    public double calConflict() throws Exception {
        return new APIs<Course>().calcConflictRatio(courseIntervalSet);
    }

    /**
     * 查看已经课程的方法
     */
    public void lookCourseSchedule(){
        System.out.println("已安排的课程");
        System.out.println(courseIntervalSet.toString());
    }

    /**
     * 输出所有的课程方法
     */
    public void lookCourses(){
        System.out.println("所有课程：");
        int i=0;
        Iterator<Course> iter = courseList.iterator();
        while (iter.hasNext()) {
            Course course = iter.next();
            System.out.println("["+i+"]"+course.toString());
            i++;
        }
    }

    public static void main(String[] args) {
        System.out.println("课程管理系统！");
        Scanner scanner=new Scanner(System.in);
        String startDate;
        int ws;
        for (;;){
            System.out.printf("（格式如：2007-01-01）开始时间：");
            startDate=scanner.nextLine();
            if (!ParserDateUtils.validDate(startDate)){
                System.out.println("输入时间格式错误："+startDate);
            }else {
                break;
            }
        }
        for (;;){
            try {
                System.out.print("周学时数：");
                ws=scanner.nextInt();
                break;
            }catch (Exception e){

            }
        }
        CourseScheduleApp app =new CourseScheduleApp(startDate,ws);
        int flag=0;
        while (flag!=6){
            String[] inf = new String[]{"请输入数字，选择操作：","1. 查看课表","2. 查看课程信息","3. 安排课程",
                    "4. 添加课程","5. 删除课程","6. 退出"};
            for(int i = 0; i < 7; ++i){
                System.out.println(inf[i]);
            }
            flag=scanner.nextInt();
            String[] lines;
            String ID,courseName,teachName,address;
            switch (flag){
                case 1:
                    app.lookCourseSchedule();
                    break;
                case 2:
                    try {
                        System.out.println("空闲比："+app.calFree());

                    } catch (Exception e) {

                    }
                    try {
                        System.out.println("冲突比:"+app.calConflict());
                    } catch (Exception e) {

                    }
                    app.lookNoScheduleCourse();
                    break;
                case 3:
                    app.lookCourses();
                    System.out.println("请选择要课程（输入[]中的数字）：");
                    int index=scanner.nextInt();
                    System.out.print("请选择周几上课（选择输入数字1-7）：");
                    int day=scanner.nextInt();
                    int hour;
                    for (;;){
                        System.out.println("[8]8:00-10:00  [10]10:00-12:00  [13]13:00-15:00  [15]15:00-17:00 [19]19:00-21:00选择一个时间段输入[]中的数字）：\n");
                        hour=scanner.nextInt();
                        if (hour==8||hour==10||hour==13||hour==15||hour==19){
                            break;
                        }
                    }
                    app.insertSchedule(index,day,hour);
                    break;
                case 4:
                    System.out.println("请在一行输入课程ID，课程名字，教师名字，地点，以逗号隔开：");
                    lines = scanner.next().split(",");
                    ID=lines[0];
                    courseName=lines[1];
                    teachName=lines[2];
                    address=lines[3];
                    Course course=new Course(ID,courseName,teachName,address);
                    app.addCourse(course);
                    break;
                case 5:
                    System.out.println("请在一行输入课程ID，课程名字，教师名字，地点，以逗号隔开：");
                    lines = scanner.next().split(",");
                    ID=lines[0];
                    courseName=lines[1];
                    teachName=lines[2];
                    address=lines[3];

                    app.removeCourse(new Course(ID,courseName,teachName,address));
                    break;
                case 6:
                    System.exit(0);
                    break;
            }

        }
    }

}
