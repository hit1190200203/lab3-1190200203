package test;

import org.junit.Assert;
import org.junit.Test;
import utils.ParserDateUtils;

/**
 * 测试时间工具类
 */
public class testDateAPI {

    /**测试方法test1
     * 测试正确
     */
    @Test
    public void test1(){
        String date="2022-06-07";
        Assert.assertTrue(ParserDateUtils.validDate(date));
        Assert.assertEquals(ParserDateUtils.getYear(date),2022);
        Assert.assertEquals(ParserDateUtils.getMonth(date),6);
        Assert.assertEquals(ParserDateUtils.getDay(date),7);
    }

    /**测试方法test2
     * 测试错误日期格式
     */
    @Test
    public void test2(){
        String date="201222455-454555-441215657";
        Assert.assertFalse(ParserDateUtils.validDate(date));
    }
}
