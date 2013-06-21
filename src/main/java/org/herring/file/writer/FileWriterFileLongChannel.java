package org.herring.file.writer;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class FileWriterFileLongChannel implements FileWriter<Long>{
    private static final Logger LOG = Logger.getLogger(FileWriterFileLongChannel.class);
    private File file;
    private RandomAccessFile raf;
    private long position;
    private long appendRowSize = 0;
    private FileChannel channel;

    public FileWriterFileLongChannel() {
    }

    @Override
    public boolean createFile(String directory, String fileName) throws IOException {
        if ("".equals(directory))
            throw new IOException("Directory path is whitespace");
        if ("".equals(directory))
            throw new IOException("fileName is whitespace");

        File directoryFile = new File(directory);
        File file = new File(directory + "/" + fileName);

        if (!directoryFile.exists()){
            directoryFile.mkdir();
        }

        if (file.exists())
            throw new IOException("File is exist");
        boolean success = file.createNewFile();

        if (success) {
            this.file = file;
        }
        return success;
    }

    @Override
    public long appendLine(Long values) throws IOException {
        return append(ByteBuffer.allocate(8).putLong(values));
    }

    @Override
    public long append(ByteBuffer buf) throws IOException {
        if (raf == null){
            raf = new RandomAccessFile(this.file, "rw");
            channel = raf.getChannel();
        }
        buf.flip();
        channel.write(buf);
        return appendRowSize++;
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
