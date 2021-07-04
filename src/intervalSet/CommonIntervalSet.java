package intervalSet;


import entity.Process;
import other.MyDate;

import java.util.*;

/**
 * IntervalSet��ʵ���࣬����ʵ��IntervalSet���ַ���
 * @param <L> ����
 */
public class CommonIntervalSet<L> implements IntervalSet<L> {

    /**
     * ����ʱ��κͱ�ǩ
     */
    protected List<MyDate> intervalList=new LinkedList<>();


    /**
     * ����һ���ն���
     */
    @Override
    public void empty() {
        intervalList=new LinkedList<>();
    }

    /**
     * �ڵ�ǰ�����в����µ�ʱ��κͱ�ǩ��
     * @param start ��ʼʱ��
     * @param end ����ʱ��
     * @param label ��ǩ
     */
    @Override
    public void insert(long start, long end, L label) {
        MyDate t = new MyDate(label,start,end);
        intervalList.add(t);
    }

    /**
     *  ��õ�ǰ�����еı�ǩ����
     * @return ����label��set����
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
     * �ӵ�ǰ�������Ƴ�ĳ����ǩ��������ʱ���
     * @param label ��ǩ
     * @return �Ƿ�ɹ�
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
     * ����ĳ����ǩ��Ӧ��ʱ��εĿ�ʼʱ��
     * @param label ��ѯ�ı�ǩ
     * @return ���ؿ�ʼֵ
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
     * ����ĳ����ǩ��Ӧ��ʱ��εĽ���ʱ��
     * @param label ��ѯ�ı�ǩ
     * @return ���ؽ���ֵ
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
     * ����IntervalSet���
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
