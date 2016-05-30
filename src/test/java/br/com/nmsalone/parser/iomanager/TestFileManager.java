package br.com.nmsalone.parser.iomanager;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestFileManager {

    private static final String FILE_DIR = ".loteria";
    private static final String FILE_TEMP_NAME = "package";
    private static final String FILE_TEMP_EXTENSION = ".zip";

    private FileManager fileManger;
    private File filePath;
    private DownloadManager manager;


    @Before
    public void preapre() {
        fileManger = new FileManager();
        manager = new DownloadManager();
        try {
            filePath = manager.downaloadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUnzipPackage() {
        File out = null;
        try {
            out = fileManger.unzipResultPackage(this.filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(out);
        assertTrue(out.exists());
        assertTrue(out.isFile());
    }

}
