package org.herring.index.column.index.writer;

import org.herring.index.column.index.Index;

import java.io.IOException;
import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface IndexWriter<T> {
    void load(List<T> values);
    public boolean save(String directory, String name) throws IOException;
    long appendKeyWord(T keyword) throws Exception;
    Index findIndexByKeyWord(T word);
    void discard();
    long size();
}
