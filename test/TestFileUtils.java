package test;


import org.junit.Test;
import utils.FileUtils;

import java.io.IOException;

/**
 * 测试读取文件类
 */
public class TestFileUtils {

    /**
     * 测试文件读取
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        for (int i = 1; i <= 8; i++) {
            String name = "testFile/test" + i + ".txt";
            FileUtils file = new FileUtils(name);
            System.out.println(file.getEmployees());
            System.out.println(file.getRosters());
            System.out.println(file.getPeriods());
        }
    }
}
