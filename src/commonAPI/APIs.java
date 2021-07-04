package commonAPI;

import other.MyDate;
import intervalSet.IntervalSet;
import intervalSet.MultiIntervalSet;

import java.util.*;

/**
 * 可复用 API 设计与开发
 */
public class APIs<L> {

    /**
     * 得到二者的整体相似度
     * @param ms1 MultiIntervalSet
     * @param ms2 MultiIntervalSet
     * @return 相似度
     */
    public  double Similarity(MultiIntervalSet<L> ms1, MultiIntervalSet<L> ms2) throws Exception {
        Set<L> lSet1 = ms1.labels();
        Set<L> lSet2 = ms2.labels();
        //重复的总长度
        long totalLength = 0;
        //刻度标的最短开始
        long start = Long.MAX_VALUE;
        //刻度标的最长结束
        long end = Long.MIN_VALUE;

        Iterator<L> iterator = lSet1.iterator();
        while(iterator.hasNext()){
            L l = iterator.next();
            if (lSet2.contains(l)){
                IntervalSet<Integer> set1 = ms1.intervals(l);
                IntervalSet<Integer> set2 = ms2.intervals(l);

                Set<Integer> l1 = set1.labels();
                Set<Integer> l2 = set2.labels();

                Iterator<Integer> l1Iterator = l1.iterator();
                while(l1Iterator.hasNext()){
                    Integer l1Val = l1Iterator.next();
                    for (int l2Val : l2){
                        long start1 = set1.start(l1Val);
                        long end1 = set1.end(l1Val);
                        long start2 = set2.start(l2Val);
                        long end2 = set2.end(l2Val);

                        long[] starts = new long[3];
                        long[] ends = new long[3];
                        starts[0] = start;
                        starts[1] = start1;
                        starts[2] = start2;
                        Arrays.sort(starts);
                        start = starts[0];

                        ends[0] = end;
                        ends[1] = end1;
                        ends[2] = end2;
                        Arrays.sort(ends);
                        end = ends[2];

                        if (start1 <= start2 && start2 <= end1){
                            if (end1 <= end2){
                                totalLength +=  end1 - start2;
                            }else {
                                totalLength += end2 - start2;
                            }
                        }else if (start1 >= start2 && start1 <= end2){
                            if (end1 <= end2){
                                totalLength += end1 - start1;
                            }else {
                                totalLength += end2 - start1;
                            }
                        }
                    }
                }
            }
        }

        return totalLength / (double)(end - start);
    }




    /**
     * 计算冲突方法
     *
     * @param set MultiIntervalSet
     * @return 冲突比例
     * @throws Exception 异常捕获
     */
    public double calcConflictRatio(MultiIntervalSet<L> set) throws Exception {
        Set<L> labels=set.labels();

        List<MyDate<L>> myDateArrayList=new ArrayList<>();
        for (L l:labels){
            IntervalSet<Integer> intervalSet=set.intervals(l);
            for (Integer t:intervalSet.labels()){
                myDateArrayList.add(new MyDate<>(l,intervalSet.start(t),intervalSet.end(t)));
            }
        }
        double sum=0;
        int size=myDateArrayList.size();
        for (int i=0;i<size-1;i++){
            for (int j=i+1;j<size;j++){
                MyDate lMyDate=myDateArrayList.get(i);
                MyDate lMyDate1=myDateArrayList.get(j);
                if (lMyDate.getStart()==lMyDate1.getStart()&&lMyDate.getEnd()==lMyDate1.getEnd()){
                    sum++;
                }
            }
        }
        return sum/size;
    }

    /**
     *  计算一个 IntervalSet<L>对象中的空闲时
     *
     * @param set IntervalSet
     * @return 空闲比例
     * @throws Exception 异常捕获
     */
    public double calcFreeTimeRatio(IntervalSet<L> set) throws Exception {
        Set<L> labelSet = set.labels();
        long start = Long.MAX_VALUE;
        long end = 0;
        double busy = 0;
        Iterator<L> iterator = labelSet.iterator();

        while(iterator.hasNext()){
            L label = iterator.next();
            long tStart = set.start(label);
            long tEnd = set.end(label);
            start = Math.min(start, tStart);
            end = Math.max(end, tEnd);
            busy += tEnd - tStart;
        }

        return busy / (end - start);
    }

    /**
     *  计算一个 MultiIntervalSet<L>对象中的空闲时
     *
     * @param set MultiIntervalSet
     * @return 空闲比例
     * @throws Exception 异常捕获
     */
    public double calcFreeTimeRatio(MultiIntervalSet<L> set) throws Exception {
        Set<L> labelSet = set.labels();
        double sum = 0;
        int n = 0;
        Iterator<L> iterator = labelSet.iterator();
        while(iterator.hasNext()){
            L label = iterator.next();
            IntervalSet<Integer> intervalSet = set.intervals(label);
            sum += calcFreeTimeRatio((IntervalSet<L>) intervalSet);
            n++;
        }
        return sum / n;
    }
}
