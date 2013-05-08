package org.herring.index;

import org.herring.index.column.ColumnTable;
import org.herring.index.row.Row;
import org.herring.index.row.RowTable;
import org.herring.index.row.RowTableList;

import java.io.File;
import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class HerringColumnIndex {
    private String name;
    private RowTable rowTable;
    private ColumnTable columnTable;

    public HerringColumnIndex(String name) {
        this.name = name;
        this.rowTable = new RowTableList();
        this.columnTable = new ColumnTable();
    }

    public void addFile(File file) {
    }

    public void addList(List<String> list) {
        
    }

    public List<Long> find(String word) {
        return null;
    }

    public void merge() {


    }
    public List<Row> findRows(String word) {
        return null;
    }
}
