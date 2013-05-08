package org.herring.index.row;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface RowTable {
    void add(Row row);
    Row get(Long index);
}
