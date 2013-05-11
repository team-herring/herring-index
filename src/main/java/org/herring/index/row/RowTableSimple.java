package org.herring.index.row;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class RowTableSimple implements RowTable {
    private Row row;

    @Override
    public void add(Row row) {
        this.row = row;
    }

    @Override
    public Row get(Long index) {
        return row;
    }
}
