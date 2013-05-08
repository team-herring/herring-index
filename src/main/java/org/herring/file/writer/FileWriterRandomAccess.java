package org.herring.file.writer;

import org.apache.log4j.Logger;
import org.herring.context.ColumnConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: 영덕
 * Date: 13. 4. 21
 * Time: 오후 11:54
 * To change this template use File | Settings | File Templates.
 */
public class FileWriterRandomAccess implements FileWriter {
    private static final Logger LOG = Logger.getLogger(FileWriterRandomAccess.class);
    private File file;
    private long appendRowSize = 0;
    private BufferedWriter writer;

    public FileWriterRandomAccess() {
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
        if (writer == null)
            this.writer = new BufferedWriter(new java.io.FileWriter(this.file, true));
        writer.write(value.toString());
        writer.flush();
        return appendRowSize++;
    }

    @Override
    public void close() {
        try {
            if (writer != null)
                writer.close();
        } catch (IOException e) {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        } finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
