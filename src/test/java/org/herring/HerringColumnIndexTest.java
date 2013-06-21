package org.herring;

import org.herring.index.HerringColumnIndex;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class HerringColumnIndexTest {
    private HerringColumnIndex herringColumnIndex;
    private String date = "20130430";

    @Before
    public void before() {
        herringColumnIndex = new HerringColumnIndex(date);
    }

//    @Test
//    public void testAddList() throws Exception {
//        Scanner scanner = new Scanner(new FileInputStream("logs/ex130415.log"));
//
//        FileUtils.removeDirectory(new File(date));
//
//        ArrayList<String> date = new ArrayList<String>();
//        while (scanner.hasNext()) {
//            String row = scanner.nextLine();
//            date.add(row.split(" ")[1]);
//        }
//        herringColumnIndex.addList(date, "time");
//    }

    @Test
    public void find() throws Exception {
//        testAddList();
        long start3 = System.currentTimeMillis();
        HerringColumnIndex herringColumnIndex = new HerringColumnIndex(date);
        Map<String,List<String>> result = herringColumnIndex.find("00:00:50", "time");
        long end3 = System.currentTimeMillis();
        System.out.println(TimeUnit.MILLISECONDS.toSeconds(end3 - start3));
//        List<String> time = result.get("time");
//        for (int i = 0; i < time.size(); i++) {
//            System.out.println(time.get(i) +"  "+result.get("url").get(i));
//        }
    }
}
