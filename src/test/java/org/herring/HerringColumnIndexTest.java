package org.herring;

import org.herring.index.HerringColumnIndex;
import org.herring.index.column.ColumnTable;
import org.herring.index.row.RowTable;
import org.herring.utils.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

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
    public void before() {
        herringColumnIndex = new HerringColumnIndex(name);
    }

    @Test
    public void testAddFile() {
    }

    @Test
    public void testAddList() throws Exception {
        Scanner scanner = new Scanner(new FileInputStream("logs/ex130415.log"));

        FileUtils.removeDirectory(new File(name));

        ArrayList<String> date = new ArrayList<String>();
        while (scanner.hasNext()) {
            String row = scanner.nextLine();
            date.add(row.split(" ")[1]);
        }
        herringColumnIndex.addList(date, "time");
    }

    @Test
    public void find() throws Exception {
        testAddList();

//        String word = "00:00:50";
//        List<Long> indexs = herringColumnIndex.find(word);
//        assertNotNull(indexs);
//        for (Long index : indexs) {
//            System.out.println(index);
//        }
    }
}
