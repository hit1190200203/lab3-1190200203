package intervalSet;

import entity.Course;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 代表某个班级的特定课表
 */
public class CourseIntervalSet extends CommonMultiIntervalSet<Course> {

    /**
     * 输出结果
     * @return s
     */
    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        Set<Course> courseSet = this.labels();
        Iterator<Course> iterator = courseSet.iterator();

        while(iterator.hasNext()){
            Course course = iterator.next();
            s.append("\n 课程："+course.getID()+"  "+course.getName()+"  （教师："+course.getTeacherName()+"  上课地点"+course.getAddress()+"）：\n");

            IntervalSet<Integer> intervalSet = this.intervals(course);
            Set<Integer> labels=intervalSet.labels();

            Iterator<Integer> iterator1 = labels.iterator();
            while (iterator1.hasNext()){
                Integer val = iterator1.next();
                try {
                    s.append("     周"+intervalSet.start(val)+"  "+ getTimeByInput((int) intervalSet.end(val))+" \n");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            s.append("\n");
        }

        return s.toString();
    }

    /**
     * 允许重叠和空白
     * @return
     */
    @Override
    public boolean checkRep() {
        return true;
    }

    @Override
    public IntervalSet<Integer> intervals(Course label) {
        return super.intervals(label);
    }


    private String getTimeByInput(int t){
        Map<Integer,String> map = new HashMap<>();
        map.put(8, "8:00-10:00");
        map.put(10, "10:00-12:00");
        map.put(13, "13:00-15:00");
        map.put(15, "15:00-17:00");
        map.put(19, "19:00-21:00");

        if(map.containsKey(t)){
            return map.get(t);
        }
        return "";
    }

    @Override
    public void empty() {
        super.empty();
    }

    @Override
    public void insert(long start, long end, Course label) {
        super.insert(start, end, label);
    }

    @Override
    public Set<Course> labels() {
        return super.labels();
    }

    @Override
    public boolean remove(Course label) {
        return super.remove(label);
    }
}
