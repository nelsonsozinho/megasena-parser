package br.com.nmsalone.parser.iomanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileManager {

    public File loaGameReusltFile() throws InvalidFileException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final File file = new File(classLoader.getResource("loteria/D_MEGA.HTM").getFile());
        if(!isValidFile(file)) {
            throw new InvalidFileException("File with problems");
        }

        return file;
    }

    public Properties loadDatabaseProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("db-connection.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    private boolean isValidFile(final File file) {
        if(!file.exists()) {
            return false;
        } else if(file.isDirectory()) {
            return false;
        } else if(file.canRead()) {
            return false;
        }

        return true;
    }

}
