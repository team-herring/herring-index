package org.herring.index.column.index;

import java.io.IOException;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface IndexTable {
    public boolean load(String directory, String name) throws IOException;
    public boolean save(String directory, String name) throws IOException;
    long appendKeyWord(String keyword) throws Exception;
    Index findIndexByKeyWord(String word);
    void discard();
    long size();
}
