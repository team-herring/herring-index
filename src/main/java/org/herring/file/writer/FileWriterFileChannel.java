package org.herring.file.writer;

import org.apache.log4j.Logger;
import org.herring.context.ColumnConfig;

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
public class FileWriterFileChannel implements FileWriter{
    private static final Logger LOG = Logger.getLogger(FileWriterFileChannel.class);
    private File file;
    private RandomAccessFile raf;
    private long position;
    private long appendRowSize = 1;
    private FileChannel channel;

    public FileWriterFileChannel() {
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
    public long appendLine(String values) throws IOException {
        values = values + ColumnConfig.LINE_SEPARATOR;
        return append(ByteBuffer.wrap(values.getBytes()));
    }

    @Override
    public long append(ByteBuffer buf) throws IOException {
        if (raf == null){
            raf = new RandomAccessFile(this.file, "rw");
            channel = raf.getChannel();
        }
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
