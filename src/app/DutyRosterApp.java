package app;

import entity.Employee;
import other.MyDate;
import intervalSet.DutyIntervalSet;
import utils.ParserDateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DutyRosterApp {


    private String startDate;

    private String endDate;

    private ArrayList<Employee> employeeArrayList;

    private DutyIntervalSet dutyIntervalSet;

    private String nowDate;

    private long day;

    int workDays;

    public DutyRosterApp(String startDate, String endDate) throws ParseException {

        this.startDate = startDate;
        this.endDate = endDate;
        workDays = countWordDays();
        this.day = -1;
        employeeArrayList = new ArrayList<>();
        dutyIntervalSet = new DutyIntervalSet();
    }

    /**
     * 添加员工方法
     *
     * @param e Employee
     * @return 返回是否添加成功员工
     */
    public boolean addEmployee(Employee e) {
        if (employeeArrayList.contains(e)) {
            System.out.println(e + "添加失败!");
            return false;
        }
        employeeArrayList.add(e);
        System.out.println("添加员工:" + e.toString());
        return true;
    }

    /**
     * 删除员工方法
     *
     * @param e
     * @return返回是否添加员工方法
     */
    public boolean removeEmployee(Employee e) {
        if (dutyIntervalSet.labels().contains(e)) {
            System.out.println(e + "有排班，删除失败!");
            return false;
        }
        if (employeeArrayList.contains(e)) {
            employeeArrayList.remove(e);
            return true;
        }
        return false;
    }


    /**
     * 随机添加排班信息
     *
     * @param employee employee
     * @param d        天数
     * @return 返回是否添加排版信息
     */
    public boolean insertToDuty(Employee employee, int d) {
        if (employeeArrayList.contains(employee)) {
            if (dutyIntervalSet.labels().contains(employee)) {
                System.out.println("有工作安排，暂时不能安排了");
                return false;
            } else {
                dutyIntervalSet.insert(day + 1, day + d, employee);
                day = day + d;
                System.out.println("添加成功" + employee.toString());
                return true;
            }
        } else {
            System.out.println("员工不存在");
            return false;
        }
    }

    /**
     * 随机安排方法
     */
    public void randomAssign() {
        Iterator<Employee> iter = employeeArrayList.iterator();
        int n = employeeArrayList.size();
        while (iter.hasNext()) {
            Employee employee = iter.next();
            if (day >= workDays) {
                break;
            }
            int tempDay = (int) ((workDays - day) / n * 0.8);
            tempDay = tempDay == 0 ? 1 : tempDay;

            insertToDuty(employee, tempDay);
        }
    }

    /**
     * 计算开始时间和结束时间相差几天
     *
     * @return 相差几天
     * @throws ParseException 异常抛出
     */
    private int countWordDays() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = simpleDateFormat.parse(startDate);
        Date d2 = simpleDateFormat.parse(endDate);
        return (int) ((d2.getTime() - d1.getTime() + 1000000) / (60 * 60 * 24 * 1000));
    }

    /**
     * 查看所有员工信息方法，姓名、职业、电话等
     */
    public void lookEmployees() {
        System.out.println("-------------员工信息--------------------");
        System.out.println("姓名 职业 电话");
        for (Employee employee : employeeArrayList) {
            System.out.println(employee.toString());
        }
    }

    /**
     * 查看员工排班信息，日期、姓名、职业、电话
     */
    public void lookPlan() {
        System.out.println("-------------员工安排--------------------");
        System.out.println("日期 姓名 职业 电话");
        this.nowDate = startDate;
        Set<Employee> employeeSet = dutyIntervalSet.labels();
        List<MyDate<Employee>> intervals = new ArrayList<>();
        for (Employee employee : employeeSet) {
            MyDate<Employee> employeeInterval = null;
            try {
                employeeInterval = new MyDate<>(employee, dutyIntervalSet.start(employee), dutyIntervalSet.end(employee));
            } catch (Exception e) {
                e.printStackTrace();
            }
            intervals.add(employeeInterval);
        }
        Collections.sort(intervals);
        for (MyDate interval : intervals) {
            int start = (int) interval.getStart();
            int end = (int) interval.getEnd();
            for (int i = start; i <= end; i++) {
                System.out.println(countFurtrueDate(i, nowDate) + "   " + interval.getLabel().toString());
            }

        }
    }

    /**
     * 计算日期，参数为需要增加的天数和现在的日期
     *
     * @param days  未来第几天
     * @param today 新日期
     * @return
     */
    private String countFurtrueDate(int days, String today) {
        String date = today;
        while (days > 0) { //死循环
            date = getNextDay(date);    //计算下一天
            days--;                        //要增加的天数--
        }
        return date;
    }

    /**
     * nextDay
     *
     * @param date
     * @return
     */
    private String getNextDay(String date) {
        int[] dates = ParserDateUtils.processDate(date);
        if (dates[2] == lastDaysOfMonth(dates[0], dates[1])) {
            dates[2] = 1;
            dates[1]++;
            if (dates[1] == 13) {//如果是年底
                dates[1] = 1;
                dates[0]++;
            }
        } else
            dates[0]++;
        return dates[0] + "-" + dates[1] + "-" + dates[2];
    }

    /**
     * 返回对应月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    private int lastDaysOfMonth(int year, int month) {
        int cnt = judgeLeapYear(year) ? 1 : 0;
        if (month == 2) {
            return 28 + cnt;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 31;
    }

    /**
     * 是否是闰年
     *
     * @param year 年份
     * @return 是否闰年
     */
    private boolean judgeLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        return false;
    }

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) throws ParseException {
        System.out.println("排班管理系统！");
        Scanner scanner = new Scanner(System.in);
        String startDate, endDate;
        while (true) {
            System.out.printf("（格式如：2007-01-01）开始时间：");
            startDate = scanner.nextLine();
            if (ParserDateUtils.validDate(startDate)) {
                break;
            } else {
                System.out.println("输入时间格式错误：" + startDate);

            }
        }
        while (true) {
            System.out.printf("（格式如：200701-01）结束时间：");
            endDate = scanner.nextLine();
            if (ParserDateUtils.validDate(endDate)) {
                break;
            } else {
                System.out.println("输入时间格式错误：" + endDate);

            }
        }

        DutyRosterApp dutyRosterApplication = new DutyRosterApp(startDate, endDate);

        int flag = 0;
        while (flag != 7) {
            String name, career, phone;

            String[] inf = new String[]{"请输入数字，选择操作：", "1.查看所有所有员工", "2.查看排班", "3.安排员工工作",
                    "4.随机安排", "5.添加员工", "6.删除员工", "7.退出"};
            for (String str : inf) {
                System.out.println(str);
            }
            flag = scanner.nextInt();
            String[] lines;
            switch (flag) {

                case 1:
                    dutyRosterApplication.lookEmployees();
                    break;
                case 2:
                    dutyRosterApplication.lookPlan();
                    break;
                case 3:
                    System.out.println("请在一行输入名字，职业，电话,工作时间，以,隔开：");
                    lines = scanner.next().split(",");
                    name = lines[0];
                    career = lines[1];
                    phone = lines[2];
                    int day = Integer.valueOf(lines[3]);
                    dutyRosterApplication.insertToDuty(new Employee(name, career, phone), day);
                    break;
                case 4:
                    dutyRosterApplication.randomAssign();
                    break;
                case 5:
                    System.out.println("请在一行输入名字，职业，电话，以,隔开：");
                    lines = scanner.next().split(",");
                    name = lines[0];
                    career = lines[1];
                    phone = lines[2];
                    dutyRosterApplication.addEmployee(new Employee(name, career, phone));
                    break;
                case 6:
                    System.out.println("请在一行输入名字，职业，电话，以,隔开：");
                    lines = scanner.next().split(",");
                    name = lines[0];
                    career = lines[1];
                    phone = lines[2];
                    dutyRosterApplication.removeEmployee(new Employee(name, career, phone));
                    break;
                case 7:
                    System.exit(0);
                    break;
            }
        }
    }

}
