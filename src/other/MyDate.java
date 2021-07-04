package other;

import java.util.Objects;
import java.util.*;

/**
 * 时间间隔类
 * @param <L>
 */
public class MyDate<L> implements Comparable{

    /**
     * 保存开始时间和结束时间
     */
    private Map<String, Long> data;

    /**
     * 标签
     */
    private L label;

    public MyDate() {
        data = new HashMap<>();
    }

    public MyDate(L label, long start, long end) {
        this();
        this.label = label;
        this.data.put("start", start);
        this.data.put("end", end);
    }

    public L getLabel() {
        return label;
    }

    public void setLabel(L label) {
        this.label = label;
    }

    public MyDate(L label) {
        this.label = label;
    }

    public long getStart() {
        return this.data.get("start");
    }

    public void setStart(long start) {
        this.data.put("start", start);
    }

    public long getEnd() {
        return this.data.get("end");
    }

    public void setEnd(long end) {
        this.data.put("end", end);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MyDate<?> interval = (MyDate<?>) object;
        return this.data.get("start") == interval.data.get("start") && this.data.get("end") == interval.data.get("end") && Objects.equals(label, interval.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, this.data.get("start"), this.data.get("end"));
    }


    @Override
    public int compareTo(Object o) {
        MyDate myDate= (MyDate) o;
        return (int) (this.getStart() - myDate.getStart());
    }
}
