package org.herring.index.row;

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

    @Override
    public String toString() {
        return "Row{" +
                "startOffset=" + startOffset +
                ", endOffset=" + endOffset +
                '}';
    }
}
