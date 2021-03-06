package org.herring.index.column.index.reader;

import org.herring.file.reader.FileReader;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface IndexReader<T> {
    public List<T> load(FileReader fileReader) throws Exception;
}
