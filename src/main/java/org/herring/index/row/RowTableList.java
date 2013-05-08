package org.herring.index.row;

import java.util.ArrayList;
import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class RowTableList implements RowTable {
    private List<Row> rows;

    public RowTableList() {
        this.rows = new ArrayList<Row>();
    }

    @Override
    public void add(Row row) {
        rows.add(row);
    }

    @Override
    public Row get(Long index) {
        int i = index.intValue();
        if(rows.size()-1 < i)
            return null;
        return rows.get(i);
    }
}
