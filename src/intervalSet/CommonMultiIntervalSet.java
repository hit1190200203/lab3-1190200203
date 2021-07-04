package intervalSet;

import other.MyDate;

import java.util.*;

/**
 *  ����һ�� mutable �� ADT��������һ����ʱ�����Ϸֲ��ġ�ʱ��Ρ���interval����ÿ��ʱ��θ���һ���ض��ı�ǩ���ұ�ǩ���ظ���ÿ����ǩ�������ʱ��μ���
 *  MultiIntervalSetʵ����
 * @param <L> ����
 */
public class CommonMultiIntervalSet<L> implements MultiIntervalSet<L> {

    /**
     * ����ʱ��κͱ�ǩ
     */
    protected Map<L,List<MyDate<L>>> intervalMap=new HashMap<>();


    /**
     * ����һ���ն���
     */
    @Override
    public void empty() {
        intervalMap.clear();
        intervalMap = new HashMap<>();
    }

    /**
     * �ڵ�ǰ�����в����µ�ʱ��κͱ�ǩ
     * @param start ��ʼʱ��
     * @param end ����ʱ��
     * @param label ��ǩ
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
     * ��õ�ǰ�����еı�ǩ����
     * @return  ���ر�ǩ����
     */
    @Override
    public Set<L> labels() {
        Set<L> set = intervalMap.keySet();
        return set;
    }

    /**
     * �ӵ�ǰ�������Ƴ�ĳ����ǩ������������ʱ���
     * @param label ��ǩ
     * @return �Ƿ�ɹ�
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
     * �ӵ�ǰ�����л�ȡ��ĳ����ǩ������������ʱ���
     * @param label ��ǩ
     * @return ʱ��ΰ���ʼʱ���С����Ĵ�������
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
     * ����MultiIntervalSet���
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
     * �����޿հס���Լ������
     * �Ƿ�����ͬ�� interval ֮�����ص���
     * �Ƿ���������Ե�ʱ��Ρ�
     * �������� checkRep()�м��Լ��
     * @return �ǻ��
     */
    public boolean checkRep(){
        return false;
    }
}
