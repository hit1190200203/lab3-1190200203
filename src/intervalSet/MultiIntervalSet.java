package intervalSet;

import java.util.Set;

/**
 * 这是一个 mutable 的 ADT，描述了一组在时间轴上分布的“时间段”（interval），每个时间段附着一个特定的标签，且标签不重复。
 * 每个标签包含多个时间段集合
 * @param <L>
 */
public interface MultiIntervalSet<L> {
    /**
     * 创建一个空对象
     */
    void empty();

    /**
     * 在当前对象中插入新的时间段和标签
     * @param start 开始时间
     * @param end 结束时间
     * @param label 标签
     */
    void insert(long start, long end, L label);


    /**
     * 获得当前对象中的标签集合
     * @return  返回标签集合
     */
    Set<L> labels();

    /**
     * 从当前对象中移除某个标签所关联的所有时间段
     * @param label 标签
     * @return 是否成功
     */
    boolean remove(L label);

    /**
     * 从当前对象中获取与某个标签所关联的所有时间段
     * @param label 标签
     * @return 时间段按开始时间从小到大的次序排列
     */
    IntervalSet<Integer> intervals(L label);

}
