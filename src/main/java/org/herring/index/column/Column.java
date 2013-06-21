package org.herring.index.column;

import org.herring.file.writer.FileWriter;

import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public interface Column {
    void load();
    boolean create(List<String> datas) throws Exception;
    List findIndexs(String word);
    void save(FileWriter fileWriter) throws Exception;
    String getName();
    void close();
}
