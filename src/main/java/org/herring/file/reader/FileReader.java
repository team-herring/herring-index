package org.herring.file.reader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 영덕
 * Date: 13. 4. 21
 * Time: 오후 11:33
 * To change this template use File | Settings | File Templates.
 */
public interface FileReader<T> {
    List<T> load() throws Exception;
    List<T> findByIndex(List<Long> index) throws Exception;
    void close();
}
