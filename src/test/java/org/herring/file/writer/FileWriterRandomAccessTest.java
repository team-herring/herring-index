package org.herring.file.writer;

import org.herring.utils.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class FileWriterRandomAccessTest {
    private static FileWriter writer = new FileWriterRandomAccess();
    private static String directory = "test";
    private String fileName = "test";


    @Before
    public void before(){
        writer = new FileWriterRandomAccess();
        File file = new File(directory);
        FileUtils.removeDirectory(file);
    }
    @After
    public void after(){
        testClose();
    }

    @Test
    public void testCreateFile() throws Exception {
        boolean success = writer.createFile(directory, fileName);
        assertTrue(success);
    }

    @Test
    public void testAppendLine() throws Exception {
        boolean success = writer.createFile(directory, fileName);
        assertTrue(success);

        long index = writer.appendLine("Hello World..");
        assertEquals(index, 0);

        for (int i=0; i < 10; i++) {
            index = writer.appendLine("Hello World..");
        }
        assertEquals(index, 10);
    }

    @Test
    public void testClose(){
        try {
            writer.close();
        } catch (IOException e) {
            assertNull(e);
        }
    }


}
