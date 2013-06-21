package org.herring.index.column;

import org.herring.file.writer.FileWriter;
import org.herring.index.column.index.Index;
import org.herring.index.column.index.writer.IndexWriter;
import org.herring.index.column.keyword.KeywordTable;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ColumnLong implements Column {
    private String directory;
    private String name;
    private IndexWriter<Long> indexWriter;
    private KeywordTable keywordTable;

    public ColumnLong(String directory, String name, IndexWriter<Long> indexWriter, KeywordTable keywordTable) {
        this.directory = directory;
        this.name = name;
        this.indexWriter = indexWriter;
        this.keywordTable = keywordTable;
    }

    @Override
    public boolean create(List<String> datas) throws Exception {
        boolean isSuccess = indexWriter.save(directory, name);
        if( !isSuccess)
            return false;
        for (String data : datas) {
            Long keyword = keywordTable.appendKeyword(data);
            indexWriter.appendKeyWord(keyword);
        }
        return true;
    }

    @Override
    public List<Long> findIndexs(String word) {
        Long wordMeaning= keywordTable.get(word);
        Index index = indexWriter.findIndexByKeyWord(wordMeaning);
        if (index == null)
            return null;
        return index.getIndexs();
    }

    @Override
    public void save(FileWriter fileWriter) throws Exception {
        keywordTable.save(fileWriter, directory, name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void close(){
        keywordTable.discard();
        indexWriter.discard();
    }
}
