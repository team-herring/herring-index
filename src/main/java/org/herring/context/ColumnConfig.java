package org.herring.context;

/**
 * Description.
 *
 * @author Youngdeok Kim
 * @since 1.0
 */
public class ColumnConfig {
    public static final String EXTENDS_INDEX_NAME = ".index";
    public static final String EXTENDS_KEYWORD_NAME = ".keyword";
    public static final String DIRECTORY_SPACE = "/";
    public static final String DELIMITER = " ";
    public static final String LINE_SEPARATOR =	(String) java.security.AccessController.doPrivileged(
                  new sun.security.action.GetPropertyAction("line.separator"));
}
