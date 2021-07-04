package intervalSet;

import java.util.Set;

/**
 * ����һ�� mutable �� ADT��������һ����ʱ�����Ϸֲ��ġ�ʱ��Ρ���interval����ÿ��ʱ��θ���һ���ض��ı�ǩ���ұ�ǩ���ظ���
 * ÿ����ǩ�������ʱ��μ���
 * @param <L>
 */
public interface MultiIntervalSet<L> {
    /**
     * ����һ���ն���
     */
    void empty();

    /**
     * �ڵ�ǰ�����в����µ�ʱ��κͱ�ǩ
     * @param start ��ʼʱ��
     * @param end ����ʱ��
     * @param label ��ǩ
     */
    void insert(long start, long end, L label);


    /**
     * ��õ�ǰ�����еı�ǩ����
     * @return  ���ر�ǩ����
     */
    Set<L> labels();

    /**
     * �ӵ�ǰ�������Ƴ�ĳ����ǩ������������ʱ���
     * @param label ��ǩ
     * @return �Ƿ�ɹ�
     */
    boolean remove(L label);

    /**
     * �ӵ�ǰ�����л�ȡ��ĳ����ǩ������������ʱ���
     * @param label ��ǩ
     * @return ʱ��ΰ���ʼʱ���С����Ĵ�������
     */
    IntervalSet<Integer> intervals(L label);

}
