package br.com.nmsalone.parser.iomanager;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class TestDownloadManager {

    private DownloadManager manager;

    @Before
    public void preapre() {
        manager = new DownloadManager();
    }

    @Test
    public void testDonalodFile() {
        File file = null;
        try {
            file = manager.downaloadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(file);
    }


}