package intervalSet;

import entity.Employee;
import other.MyDate;

import java.util.*;

/**
 * 代表一个排班表
 */
public class DutyIntervalSet extends CommonIntervalSet<Employee>{

    /**
     * 按值班管理表格式输出
     * @return
     */
    @Override
    public String toString() {
        List<MyDate<Employee>> intervals = new LinkedList<>();
        Iterator<Employee> iterator = this.labels().iterator();
        while(iterator.hasNext()){
            Employee employee = iterator.next();
            MyDate<Employee> employeeInterval = null;
            try {
                employeeInterval = new MyDate<>(employee,this.start(employee),this.end(employee));
            } catch (Exception e) {
                e.printStackTrace();
            }
            intervals.add(employeeInterval);
        }

        Collections.sort(intervals, (o1, o2) -> {
                return (int) (o1.getStart()-o2.getStart());
        });
        StringBuffer s = new StringBuffer();
        Iterator<MyDate<Employee>> iterator1 = intervals.iterator();
        while(iterator1.hasNext()){
            MyDate interval = iterator1.next();
            s.append(interval.toString());
        }

        return s.toString();
    }

    @Override
    public void empty() {
        super.empty();
    }

    @Override
    public void insert(long start, long end, Employee label) {
        super.insert(start, end, label);
    }

    @Override
    public Set<Employee> labels() {
        return super.labels();
    }

    @Override
    public boolean remove(Employee label) {
        return super.remove(label);
    }

    @Override
    public long start(Employee label) {
        return super.start(label);
    }

    @Override
    public long end(Employee label) throws Exception {
        return super.end(label);
    }

    @Override
    public boolean checkRep() {
        return super.checkRep();
    }
}
