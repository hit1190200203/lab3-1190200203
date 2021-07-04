package intervalSet;

import java.util.Set;

/**
 *
 * ����һ�� mutable �� ADT��������һ����ʱ�����Ϸֲ��ġ�ʱ��Ρ���interval����ÿ��ʱ��θ���һ���ض��ı�ǩ���ұ�ǩ���ظ���
 * @param <L> ADT
 */
public interface IntervalSet<L> {
    /**
     * ����һ���ն���
     */
    void  empty();

    /**
     * �ڵ�ǰ�����в����µ�ʱ��κͱ�ǩ��
     * @param start ��ʼʱ��
     * @param end ����ʱ��
     * @param label ��ǩ
     */
    void insert(long start, long end, L label);

    /**
     *  ��õ�ǰ�����еı�ǩ����
     * @return ����label��set����
     */
    Set<L> labels();

    /**
     * �ӵ�ǰ�������Ƴ�ĳ����ǩ��������ʱ���
     * @param label ��ǩ
     * @return �Ƿ�ɹ�
     */
    boolean remove(L label);

    /**
     * ����ĳ����ǩ��Ӧ��ʱ��εĿ�ʼʱ��
     * @param label ��ѯ�ı�ǩ
     * @return ���ؿ�ʼֵ
     */
    long start(L label) throws Exception;

    /**
     * ����ĳ����ǩ��Ӧ��ʱ��εĽ���ʱ��
     * @param label ��ѯ�ı�ǩ
     * @return ���ؽ���ֵ
     */
    long end(L label) throws Exception;

}
