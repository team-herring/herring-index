package org.herring.index.column.index;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class IndexLong implements Serializable, Comparable<Long>, Index<Long> {
    /**
     * index의 단어
     */
    private Long keyword;
    /**
     * keyword가 포함된 Row의 위치값
     */
    private List<Long> indexs;

    public IndexLong(Long keyword, Long index) {
        this.indexs = new ArrayList<Long>();
        this.keyword = keyword;
        this.indexs.add(index);
    }


    public void appendIndex(Long index) {
        if (!this.indexs.contains(index))
            this.indexs.add(index);
//        int i = Collections.binarySearch(indexs, index);
//             if (i < 0)
//                 this.indexs.add(index);
    }


    public Long getKeyword() {
        return keyword;
    }


    public List<Long> getIndexs() {
        return indexs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexLong index = (IndexLong) o;

        if (keyword != null ? !keyword.equals(index.keyword) : index.keyword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return keyword != null ? keyword.hashCode() : 0;
    }

    @Override
    public int compareTo(Long o) {
        return keyword.compareTo(o);
    }
}
