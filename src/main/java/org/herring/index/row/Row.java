package org.herring.index.row;

import java.nio.ByteBuffer;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class Row {
    private long startOffset;
    private long endOffset;

    public Row(long startOffset, long endOffset) {
        this.startOffset = startOffset;
        this.endOffset = endOffset;
    }

    public long getStartOffset() {
        return startOffset;
    }

    public long getEndOffset() {
        return endOffset;
    }

    public ByteBuffer getByteBufferRow(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.putLong(startOffset);
        byteBuffer.putLong(endOffset);
        byteBuffer.flip();
        return byteBuffer;
    }


    @Override
    public String toString() {
        return "Row{" +
                "startOffset=" + startOffset +
                ", endOffset=" + endOffset +
                '}';
    }
}
