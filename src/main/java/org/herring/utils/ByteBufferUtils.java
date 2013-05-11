package org.herring.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ByteBufferUtils {
    public static ByteBuffer toBytes(Long val, ByteOrder byteOrder) {
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.order(byteOrder);
        buf.putLong(val);
        return buf;
    }

    public static long toLong(byte[] buff, ByteOrder byteOrder) {
        assert (buff.length == 8);
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.order(byteOrder);
        buf.put(buff);
        buf.flip();
        return buf.getLong();
    }

    public static ByteBuffer toBytes(String str) {
        ByteBuffer buf = ByteBuffer.wrap(str.getBytes());
        return buf;
    }

    public static String toString(byte[] buff) {
        return new String(buff);
    }

    public static int compareTo(ByteBuffer origin, ByteBuffer that) {
        int n = origin.position() + Math.min(origin.remaining(), that.remaining());
        for (int i = origin.position(), j = that.position(); i < n; i++, j++) {
            byte v1 = origin.get(i);
            byte v2 = that.get(j);
            if (v1 == v2)
                continue;
            if ((v1 != v1) && (v2 != v2))    // For float and double
                continue;
            if (v1 < v2)
                return -1;
            return +1;
        }
        return origin.remaining() - that.remaining();
    }
}
