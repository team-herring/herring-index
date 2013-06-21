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
    void load() throws Exception;
    void save(FileWriter fileWriter) throws Exception;
    boolean create(List<String> datas) throws Exception;
    List<Long> findIndexs(String word);
    List<String> findWords(List<Long> index)throws Exception;
    String getName();
    void close();
}
