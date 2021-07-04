package entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * 进程类
 */
public class Process {

    /**
     * 进程 ID
     */
    private String processID;

    /**
     * 进程名称
     */
    private String processName;

    /**
     * 最短执行时间
     */
    private long processShortT;

    /**
     * 最长执行时间
     */
    private long processLongT;

    public Process() {
    }

    public Process(String id, String name, long shortT, long longT) {
        processID = id;
        processName = name;
        processShortT = shortT;
        processLongT = longT;
    }

    public String getID() {
        return processID;
    }

    public void setID(String ID) {
        this.processID = ID;
    }

    public String getName() {
        return processName;
    }

    public void setName(String name) {
        this.processName = name;
    }

    public long getShortT() {
        return processShortT;
    }

    public void setShortT(long shortT) {
        this.processShortT = shortT;
    }

    public long getLongT() {
        return processLongT;
    }

    public void setLongT(long longT) {
        this.processLongT = longT;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return  processID.equals(process.processID) && processName.equals(process.processName);
    }


    @Override
    public String toString() {
        return "[id='" + processID + '\'' +
                ", name='" + processName + '\'' +
                ", shortT=" + processShortT +
                ", longT=" + processLongT+"]";
    }
}
