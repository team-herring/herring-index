package org.herring.index.column.index.writer;

import org.apache.log4j.Logger;
import org.herring.file.writer.FileWriter;
import org.herring.index.column.index.IndexString;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class IndexWriterSimple implements IndexWriter<String> {
    private static final Logger LOG = Logger.getLogger(IndexWriterSimple.class);
    private List<IndexString> keywords;
    private HashMap<String, IndexString> keywordMap;
    private FileWriter writer;
    private long currentIndex;


    public IndexWriterSimple(FileWriter writer) {
        this.keywordMap = new HashMap<String, IndexString>();
        this.writer = writer;
        LOG.info("created IndexWriterSimple");
    }

    @Override
    public void load(List<String> values) {

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
            keywordMap.put(keyword, new IndexString(keyword, currentIndex));
        else
            keywordMap.get(keyword).appendIndex(currentIndex);
        return currentIndex;
    }

    @Override
    public IndexString findIndexByKeyWord(String word) {
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
