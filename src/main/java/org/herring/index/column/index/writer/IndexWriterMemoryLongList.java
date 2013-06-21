package org.herring.index.column.index.writer;

import org.apache.log4j.Logger;
import org.herring.context.ColumnConfig;
import org.herring.file.writer.FileWriter;
import org.herring.index.column.index.Index;
import org.herring.index.column.index.IndexLong;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class IndexWriterMemoryLongList implements IndexWriter<Long> {
    private static final Logger LOG = Logger.getLogger(IndexWriterMemoryLongList.class);
    private List<IndexLong> indexs;
    private FileWriter<Long> writer;
    private AtomicLong currentIndex;

    public IndexWriterMemoryLongList(FileWriter<Long> writer) {
        this.currentIndex = new AtomicLong();
        this.indexs = new ArrayList<IndexLong>();
        this.writer = writer;
        LOG.info("created IndexWriterMemoryList");
    }

    public void load(List<Long> values) {
        for (int i = 0; i < values.size(); i++) {
            Index index = findKeyword(values.get(i));
            if (index == null)
                indexs.add(new IndexLong(values.get(i), (long)i));
            else
                index.appendIndex((long) i);
        }
    }

    @Override
    public boolean save(String directory, String name) throws IOException {
        boolean success = writer.createFile(directory, name + ColumnConfig.EXTENDS_INDEX_NAME);
        LOG.info("IndexWriterMemoryList load");
        return success;
    }

    @Override
    public long appendKeyWord(Long word) throws Exception {
        long lineNumber = writer.appendLine(word);

        Index index = findKeyword(word);
        if (index == null)
            indexs.add(new IndexLong(word, lineNumber));
        else
            index.appendIndex(lineNumber);
        return currentIndex.get();
    }

    private Index findKeyword(Long word) {
        if (indexs.size() <= word)
            return null;
        return indexs.get(word.intValue());
    }

    @Override
    public Index findIndexByKeyWord(Long word) {
        return findKeyword(word);
    }

    @Override
    public void discard() {
        try {
            writer.close();
            writer = null;
            LOG.info("IndexWriterMemoryList discard");
        } catch (IOException e) {
            LOG.error(e.getStackTrace());
        }
        writer = null;
    }

    @Override
    public long size() {
        return indexs.size();
    }
}