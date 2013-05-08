package org.herring.index.column;

import org.herring.index.column.index.IndexTable;
import org.herring.index.column.index.IndexTableMemoryList;
import org.herring.index.column.keyword.KeywordTable;
import org.herring.index.column.keyword.KeywordTableMemoryList;
import org.herring.file.writer.FileWriterFileChannel;
import org.herring.utils.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class CoumnLogTest {
    @Test
    public void test() throws Exception {
        Scanner scanner = new Scanner(new FileInputStream("logs/ex130415.log"));

        FileUtils.removeDirectory(new File("20130430"));

        ArrayList<String> date = new ArrayList<String>();
        ArrayList<String> time = new ArrayList<String>();
        ArrayList<String> ip = new ArrayList<String>();
        ArrayList<String> method = new ArrayList<String>();

        while(scanner.hasNext()){
            String row = scanner.nextLine();
            String[] coulumns = row.split(" ");

            if (coulumns.length < 5)
                continue;
            date.add(coulumns[0]);
            time.add(coulumns[1]);
            ip.add(coulumns[2]);
            method.add(coulumns[3]);
        }
        scanner.close();

        HashMap<String, Column> map = new HashMap<String, Column>();
        map.put("time", createColumn("20130430", "time", time));
        map.put("ip", createColumn("20130430", "ip", ip));
        map.put("date", createColumn("20130430", "date", date));
        map.put("method", createColumn("20130430", "method", method));


        Column column = map.get("time");
        List<Long> indexs = column.findIndexs("00:00:50");
        for (Long index : indexs) {
            System.out.println(index);
        }

//        FileReader reader = new FileReaderFileChannel(new File("logs/ex130415.log"));
//        for (Long index : indexs) {
//            String result = reader.findByIndex(index);
//            System.out.println(result);
//        }
    }

    private Column createColumn(String date, String name, List<String> datas) throws Exception {
        IndexTable indexTable = new IndexTableMemoryList(new FileWriterFileChannel());
        KeywordTable keywordTable = new KeywordTableMemoryList();
        Column column = new Column(date, name, indexTable, keywordTable);
        column.create(datas);
        column.save(new FileWriterFileChannel());
        return column;
    }
}
