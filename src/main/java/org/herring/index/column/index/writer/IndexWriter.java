package org.herring.index.column.index.writer;

import org.herring.index.column.index.Index;

import java.io.IOException;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface IndexWriter {
    public boolean save(String directory, String name) throws IOException;
    long appendKeyWord(String keyword) throws Exception;
    Index findIndexByKeyWord(String word);
    void discard();
    long size();
}
