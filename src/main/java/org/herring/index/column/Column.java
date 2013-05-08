package org.herring.index.column;

import org.herring.index.column.index.Index;
import org.herring.index.column.index.IndexTable;
import org.herring.index.column.keyword.KeywordTable;
import org.herring.file.writer.FileWriter;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class Column {
    private String directory;
    private String name;
    private IndexTable indexTable;
    private KeywordTable keywordTable;

    public Column(String directory, String name, IndexTable indexTable, KeywordTable keywordTable) {
        this.directory = directory;
        this.name = name;
        this.indexTable = indexTable;
        this.keywordTable = keywordTable;
    }

    public boolean create(List<String> datas) throws Exception {
        boolean isSuccess = indexTable.save(directory, name);
        if( !isSuccess)
            return false;
        for (String data : datas) {
            Long keyword = keywordTable.appendKeyword(data);
            indexTable.appendKeyWord(String.valueOf(keyword));
        }
        return true;
    }

    public List<Long> findIndexs(String word) {
        Long wordMeaning= keywordTable.get(word);

        Index index = indexTable.findIndexByKeyWord(String.valueOf(wordMeaning));
        if (index == null)
            return null;
        return index.getIndexs();
    }

    public void save(FileWriter fileWriter) throws Exception {
        keywordTable.save(fileWriter, directory, name);
    }

    public String getName() {
        return name;
    }

    public void close(){
        keywordTable.discard();
        indexTable.discard();
    }
}
