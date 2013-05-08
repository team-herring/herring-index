package org.herring.analysis.dispacher;

import org.herring.context.ColumnContext;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface ColumnDispacher<T> {
    void dispach (List<T> datas, ColumnContext context);
}
