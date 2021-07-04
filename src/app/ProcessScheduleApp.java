package app;

import entity.Process;
import intervalSet.ProcessIntervalSet;

import java.util.*;

/**
 * 进程系统管理类
 */
public class ProcessScheduleApp {

    /**
     * 时间属性
     */
    private long timeSlice;

    /**
     * ProcessIntervalSet
     */
    private ProcessIntervalSet processIntervalSet;

    /**
     * 进程队列属性
     */
    private List<Process> processList;

    /**
     * 当前时间
     */
    private long nowTime;

    /**
     * 构造函数
     *
     * @param time 进程可运行时间
     */
    public ProcessScheduleApp(long time) {
        this.nowTime = 0;
        this.timeSlice = time;
        processIntervalSet = new ProcessIntervalSet();
        processList = new ArrayList<>();
    }

    /**
     * 添加一个进程方法
     *
     * @param process process
     * @return 返回是否成功添加进程
     */
    public boolean addProcess(Process process) {
        if (processList.contains(process)) {
            System.out.println("该进程已经添加");
            return false;
        }
        processList.add(process);
        System.out.println("添加进程:" + process);
        return true;
    }

    /**
     * 删除一个进程方法
     *
     * @param process process
     * @return 返回是否成功删除进程
     */
    public boolean removeProcess(Process process) {
        if (processList.contains(process)) {
            processList.remove(process);
            System.out.println("删除成功");
            return true;
        }
        System.out.println(process + "不存在");
        return false;
    }

    private int run(int runFinished) {
        for (int i = 0; i < processList.size(); i++) {
            Process process = processList.get(i);
            if (process.getShortT() > timeSlice) {
                processIntervalSet.insert(nowTime, nowTime + timeSlice, process);
                nowTime += timeSlice;
                process.setShortT(process.getShortT() - timeSlice);
                processList.set(i, process);
            } else if (process.getShortT()>0){
                processIntervalSet.insert(nowTime, nowTime + process.getShortT(), process);
                nowTime += process.getShortT();
                process.setShortT(0);
                processList.set(i, process);
                runFinished++;
            }
        }
        return runFinished;
    }

    /**
     * 随机选择进程运行方法
     */
    public void runRandom() {
        int runFinished = 0;
        while (runFinished < processList.size()) {
            runFinished = run(runFinished);
        }
    }

    /**
     * 按最短进程优先运行方法
     */
    public void runShortFirst() {
        Collections.sort(processList, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return (int) (o1.getShortT() - o2.getShortT());
            }
        });
        int runFinished = 0;
        while (runFinished < processList.size()) {
            runFinished = run(runFinished);
            Collections.sort(processList, new Comparator<Process>() {
                @Override
                public int compare(Process o1, Process o2) {
                    return (int) (o1.getShortT() - o2.getShortT());
                }
            });
        }
    }

    /**
     * 查看所有进程方法
     */
    public void lookProcess() {
        System.out.println("\n----------------进程-------------");
        Iterator<Process> iter = processList.iterator();
        while (iter.hasNext()) {
            Process process = iter.next();
            System.out.println(process.toString());
        }
        System.out.println();
    }

    /**
     * 查看CPU运行记录方法
     */
    public void lookSchedule() {
        System.out.println("\n----------进程执行状态------------");
        System.out.println("进程ID  进程名  运行时间段");
        System.out.println(processIntervalSet.toString());
    }


    /**
     * 主类
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("进程管理系统");
        int time = 2;
        System.out.print("请输入数字表示时间片：");
        time = scanner.nextInt();
        ProcessScheduleApp processScheduleApplication = new ProcessScheduleApp(time);
        int flag = 0;
        while (flag != 7) {
            System.out.println();
            String[] inf = new String[]{"1. 按最短时间优先运行", "2. 查看所有进程", "3. 查看CPU运行记录",
                    "4. 添加进程", "5. 删除进程", "6. 随机选择运行", "7. 退出"};
            for (String str : inf) {
                System.out.println(str);
            }
            flag = scanner.nextInt();
            String ID, name;
            long shortT, longT;
            String[] lines;
            switch (flag) {
                case 1:
                    processScheduleApplication.runShortFirst();
                    break;
                case 2:
                    processScheduleApplication.lookProcess();
                    break;
                case 3:
                    processScheduleApplication.lookSchedule();
                    break;
                case 4:
                    System.out.println("请在按行依次输入进程ID，名字，最短执行时间，最长执行时间：");
                    ID = scanner.next();
                    name = scanner.next();
                    shortT = scanner.nextInt();
                    longT = scanner.nextInt();
                    Process process = new Process(ID, name, shortT, longT);
                    processScheduleApplication.addProcess(process);
                    break;
                case 5:
                    System.out.println("请在一行输入进程ID，名字，最短执行时间，最长执行时间，以，隔开：");
                    lines = scanner.next().split(",");
                    ID = lines[0];
                    name = lines[1];
                    shortT = Long.valueOf(lines[2]);
                    longT = Long.valueOf(lines[3]);
                    processScheduleApplication.removeProcess(new Process(ID, name, shortT, longT));
                    break;
                case 6:
                    processScheduleApplication.runRandom();
                    break;
                case 7:
                    System.exit(0);
                    break;
            }

        }
    }

}
