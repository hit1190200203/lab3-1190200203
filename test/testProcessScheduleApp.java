package test;

import entity.Process;
import app.ProcessScheduleApp;
import org.junit.Test;

/**
 * 测试ProcessScheduleApp
 */
public class testProcessScheduleApp {

    /**
     * 测试方法test1
     *   测试1
     */
    @Test
    public void test1(){
        ProcessScheduleApp processScheduleApplication =new ProcessScheduleApp(10);
        Process process1 = new Process();
        process1.setID("1");
        process1.setName("pro1");
        process1.setShortT(3);
        process1.setLongT(8);

        Process process2 = new Process();
        process2.setID("2");
        process2.setName("pro2");
        process2.setShortT(4);
        process2.setLongT(7);

        Process process3 = new Process();
        process3.setID("3");
        process3.setName("pro3");
        process3.setShortT(1);
        process3.setLongT(1);

        Process process4 = new Process();
        process4.setID("4");
        process4.setName("pro4");
        process4.setShortT(9);
        process4.setLongT(6);

        processScheduleApplication.addProcess(process1);
        processScheduleApplication.addProcess(process2);
        processScheduleApplication.addProcess(process3);
        processScheduleApplication.addProcess(process4);


        processScheduleApplication.lookProcess();

        processScheduleApplication.runRandom();

        processScheduleApplication.lookSchedule();
    }

    /**
     * 测试方法test2
     * 测试2
     */
    @Test
    public void test2(){
        ProcessScheduleApp processScheduleApplication =new ProcessScheduleApp(3);

        Process process1 = new Process();
        process1.setID("1");
        process1.setName("pro1");
        process1.setShortT(3);
        process1.setLongT(8);

        Process process2 = new Process();
        process2.setID("2");
        process2.setName("pro2");
        process2.setShortT(4);
        process2.setLongT(7);

        Process process3 = new Process();
        process3.setID("3");
        process3.setName("pro3");
        process3.setShortT(1);
        process3.setLongT(1);

        Process process4 = new Process();
        process4.setID("4");
        process4.setName("pro4");
        process4.setShortT(9);
        process4.setLongT(6);

        processScheduleApplication.addProcess(process1);
        processScheduleApplication.addProcess(process2);
        processScheduleApplication.addProcess(process3);
        processScheduleApplication.addProcess(process4);

        processScheduleApplication.lookProcess();

        processScheduleApplication.runShortFirst();

        processScheduleApplication.lookSchedule();
    }

}
