package org.herring.file.writer;

import org.apache.log4j.Logger;
import org.herring.context.ColumnConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * Description.
 *
 * @author  Youngdeok Kim
 * @since 1.0
 */
public class FileWriterWritableByteChannel implements FileWriter<String> {
    private static final Logger LOG = Logger.getLogger(FileWriterWritableByteChannel.class);
    private File file;
    private long appendRowSize = 0;
    private WritableByteChannel channel;

    public FileWriterWritableByteChannel() {
    }

    @Override
    public boolean createFile(String directory, String fileName) throws IOException {
        if ("".equals(directory))
            throw new IOException("Directory path is whitespace");
        if ("".equals(directory))
            throw new IOException("fileName is whitespace");

        File directoryFile = new File(directory);
        File file = new File(directory + "/" + fileName);

        if ( !directoryFile.exists()) {
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
    public long append(ByteBuffer value) throws IOException {
        if (channel == null)
            channel = Channels.newChannel(new FileOutputStream(this.file, true));

        channel.write(value);
        return appendRowSize++;
    }

    @Override
    public void close() {
        try {
            if (channel != null)
                channel.close();
        } catch (IOException e) {
            if (channel != null)
                try {
                    channel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        } finally {
            if (channel != null)
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
