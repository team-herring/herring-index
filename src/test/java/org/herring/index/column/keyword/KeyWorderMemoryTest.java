package org.herring.index.column.keyword;

import org.herring.file.writer.FileWriter;
import org.herring.file.writer.FileWriterWritableByteChannel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class KeyWorderMemoryTest {
    private KeywordTable keyWorder;
    String keyword = "Hello";
    String keyword2 = "Hello2";
    String keyword3 = "Hello3";
    String directory = "20130430";
    long firstIndex = 0;
    long secondIndex = 1;
    long thirdIndex = 2;
    String name = "test";

    @Before
    public void testBuild() throws Exception {
        this.keyWorder = new KeywordTableMemoryList();
    }

    @After
    public void testDiscard() throws Exception {
        this.keyWorder.discard();
    }

    @Test
    public void testAppendKeyword() throws Exception {
        this.keyWorder.appendKeyword(keyword);
        String result = this.keyWorder.get(firstIndex);

        assertNotNull(keyword);
        assertEquals(keyword, result);
    }

    @Test
    public void testAppedkeywords() {
        this.keyWorder.appendKeyword(keyword);
        this.keyWorder.appendKeyword(keyword2);
        this.keyWorder.appendKeyword(keyword3);

        String result = this.keyWorder.get(firstIndex);
        assertNotNull(keyword);
        assertEquals(keyword, result);

        result = this.keyWorder.get(secondIndex);
        assertNotNull(keyword2);
        assertEquals(keyword2, result);

        result = this.keyWorder.get(thirdIndex);
        assertNotNull(keyword3);
        assertEquals(keyword3, result);
    }

    @Test
    public void testAppedSameKeyword() {
        this.keyWorder.appendKeyword(keyword);
        this.keyWorder.appendKeyword(keyword);


        String result = this.keyWorder.get(keyWorder.get(keyword));
        assertEquals(keyword, result);

        result = this.keyWorder.get(secondIndex);
        assertNull(result);
    }

    @Test
    public void testGet() throws Exception {
        this.keyWorder.appendKeyword(keyword);
        String result = this.keyWorder.get(firstIndex);

        assertNotNull(keyword);
        assertEquals(keyword, result);
    }

    @Test
    public void dtestGetLong() {
        this.keyWorder.appendKeyword(keyword);
        Long result = this.keyWorder.get(keyword);
    }

    @Test
    public void testSave() throws Exception {
        FileWriter fileWriter = mock(FileWriterWritableByteChannel.class);

        this.keyWorder.appendKeyword(keyword);
        this.keyWorder.appendKeyword(keyword2);

        when(fileWriter.appendLine(keyword)).thenReturn(firstIndex);
        when(fileWriter.appendLine(keyword)).thenReturn(secondIndex);

        this.keyWorder.save(fileWriter, directory, name);

        assertEquals(keyWorder.get(firstIndex), keyword);
        assertEquals(keyWorder.get(secondIndex), keyword2);
    }
}
