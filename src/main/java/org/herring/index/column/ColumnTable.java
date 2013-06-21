package org.herring.index.column;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ColumnTable {
    private List<Column> columns;

    public ColumnTable() {
        this.columns = new ArrayList<Column>();
    }

    public void addColumn(Column column){
        columns.add(column);
    }

    public Column get(int index){
        return columns.get(index);
    }

    public List<Long> find(String word, String date) {
        if (word.equals(""))
            return null;

        Set<Long> temp = new HashSet<Long>();
        for (Column column : columns) {
            List<Long> indexs = column.findIndexs(word);

            if(indexs == null || indexs.size() == 0)
                continue;

            temp.addAll(indexs);
        }
        return new ArrayList<Long>(temp);
    }
}
