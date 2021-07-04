package intervalSet;

import java.util.Set;

/**
 *
 * 这是一个 mutable 的 ADT，描述了一组在时间轴上分布的“时间段”（interval），每个时间段附着一个特定的标签，且标签不重复。
 * @param <L> ADT
 */
public interface IntervalSet<L> {
    /**
     * 创建一个空对象
     */
    void  empty();

    /**
     * 在当前对象中插入新的时间段和标签：
     * @param start 开始时间
     * @param end 结束时间
     * @param label 标签
     */
    void insert(long start, long end, L label);

    /**
     *  获得当前对象中的标签集合
     * @return 返回label的set集合
     */
    Set<L> labels();

    /**
     * 从当前对象中移除某个标签所关联的时间段
     * @param label 标签
     * @return 是否成功
     */
    boolean remove(L label);

    /**
     * 返回某个标签对应的时间段的开始时间
     * @param label 查询的标签
     * @return 返回开始值
     */
    long start(L label) throws Exception;

    /**
     * 返回某个标签对应的时间段的结束时间
     * @param label 查询的标签
     * @return 返回结束值
     */
    long end(L label) throws Exception;

}
