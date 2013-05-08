package org.herring.file.reader;

/**
 * Created with IntelliJ IDEA.
 * User: 영덕
 * Date: 13. 4. 21
 * Time: 오후 11:33
 * To change this template use File | Settings | File Templates.
 */
public interface FileReader {
    String findByIndex(long index) throws Exception;
    void close();
}
