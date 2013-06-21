package org.herring.file.reader;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class FileReaderFileLongChannel implements FileReader {
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
    public String findByIndex(long index) throws IOException {
        raf.seek(index);
        return raf.readLine();
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
