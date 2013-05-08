package org.herring.analysis.dispacher;

import org.herring.index.column.Column;
import org.herring.index.column.index.IndexTable;
import org.herring.index.column.index.IndexTableMemoryList;
import org.herring.index.column.keyword.KeywordTable;
import org.herring.index.column.keyword.KeywordTableMemoryList;
import org.herring.context.ColumnContext;
import org.herring.file.writer.FileWriterFileChannel;

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

    private Column createColumn(String date, String name, List<String> datas) throws Exception {
        IndexTable indexTable = new IndexTableMemoryList(new FileWriterFileChannel());
        KeywordTable keywordTable = new KeywordTableMemoryList();
        Column column = new Column(date, name, indexTable, keywordTable);
        column.create(datas);
        column.save(new FileWriterFileChannel());
        return column;
    }
}
