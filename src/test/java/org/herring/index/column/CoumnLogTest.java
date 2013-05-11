package org.herring.index.column;

import org.herring.file.writer.FileWriterFileChannel;
import org.herring.index.column.index.writer.IndexWriter;
import org.herring.index.column.index.writer.IndexWriterMemoryList;
import org.herring.index.column.keyword.KeywordTable;
import org.herring.index.column.keyword.KeywordTableMemoryList;
import org.herring.utils.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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
        ArrayList<String> c3 = new ArrayList<String>();
        ArrayList<String> ip = new ArrayList<String>();
        ArrayList<String> method = new ArrayList<String>();

        ArrayList<String> url = new ArrayList<String>();
        ArrayList<String> c6 = new ArrayList<String>();
        ArrayList<String> port = new ArrayList<String>();
        ArrayList<String> c8 = new ArrayList<String>();
        ArrayList<String> serverip = new ArrayList<String>();

        ArrayList<String> client = new ArrayList<String>();
        ArrayList<String> state = new ArrayList<String>();
        ArrayList<String> c13 = new ArrayList<String>();
        ArrayList<String> c14 = new ArrayList<String>();

        while(scanner.hasNext()){
            String row = scanner.nextLine();
            String[] coulumns = row.split(" ");

            if (coulumns.length != 14)
                continue;
            date.add(coulumns[0]);
            time.add(coulumns[1]);
            c3.add(coulumns[2]);
            ip.add(coulumns[3]);
            method.add(coulumns[4]);

            url.add(coulumns[5]);
            c6.add(coulumns[6]);
            port.add(coulumns[7]);
            c8.add(coulumns[8]);
            serverip.add(coulumns[9]);

            client.add(coulumns[10]);
            state.add(coulumns[11]);
            c13.add(coulumns[12]);
            c14.add(coulumns[13]);
        }
        scanner.close();

        createColumn("20130430", "date", date);
        createColumn("20130430", "time", time);
        createColumn("20130430", "c3", c3);
        createColumn("20130430", "ip", ip);
        createColumn("20130430", "method", method);

        createColumn("20130430", "url", url);
        createColumn("20130430", "c6", c6);
        createColumn("20130430", "port", port);
        createColumn("20130430", "c8", c8);
        createColumn("20130430", "serverip", serverip);

        createColumn("20130430", "client", client);
        createColumn("20130430", "state", state);
        createColumn("20130430", "c13", c13);
        createColumn("20130430", "c14", c14);


//        HashMap<String, Column> map = new HashMap<String, Column>();
//        map.put("time", createColumn("20130430", "time", time));
//        map.put("ip", createColumn("20130430", "ip", ip));
//        map.put("date", createColumn("20130430", "date", date));
//        map.put("method", createColumn("20130430", "method", method));


//        Column column = map.get("time");
//        List<Long> indexs = column.findIndexs("00:00:50");
//        for (Long index : indexs) {
//            System.out.println(index);
//        }

//        FileReader reader = new FileReaderFileChannel(new File("logs/ex130415.log"));
//        for (Long index : indexs) {
//            String result = reader.findByIndex(index);
//            System.out.println(result);
//        }
    }

    private Column createColumn(String date, String name, List<String> datas) throws Exception {
        IndexWriter indexWriter = new IndexWriterMemoryList(new FileWriterFileChannel());
        KeywordTable keywordTable = new KeywordTableMemoryList();
        Column column = new Column(date, name, indexWriter, keywordTable);
        column.create(datas);
        column.save(new FileWriterFileChannel());
        return column;
    }
}
