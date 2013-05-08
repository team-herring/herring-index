package org.herring;

import org.herring.index.HerringColumnIndex;
import org.herring.index.column.ColumnTable;
import org.herring.index.row.Row;
import org.herring.index.row.RowTable;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class HerringColumnIndexTest {
    private HerringColumnIndex herringColumnIndex;
    private String name = "test";
    private RowTable rowTable;
    private ColumnTable columnTable;


    @Before
    public void before(){
        herringColumnIndex=new HerringColumnIndex(name);
    }

    @Test
    public void testAddFile(){
        herringColumnIndex.addFile(new File(""));
    }

    @Test
    public void testAddList(){
        List<String> list = new ArrayList<String>();
        herringColumnIndex.addList(list);
    }

    @Test
    public void find(){
        String word = "";
        List<Long> indexs = herringColumnIndex.find(word);
    }

    @Test
    public void merge(){
        herringColumnIndex.merge();
    }

    @Test
    public void findRows(){
        String word = "";
        List<Row> rows = herringColumnIndex.findRows(word);
    }
}
