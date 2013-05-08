package org.herring.index.row;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class RowTableTest {
    private RowTable rowTable;

    @Before
    public void setUp() throws Exception {
        rowTable = new RowTableList();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGet(){
        long index = 0;
        Row row = rowTable.get(index);
        TestCase.assertNotNull(row);
    }
}
