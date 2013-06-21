package org.herring.index;

import org.herring.file.writer.FileWriterFileLongChannel;
import org.herring.file.writer.FileWriterWritableByteChannel;
import org.herring.index.column.Column;
import org.herring.index.column.ColumnLong;
import org.herring.index.column.index.writer.IndexWriter;
import org.herring.index.column.index.writer.IndexWriterMemoryLongList;
import org.herring.index.column.keyword.KeywordTable;
import org.herring.index.column.keyword.KeywordTableMemoryList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 로컬 모드에서 Herring-index를 사용하기 위한 클래스.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class HerringColumnIndex {
    private String date;
    private List<Column> columns;

    public HerringColumnIndex(String date) {
        this.date = date;
        this.columns = new ArrayList<Column>();

    }

    public void addList(List<String> list, String columnName) throws Exception {
        IndexWriter indexWriter = new IndexWriterMemoryLongList(new FileWriterFileLongChannel());
        KeywordTable keywordTable = new KeywordTableMemoryList();
        Column column = new ColumnLong(date, columnName, indexWriter, keywordTable);
        column.create(list);
        column.save(new FileWriterWritableByteChannel());
        column.close();

        columns.add(column);
    }

    public Map<String, List<String>> find(String word, String columnName) throws Exception {
        Column column = findAndCreate(columnName);
        List<Long> indexs = column.findIndexs(word);


        Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
        List<String> columnNames = getColumnNames();
        for (String name : columnNames) {
            Column otherColumn = findAndCreate(name);
            List<String> results = otherColumn.findWords(indexs);
            resultMap.put(columnName, column.findWords(indexs));
        }
        return resultMap;
    }

    public Column findAndCreate(String columnName) throws Exception {
        for (Column column : columns) {
            if (column.getName().equals(columnName))
                return column;
        }
        IndexWriter indexWriter = new IndexWriterMemoryLongList(new FileWriterFileLongChannel());
        KeywordTable keywordTable = new KeywordTableMemoryList();
        Column column = new ColumnLong(date, columnName, indexWriter, keywordTable);
        column.load();
        column.close();
        columns.add(column);
        return column;
    }

    public List<String> getColumnNames() {
        List<String> results = new ArrayList<String>();
        File file = new File(date);
        String[] strs = file.list();
        for (String str : strs) {
            String[] names = str.split(".");
            if (names[1].equals("index")){
                results.add(names[0]);
            }
        }
        return results;
    }
}
