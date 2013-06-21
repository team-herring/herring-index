package org.herring.index.column;

import org.herring.context.ColumnConfig;
import org.herring.file.reader.FileReaderFileLongChannel;
import org.herring.file.reader.FileReaderFileStringChannel;
import org.herring.file.writer.FileWriter;
import org.herring.index.column.index.Index;
import org.herring.index.column.index.reader.IndexReaderLong;
import org.herring.index.column.index.writer.IndexWriter;
import org.herring.index.column.keyword.KeywordTable;

import java.io.File;
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
    public void load() throws Exception {
        String path = directory+"/"+ name;
        this.keywordTable.load(new FileReaderFileStringChannel(new File(path + ColumnConfig.EXTENDS_KEYWORD_NAME)));

        IndexReaderLong ir = new IndexReaderLong();
        List<Long> values = ir.load(new FileReaderFileLongChannel(new File(path + ColumnConfig.EXTENDS_INDEX_NAME)));
        this.indexWriter.load(values);
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
    public List<String> findWords(List<Long> index) throws Exception {
        String path = directory+"/"+ name;
        if (this.keywordTable == null){
            this.keywordTable.load(new FileReaderFileStringChannel(new File(path + ColumnConfig.EXTENDS_KEYWORD_NAME)));
        }
        FileReaderFileLongChannel fr = new FileReaderFileLongChannel(new File(path + ColumnConfig.EXTENDS_INDEX_NAME));
        List<Long> keywords = fr.findByIndex(index);

        return this.keywordTable.get(keywords);
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
