package org.herring.index.column.index;

import org.apache.log4j.Logger;
import org.herring.context.ColumnConfig;
import org.herring.file.writer.FileWriter;

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
public class IndexTableMemoryList implements IndexTable {
    private static final Logger LOG = Logger.getLogger(IndexTableMemoryList.class);
    private List<Index> indexs;
    private FileWriter writer;
    private AtomicLong currentIndex;

    public IndexTableMemoryList(FileWriter writer) {
        this.currentIndex = new AtomicLong();
        this.indexs = new ArrayList<Index>();
        this.writer = writer;
        LOG.info("created IndexTableMemoryList");
    }

    @Override
    public boolean load(String directory, String name) throws IOException {
        return false;
    }

    @Override
    public boolean save(String directory, String name) throws IOException {
        boolean success = writer.createFile(directory, name + ColumnConfig.EXTENDS_INDEX_NAME);
        LOG.info("IndexTableMemoryList load");
        return success;
    }

    @Override
    public long appendKeyWord(String word) throws Exception {
        writer.appendLine(word);

        Index index = findKeyword(word);
        if (index == null)
            indexs.add(new Index(word, currentIndex.incrementAndGet()));
        else
            index.appendIndex(currentIndex.incrementAndGet());
        return currentIndex.get();
    }

    private Index findKeyword(String word) {
        for (int i = 0; i < indexs.size(); i++) {
            Index index = indexs.get(i);

            if (index.getKeyword().equals(word))
                return index;
        }
        return null;
    }

    @Override
    public Index findIndexByKeyWord(String word) {
        return findKeyword(word);
    }

    @Override
    public void discard() {
        try {
            writer.close();
            writer = null;
            LOG.info("IndexTableMemoryList discard");
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