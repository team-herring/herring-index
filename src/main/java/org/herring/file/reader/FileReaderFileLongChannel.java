package org.herring.file.reader;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class FileReaderFileLongChannel implements FileReader<Long> {
    private static final Logger LOG = Logger.getLogger(FileReaderFileLongChannel.class);
    private File file;
    private RandomAccessFile raf;
    private FileChannel channel;


    public FileReaderFileLongChannel(File file) throws FileNotFoundException {
        this.file = file;
        raf = new RandomAccessFile(file, "r");
        channel = raf.getChannel();
    }

    @Override
    public List<Long> load() throws Exception {
        List<Long> results = new ArrayList<Long>();
        while(true){
            if (raf.getFilePointer() == raf.length())
                break;
            Long data = raf.readLong();
            results.add(data);
        }
        return results;
    }

    @Override
    public List<Long> findByIndex(List<Long> indexs) throws Exception {
        List<Long> results = new ArrayList<Long>();
        for (Long index : indexs) {
            raf.seek(index * 8);
            results.add(raf.readLong());
        }
        return results;
    }

    @Override
    public void close() {
        try {
            if (raf != null)
                raf.close();
            if (raf != null)
                channel.close();
        } catch (IOException e) {
            try {
                if (raf != null)
                    raf.close();
                if (raf != null)
                    channel.close();
            } catch (IOException e1) {
                LOG.error(e1.getStackTrace());
            }
        }
    }
}
