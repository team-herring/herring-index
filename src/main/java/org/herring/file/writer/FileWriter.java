package org.herring.file.writer;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: 영덕
 * Date: 13. 4. 21
 * Time: 오후 11:35
 * To change this template use File | Settings | File Templates.
 */
public interface FileWriter<T> {
    public boolean createFile(String directory, String fileName) throws IOException;
    public long appendLine(T value) throws IOException;
    public long append(ByteBuffer value) throws IOException;
    void close() throws IOException;
}
