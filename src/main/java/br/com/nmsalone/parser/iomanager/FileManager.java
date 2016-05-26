package br.com.nmsalone.parser.iomanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load resources from sand box
 */
public class FileManager {

    /**
     * Load html content file
     *
     * @return html file
     * @throws InvalidFileException
     */
    public InputStream loaGameResultFile()  {
        InputStream fileLoaded = null;
        try {
            fileLoaded = loadResourceAsStream("loteria/D_MEGA.HTM");
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }
        return fileLoaded;
    }

    private InputStream loadResourceAsStream(final String resourcePath) throws InvalidFileException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final InputStream stream = classLoader.getResourceAsStream(resourcePath);
        return stream;
    }

    /**
     * Load database properties
     *
     * @return
     */
    public Properties loadDatabaseProperties() {
        Properties properties = new Properties();
        try {
            InputStream file = loadResourceAsStream("db-connection.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * Load xml file object
     *
     * @return
     */
    public InputStream loadXmlInsertFiles() {
        InputStream fileLoaded = null;
        try {
            fileLoaded = loadResourceAsStream("sql/inserts.xml");
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }
        return fileLoaded;
    }

}
