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
public class FileReaderFileStringChannel implements FileReader<String> {
    private static final Logger LOG = Logger.getLogger(FileReaderFileStringChannel.class);
    private File file;
    private RandomAccessFile raf;
    private FileChannel channel;


    public FileReaderFileStringChannel(File file) throws FileNotFoundException {
        this.file = file;
        raf = new RandomAccessFile(file, "r");
        channel = raf.getChannel();
    }

    @Override
    public List<String> load() throws Exception {
        List<String> results = new ArrayList<String>();
        while(true){
            String line = raf.readLine();
            if(line == null)
                break;
            results.add(line);
        }
        return results;
    }

    @Override
    public List<String> findByIndex(List<Long> indexs) throws Exception {
        List<String> results = new ArrayList<String>();
        for (Long index : indexs) {
            raf.seek(index*8);
            results.add(raf.readLine());
        }
        return results;
    }

    @Override
    public void close() {
        try {
            if(raf != null)
                raf.close();
            if(raf != null)
                channel.close();
        } catch (IOException e) {
            try {
                if(raf != null)
                    raf.close();
                if(raf != null)
                    channel.close();
            } catch (IOException e1) {
                LOG.error(e1.getStackTrace());
            }
        }
    }
}
