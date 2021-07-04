package intervalSet;

import other.MyDate;

import java.util.*;

/**
 *  这是一个 mutable 的 ADT，描述了一组在时间轴上分布的“时间段”（interval），每个时间段附着一个特定的标签，且标签不重复。每个标签包含多个时间段集合
 *  MultiIntervalSet实现类
 * @param <L> 泛型
 */
public class CommonMultiIntervalSet<L> implements MultiIntervalSet<L> {

    /**
     * 保存时间段和标签
     */
    protected Map<L,List<MyDate<L>>> intervalMap=new HashMap<>();


    /**
     * 创建一个空对象
     */
    @Override
    public void empty() {
        intervalMap.clear();
        intervalMap = new HashMap<>();
    }

    /**
     * 在当前对象中插入新的时间段和标签
     * @param start 开始时间
     * @param end 结束时间
     * @param label 标签
     */
    @Override
    public void insert(long start, long end, L label) {
        List<MyDate<L>> intervalList;
        intervalList = intervalMap.getOrDefault(label, new LinkedList<>());
        MyDate<L> interval = new MyDate<>(label,start,end);
        intervalList.add(interval);
        intervalMap.put(label,intervalList);
    }

    /**
     * 获得当前对象中的标签集合
     * @return  返回标签集合
     */
    @Override
    public Set<L> labels() {
        Set<L> set = intervalMap.keySet();
        return set;
    }

    /**
     * 从当前对象中移除某个标签所关联的所有时间段
     * @param label 标签
     * @return 是否成功
     */
    @Override
    public boolean remove(L label) {
        for(L l : intervalMap.keySet()){
            if(l.equals(label)){
                intervalMap.remove(label);
                return true;
            }
        }
        return false;
    }

    /**
     * 从当前对象中获取与某个标签所关联的所有时间段
     * @param label 标签
     * @return 时间段按开始时间从小到大的次序排列
     */
    @Override
    public IntervalSet<Integer> intervals(L label) {
        List<MyDate<L>> intervalList=intervalMap.get(label);
        Collections.sort(intervalList, (o1, o2)-> {
            return (int) ((o1.getEnd()-o1.getStart())-(o2.getEnd()-o2.getStart()));
        });

        IntervalSet<Integer> intervalSet = new CommonIntervalSet<>();
        int i = 0;
        for(int j = 0; j < intervalList.size(); ++j){
            intervalSet.insert(intervalList.get(j).getStart(),intervalList.get(j).getEnd(), i);
            i++;
        }

        return intervalSet;
    }

    /**
     * 返回MultiIntervalSet结果
     * @return s
     */
    @Override
    public String toString() {
        Set<L> set = this.labels();
        StringBuffer t = new StringBuffer();
        Iterator<L> iterator = set.iterator();
        while(iterator.hasNext()){
            L l = iterator.next();
            IntervalSet<Integer> interval=this.intervals(l);
            t.append(l+"{");
            try {
                t.append(interval.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            t.append("}");
        }

        return t.toString();
    }

    /**
     * “有无空白”的约束定义
     * 是否允许不同的 interval 之间有重叠。
     * 是否包含周期性的时间段。
     * 出来并在 checkRep()中加以检查
     * @return 是或否
     */
    public boolean checkRep(){
        return false;
    }
}
