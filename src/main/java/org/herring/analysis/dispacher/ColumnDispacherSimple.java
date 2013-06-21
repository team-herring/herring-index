package org.herring.analysis.dispacher;

import org.herring.file.writer.FileWriterFileLongChannel;
import org.herring.index.column.ColumnString;
import org.herring.index.column.index.writer.IndexWriter;
import org.herring.index.column.index.writer.IndexWriterMemoryList;
import org.herring.index.column.keyword.KeywordTable;
import org.herring.index.column.keyword.KeywordTableMemoryList;
import org.herring.context.ColumnContext;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ColumnDispacherSimple implements ColumnDispacher<String> {
    @Override
    public void dispach(List<String> datas, ColumnContext context) {


        //send column
    }

    private ColumnString createColumn(String date, String name, List<String> datas) throws Exception {
        IndexWriter indexWriter = new IndexWriterMemoryList(new FileWriterFileLongChannel());
        KeywordTable keywordTable = new KeywordTableMemoryList();
        ColumnString column = new ColumnString(date, name, indexWriter, keywordTable);
        column.create(datas);
        column.save(new FileWriterFileLongChannel());
        return column;
    }
}
