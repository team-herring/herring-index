package org.herring.index.column.index.reader;

import org.herring.file.reader.FileReader;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class IndexReaderLong implements IndexReader<Long> {
    @Override
    public List<Long> load(FileReader fileReader) throws Exception {
        return fileReader.load();
    }
}
