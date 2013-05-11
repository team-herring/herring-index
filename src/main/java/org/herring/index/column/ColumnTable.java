package org.herring.index.column;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ColumnTable {
    private List<Column> columns;

    public void addColumn(Column column){
        columns.add(column);
    }

    public Column get(int index){
        return columns.get(index);
    }

    public void destroy(){
        for (Column column : columns) {
            column.close();
        }
    }
}
