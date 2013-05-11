package org.herring.index.column.index;

import org.herring.file.writer.FileWriter;
import org.herring.file.writer.FileWriterWritableByteChannel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class IndexTableSimpleTest {
    private IndexTable indexTable;
    private FileWriter writer;

    @Before
    public void setUp() throws Exception {
        writer = mock(FileWriterWritableByteChannel.class);
        indexTable = new IndexTableSimple(writer);
    }

    @After
    public void tearDown() throws Exception {
        indexTable.discard();
    }

    @Test
    public void testLoad() throws Exception {
        String directory = "201304222020";
        String name = "test";
        when(writer.createFile(directory, name)).thenReturn(true);
        boolean success = indexTable.save(directory, name);
        assertTrue(success);
    }

    @Test
    public void appendKeyWord() throws Exception {
        String key = "3";
        when(writer.appendLine(key)).thenReturn((long) 0);

        long beforeSize = indexTable.size();
        long indexLine = indexTable.appendKeyWord(key);
        long afterSize = indexTable.size();

        assertThat(indexLine, is((long) 0));
        assertThat(afterSize, greaterThan(beforeSize));
    }

    @Test
    public void testFindIndexByKeyWord() throws Exception {
        String key = "3";
        long location = 0;
        when(writer.appendLine(key)).thenReturn(location);
        indexTable.appendKeyWord(key);

        Index index = indexTable.findIndexByKeyWord(key);

        assertEquals(key, index.getKeyword());
        assertEquals(1, index.getIndexs().size());
        assertEquals(location, (long) index.getIndexs().get(0));
    }


}
