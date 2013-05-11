package org.herring.index;

import org.herring.file.writer.FileWriterFileChannel;
import org.herring.file.writer.FileWriterWritableByteChannel;
import org.herring.index.column.Column;
import org.herring.index.column.ColumnTable;
import org.herring.index.column.index.writer.IndexWriterMemoryList;
import org.herring.index.column.keyword.KeywordTableMemoryList;
import org.herring.index.row.Row;
import org.herring.index.row.RowTable;
import org.herring.index.row.RowTableList;
import org.herring.index.row.RowTableSimple;

import java.io.File;
import java.util.List;

/**
 * 로컬 모드에서 Herring-index를 사용하기 위한 클래스.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class HerringColumnIndex {
    private String name;
    private ColumnTable columnTable;
    private RowTable rowTable;

    public HerringColumnIndex(String name) {
        this.name = name;
        this.rowTable = new RowTableList();
        this.columnTable = new ColumnTable();
        this.rowTable = new RowTableSimple();
    }

    public void addFile(File file) {
    }

    public void addList(List<String> list, String columnName) throws Exception {
        Column column = new Column(name, columnName, new IndexWriterMemoryList(new FileWriterFileChannel()), new KeywordTableMemoryList());
        column.create(list);
        column.save(new FileWriterWritableByteChannel());
        columnTable.addColumn(column);
    }

    public List<Long> find(String word) {
        return columnTable.find(word);
    }

    public void merge() {


    }
    public List<Row> findRows(String word) {
        return null;
    }
}
