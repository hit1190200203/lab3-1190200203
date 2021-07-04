package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件读取封装对象Roster
 */
public class Roster {
    private String name;
    private String[] dates;

    public Roster(String name) {
        this();
        this.name = name;
    }

    public Roster() {
        dates=new String[2];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDate(String[] d){
        dates[0] = d[0];
        dates[1] = d[1];
    }

    public String[] getDates() {
        return dates;
    }

    @Override
    public String toString() {
        return name+"  "+dates;
    }
}
