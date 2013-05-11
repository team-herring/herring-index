package org.herring.index.column;

import org.herring.index.column.index.Index;
import org.herring.index.column.index.writer.IndexWriter;
import org.herring.index.column.index.writer.IndexWriterMemoryList;
import org.herring.index.column.keyword.KeywordTableMemoryList;
import org.herring.index.column.keyword.KeywordTable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ColumnTest {
    private Column column;
    private List<String> datas;
    private String directory = "test";
    private String name = "test";
    private IndexWriter indexWriter;
    private KeywordTable keywordTable;

    @Before
    public void before(){
        datas = new ArrayList<String>();
        datas.add("GET");
        datas.add("POST");
        indexWriter = mock(IndexWriterMemoryList.class);
        keywordTable = mock(KeywordTableMemoryList.class);
        column = new Column(directory, name, indexWriter, keywordTable);
    }

    @Test
    public void testCreate() throws Exception {
        when(indexWriter.save(directory, name)).thenReturn(true);

        boolean isSuccess = column.create(datas);
        assertTrue(isSuccess);
    }

    @Test
    public void testFindIndexs() throws Exception {
        String keyword = "GET";

        when(indexWriter.save(directory, name)).thenReturn(true);
        column.create(datas);

        Index index = new Index(keyword, (long)0);
        when(keywordTable.get(keyword)).thenReturn((long) 0);
        when(indexWriter.findIndexByKeyWord(""+0)).thenReturn(index);

        List<Long> indexs = column.findIndexs(keyword);

        assertNotNull(indexs);
        assertEquals(indexs.get(0), Long.valueOf(0));
    }

    @Test
    public void testSave() throws Exception {

    }
}
