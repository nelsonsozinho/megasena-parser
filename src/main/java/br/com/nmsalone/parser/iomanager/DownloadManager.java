package br.com.nmsalone.parser.iomanager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;

/**
 * Download manager class
 */
public class DownloadManager {

    private static final Logger log = LogManager.getLogger(DownloadManager.class);

    private static final String FILE_URL = "http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_megase.zip";

    private static final String FILE_DIR = ".loteria";

    private static final String FILE_TEMP_NAME = "package";

    private static final String FILE_TEMP_EXTENSION = ".zip";

    /**
     * Download file html result
     *
     * @return file
     * @throws IOException
     */
    public File downaloadFile() throws IOException {
        final CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        final URL url = getUrl();
        final File tempDirectory = crateDirectoryIfNoExists();
        final HttpURLConnection httpConnection = getObjectConnection(url);
        final Integer completeFileSize = httpConnection.getContentLength();
        final BufferedInputStream in = getInputStream(httpConnection);
        final FileOutputStream fos = getOutpurStream(tempDirectory);
        final BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

        byte[] data = new byte[1024];
        long downloadFileSize = 0;
        int x = 0;
        while((x = in.read(data, 0, 1024)) >= 0) {
            downloadFileSize += x;
            final int currentProgress = (int) ((((double)downloadFileSize) / ((double)completeFileSize)) * 100000d);
            log.info("Download progress: " + currentProgress);
            bos.write(data,0,x);
        }

        bos.close();
        in.close();

        return  new File(tempDirectory, FILE_TEMP_NAME+FILE_TEMP_EXTENSION);
    }

    private URL getUrl() {
        URL url = null;
        try {
            url = new URL(FILE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private HttpURLConnection getObjectConnection(final URL url) {
        HttpURLConnection httpConnection = null;
        try {
            httpConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return httpConnection;
    }

    private BufferedInputStream getInputStream(HttpURLConnection connection) {
        BufferedInputStream in = null;
        try {
            final InputStream stream = connection.getInputStream();
            in = new BufferedInputStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return in;
    }

    /**
     * Create a new file to receive the stream data
     * @param dir
     * @return stream
     */
    private FileOutputStream getOutpurStream(final File dir) {
        FileOutputStream fos = null;
        try {
            final File file = new File(dir, FILE_TEMP_NAME+FILE_TEMP_EXTENSION);
            file.createNewFile();
            fos = new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fos;
    }

    /**
     * Create a temp directory if does not exists
     */
    private File crateDirectoryIfNoExists() {
        final String homePath = System.getProperty("user.home");
        log.info("Separator: " + homePath);
        final String separator = File.separator;
        final File file = new File(homePath + separator + FILE_DIR + separator);
        if(!file.exists()) {
            file.mkdir();
            log.info("Create a new file: " + file.getAbsolutePath());
        }

        return file;
    }

}