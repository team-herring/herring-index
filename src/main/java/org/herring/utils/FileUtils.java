package org.herring.utils;

import java.io.File;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class FileUtils {
    public static void removeDirectory(File directory) {
        if (!directory.exists())
            return;
        if (!directory.isDirectory())
            return;

        File[] files = directory.listFiles();
        if (files.length > 0)
            for (File childFile : files)
                childFile.delete();
        directory.delete();
    }
}
