package org.herring.utils;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ByteBufferUtilsTest {
    @Test
    public void testLong() throws Exception {
        long val = Long.valueOf(123456);

        ByteBuffer bytes = ByteBufferUtils.toBytes(val, ByteOrder.BIG_ENDIAN);
        long result = ByteBufferUtils.toLong(bytes.array(), ByteOrder.BIG_ENDIAN);

        assertEquals(val, result);
    }

    @Test
    public void testCompareTo(){
        long val = Long.valueOf(123456);
        long val2 = Long.valueOf(-234);
        String str ="123456";

        ByteBuffer bytes = ByteBufferUtils.toBytes(val, ByteOrder.BIG_ENDIAN);
        ByteBuffer bytes2 = ByteBufferUtils.toBytes(val2, ByteOrder.BIG_ENDIAN);

        System.out.println(Arrays.toString(bytes.array()));
        System.out.println(Arrays.toString(str.getBytes()));

    }
}
