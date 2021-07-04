package intervalSet;


import entity.Process;
import other.MyDate;

import java.util.*;

/**
 * IntervalSet的实现类，具体实现IntervalSet各种方法
 * @param <L> 泛型
 */
public class CommonIntervalSet<L> implements IntervalSet<L> {

    /**
     * 保存时间段和标签
     */
    protected List<MyDate> intervalList=new LinkedList<>();


    /**
     * 创建一个空对象
     */
    @Override
    public void empty() {
        intervalList=new LinkedList<>();
    }

    /**
     * 在当前对象中插入新的时间段和标签：
     * @param start 开始时间
     * @param end 结束时间
     * @param label 标签
     */
    @Override
    public void insert(long start, long end, L label) {
        MyDate t = new MyDate(label,start,end);
        intervalList.add(t);
    }

    /**
     *  获得当前对象中的标签集合
     * @return 返回label的set集合
     */
    @Override
    public Set<L> labels() {
        Set<L> set = new HashSet<>();
        Iterator<MyDate> iter = intervalList.iterator();
        while (iter.hasNext()) {
            MyDate myDate=iter.next();
            set.add((L) myDate.getLabel());
        }
        return set;
    }

    /**
     * 从当前对象中移除某个标签所关联的时间段
     * @param label 标签
     * @return 是否成功
     */
    @Override
    public boolean remove(L label) {

        List<MyDate> t = new LinkedList<>();

        boolean flag = false;
        for(MyDate interval : intervalList){
            if (!interval.getLabel().equals(label)){
                t.add(interval);
                flag = true;
            }
        }

        intervalList = t;

        return flag;
    }

    /**
     * 返回某个标签对应的时间段的开始时间
     * @param label 查询的标签
     * @return 返回开始值
     */
    @Override
    public long start(L label) {

        Iterator<MyDate> iterator = intervalList.iterator();

        while(iterator.hasNext()){
            MyDate interval = iterator.next();
            if(interval.getLabel().equals(label)){
                return interval.getStart();
            }
        }

        System.out.println("This label is not exit:"+label);
        return -1;
    }

    /**
     * 返回某个标签对应的时间段的结束时间
     * @param label 查询的标签
     * @return 返回结束值
     */
    @Override
    public long end(L label) throws Exception {
        Iterator<MyDate> iterator = intervalList.iterator();

        while(iterator.hasNext()){
            MyDate interval = iterator.next();
            if(interval.getLabel().equals(label)){
                return interval.getEnd();
            }
        }
        System.out.println("This label is not exit:"+label);
        return -1;
    }

    /**
     * 返回IntervalSet结果
     * @return s
     */
    @Override
    public String toString() {
        Set<L> set = this.labels();
        StringBuffer t = new StringBuffer();
        Iterator<L> iterator = set.iterator();
        while(iterator.hasNext()){
            try{
                L l = iterator.next();
                t.append(l + ":["+this.start(l)+","+this.end(l)+"] ");
            }catch (Exception e){
                e.printStackTrace();
            }
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
