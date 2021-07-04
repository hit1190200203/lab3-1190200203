package intervalSet;

import entity.Process;

import java.util.Iterator;
import java.util.Set;

/**
 * 代表一个操作系统对进程的调度记录
 */
public class ProcessIntervalSet extends CommonMultiIntervalSet<Process>{

    /**
     * 返回输出结果
     * @return s
     */
    @Override
    public String toString() {
        Set<Process> processSet = this.labels();
        StringBuffer s = new StringBuffer();
        Iterator<Process> iterator = processSet.iterator();
        while(iterator.hasNext()){
            Process process = iterator.next();
            IntervalSet<Integer> set=this.intervals(process);
            s.append(process.getID()+"  "+process.getName()+"  { ");
            Set<Integer> iSet=set.labels();
            for (Integer i:iSet){
                try {
                    s.append("["+ set.start(i)+" , "+set.end(i)+"]  ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            s.append("} \n");

        }

        return s.toString();
    }

    /**
     * process应用允许空白
     * @return true
     */
    @Override
    public boolean checkRep() {
        return true;
    }

    @Override
    public void empty() {
        super.empty();
    }

    @Override
    public void insert(long start, long end, Process label) {
        super.insert(start, end, label);
    }

    @Override
    public Set<Process> labels() {
        return super.labels();
    }

    @Override
    public boolean remove(Process label) {
        return super.remove(label);
    }

    @Override
    public IntervalSet<Integer> intervals(Process label) {
        return super.intervals(label);
    }
}
