package br.com.nmsalone.parser.controller;

import br.com.nmsalone.parser.content.ParserHTMLContent;
import br.com.nmsalone.parser.database.dao.GameDAO;
import br.com.nmsalone.parser.iomanager.DownloadManager;
import br.com.nmsalone.parser.iomanager.FileManager;
import br.com.nmsalone.parser.model.Game;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * Controller to handle the parser utils
 */
public class ParserController {

    private static final Logger logger = LogManager.getLogger(ParserController.class);

    private ParserHTMLContent parserContent;
    private GameDAO dao;

    public ParserController() {
        logger.info("Configure resources");
        try {
            prepareResources();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start data recover process
     */
    public void executeDataFill() {
        logger.info("Recover games from file");
        final List<Game> games = parserContent.recoverToupleValues();
        logger.info("Transfer to database");
        dao.deleteAll();
        dao.addGames(games);
        logger.info("Process finish");
    }

    /**
     * Prepare file resource
     *
     * @throws FileNotFoundException
     */
    private void prepareResources() throws FileNotFoundException {
        final FileManager fileManager = new FileManager();
        final File fileDownloaded = prepareToDownloadFile();
        final File fileUnziped = prepareToUnzip(fileManager, fileDownloaded);
        final InputStream input = new FileInputStream(fileUnziped);

        parserContent = new ParserHTMLContent(input);
        dao = new GameDAO();
    }

    /**
     * Prepare unzip task
     *
     * @param manager
     * @param file
     * @return
     */
    private File prepareToUnzip(final FileManager manager, final File file) {
         File fileOut = null;
        try {
            fileOut = manager.unzipResultPackage(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileOut;
    }

    /**
     * Prepa re download result file
     *
     * @return file
     */
    private File prepareToDownloadFile() {
        final DownloadManager downloadManager = new DownloadManager();
        File fileDownloaded = null;
        try {
            fileDownloaded = downloadManager.downaloadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileDownloaded;
    }

}
