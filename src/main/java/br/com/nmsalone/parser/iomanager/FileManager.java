package br.com.nmsalone.parser.iomanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    public File loaGameResultFile()  {
        File fileLoaded = null;
        try {
            fileLoaded = loadResource("loteria/D_MEGA.HTM");
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }
        return fileLoaded;
    }

    private File loadResource(final String resourcePath) throws InvalidFileException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource(resourcePath).getFile());
        if(!isValidFile(file)) {
            throw new InvalidFileException("File with problems");
        }

        return file;
    }

    /**
     * Load database properties
     *
     * @return
     */
    public Properties loadDatabaseProperties() {
        Properties properties = new Properties();
        try {
            File file = loadResource("db-connection.properties");
            properties.load(new FileInputStream(file));
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
    public File loadXmlInsertFiles() {
        File fileLoaded = null;
        try {
            fileLoaded = loadResource("sql/inserts.xml");
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }
        return fileLoaded;
    }

    private boolean isValidFile(final File file) {
        if(!file.exists()) {
            return false;
        } else if(file.isDirectory()) {
            return false;
        } else if(!file.canRead()) {
            return false;
        }

        return true;
    }


}
