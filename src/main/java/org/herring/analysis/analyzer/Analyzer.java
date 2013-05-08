package org.herring.analysis.analyzer;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface Analyzer <T>{
    List<T> analysis (T data);
}
