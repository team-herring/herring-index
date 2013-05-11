package org.herring.index.column.index.reader;

import java.io.IOException;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface IndexReader {
    public boolean load(String directory, String name) throws IOException;
}
