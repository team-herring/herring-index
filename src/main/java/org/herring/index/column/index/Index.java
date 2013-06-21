package org.herring.index.column.index;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface Index<T> {
    void appendIndex(Long index);
    T getKeyword();
    List<Long> getIndexs();
}
