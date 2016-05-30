package br.com.nmsalone.parser.iomanager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Load resources from sand box
 */
public class FileManager {

    private static final Logger log = LogManager.getLogger(FileManager.class);

    private static final int BUFFER_SIZE = 4096;
    private static final String FILE_DIR = ".loteria";


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

    /**
     * Unzip file
     *
     * @param path
     * @return
     * @throws IOException
     */
    public File unzipResultPackage(final File path) throws IOException {
        final FileInputStream fis = new FileInputStream(path);
        final ZipInputStream zipInputStream = new ZipInputStream(fis);

        File out = null;
        ZipEntry entry = zipInputStream.getNextEntry();

        final byte[] buffer = new byte[BUFFER_SIZE];
        final String homePath = System.getProperty("user.home");

        while(entry != null) {
            String filename = entry.getName();
            final File newFile = new File(homePath + File.separator + FILE_DIR + File.separator + filename);
            log.info("Unzip file to: " + newFile.getAbsoluteFile());

            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while((len = zipInputStream.read(buffer)) > 0) {
                fos.write(buffer, 0 , len);
            }

            fos.close();
            zipInputStream.closeEntry();
            entry = zipInputStream.getNextEntry();
        }

        zipInputStream.closeEntry();
        zipInputStream.close();
        fis.close();

        return new File(homePath + File.separator + FILE_DIR + File.separator + "D_MEGA.HTM");
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

    private InputStream loadResourceAsStream(final String resourcePath) throws InvalidFileException {
        final ClassLoader classLoader = getClass().getClassLoader();
        final InputStream stream = classLoader.getResourceAsStream(resourcePath);
        return stream;
    }

}
