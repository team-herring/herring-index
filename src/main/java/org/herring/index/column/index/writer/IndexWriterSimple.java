package org.herring.index.column.index.writer;

import org.apache.log4j.Logger;
import org.herring.file.writer.FileWriter;
import org.herring.index.column.index.Index;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class IndexWriterSimple implements IndexWriter {
    private static final Logger LOG = Logger.getLogger(IndexWriterSimple.class);
    private List<Index> keywords;
    private HashMap<String, Index> keywordMap;
    private FileWriter writer;
    private long currentIndex;


    public IndexWriterSimple(FileWriter writer) {
        this.keywordMap = new HashMap<String, Index>();
        this.writer = writer;
        LOG.info("created IndexWriterSimple");
    }

    @Override
    public boolean save(String directory, String name) throws IOException {
        boolean success = writer.createFile(directory, name);
        LOG.info("IndexWriterSimple load");
        return success;
    }

    @Override
    public long appendKeyWord(String keyword) throws Exception {
        currentIndex = writer.appendLine(keyword);
        if (!keywordMap.containsKey(keyword))
            keywordMap.put(keyword, new Index(keyword, currentIndex));
        else
            keywordMap.get(keyword).appendIndex(currentIndex);
        return currentIndex;
    }

    @Override
    public Index findIndexByKeyWord(String word) {
        return keywordMap.get(word);
    }

    @Override
    public void discard() {
        try {
            writer.close();
            writer = null;
            LOG.info("IndexWriterSimple discard");
        } catch (IOException e) {
            LOG.error(e.getStackTrace());
        }
        writer = null;
    }

    @Override
    public long size() {
        return keywordMap.size();
    }
}
