package cn.linjk.jimapp_core;

import org.junit.Test;

import cn.linjk.jimapp_core.utils.StringUtils;
import cn.linjk.jimapp_core.utils.TimeUtils;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testTimeUtils() {
        int curDayOfMonth = TimeUtils.curDayOfMonth();
        int curMonth      = TimeUtils.curMonth();

        System.out.println("Today is: " + curMonth + "month" + curDayOfMonth + "day");
    }

    @Test
    public void testStringUtils() {
        byte[] temp = StringUtils.HexStringToByteArray("68123456789012680102");
        for (byte tmp : temp) {
            System.out.println(String.format("%02X", tmp));
        }
    }
}