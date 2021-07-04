package test;

import entity.Employee;
import app.DutyRosterApp;
import org.junit.Test;

import java.text.ParseException;

/**
 * 测试DutyRosterApp类
 */
public class testDutyRosterApp {

    /**
     * 测试test1，手动添加
     */
    @Test
    public void test1() throws ParseException {
        DutyRosterApp dutyRosterApplication =new DutyRosterApp("2021-10-01","2021-12-01");
        Employee emp1 = new Employee();
        emp1.setName("张三");
        emp1.setCareer("科长");
        emp1.setPhone("18568984536");

        Employee emp2 = new Employee();
        emp2.setName("李四");
        emp2.setCareer("厅长");
        emp2.setPhone("1815478986");

        Employee emp3 = new Employee();
        emp3.setName("王五");
        emp3.setCareer("处长");
        emp3.setPhone("1815868986");

        dutyRosterApplication.addEmployee(emp1);
        dutyRosterApplication.addEmployee(emp2);
        dutyRosterApplication.addEmployee(emp3);

        dutyRosterApplication.lookEmployees();
        dutyRosterApplication.insertToDuty(emp1, 3);
        dutyRosterApplication.insertToDuty(emp2, 8);
        dutyRosterApplication.insertToDuty(emp3, 4);

        dutyRosterApplication.lookPlan();
    }

    /**
     * 测试test2，自动测试
     */
    @Test
    public void test2() throws ParseException {
        DutyRosterApp dutyRosterApplication =new DutyRosterApp("2021-10-01","2021-12-01");
        Employee emp1 = new Employee();
        emp1.setName("张三");
        emp1.setCareer("科长");
        emp1.setPhone("18568984536");

        Employee emp2 = new Employee();
        emp2.setName("李四");
        emp2.setCareer("厅长");
        emp2.setPhone("1815478986");

        Employee emp3 = new Employee();
        emp3.setName("王五");
        emp3.setCareer("处长");
        emp3.setPhone("1815868986");

        dutyRosterApplication.addEmployee(emp1);
        dutyRosterApplication.addEmployee(emp2);
        dutyRosterApplication.addEmployee(emp3);

        dutyRosterApplication.lookEmployees();

        dutyRosterApplication.randomAssign();
        dutyRosterApplication.lookPlan();
    }

}
