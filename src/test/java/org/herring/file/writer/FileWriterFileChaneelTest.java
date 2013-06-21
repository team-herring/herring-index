package org.herring.file.writer;

import org.herring.utils.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.ByteBuffer;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class FileWriterFileChaneelTest {
    private FileWriterFileLongChannel writer;
    private String fileName = "test";
    private static String directory = "test";
    File file = new File(directory);

    @Before
    public void testCreateFile() throws Exception {
        FileUtils.removeDirectory(file);

        writer = new FileWriterFileLongChannel();
        writer.createFile(directory, fileName);
    }

    @After
    public void testClose() throws Exception {
        writer.close();
        FileUtils.removeDirectory(file);
    }
    @Test
    public void testWrite() throws Exception {
        ByteBuffer buf = ByteBuffer.wrap("1\n".getBytes());
        System.out.println(writer.append(buf));

        ByteBuffer buf2 = ByteBuffer.wrap("0\n".getBytes());
        System.out.println(writer.append(buf2));

        writer.close();

    }

}
