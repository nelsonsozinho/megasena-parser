package br.com.nmsalone.parser.controller;

import br.com.nmsalone.parser.content.ParserHTMLContent;
import br.com.nmsalone.parser.database.ConnectionFactory;
import br.com.nmsalone.parser.database.ConnectionWrapper;
import br.com.nmsalone.parser.database.dao.GameDAO;
import br.com.nmsalone.parser.iomanager.FileManager;
import br.com.nmsalone.parser.model.Game;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Controller to handle the parser utils
 */
public class ParserController {

    private static final Logger logger = LogManager.getLogger(ParserController.class);

    private ConnectionWrapper connectionWrapper;
    private ParserHTMLContent parserContent;
    private GameDAO dao;

    public ParserController() {
        logger.info("Configure reources");
        prepareResources();
    }

    public void executeDataFill() {
        logger.info("Recover games from file");
        final List<Game> games = parserContent.recoverToupleValues();
        logger.info("Transfer to database");
        dao.addGames(games);
        logger.info("Process finish");
    }

    private void prepareResources()  {
        final FileManager fileManager = new FileManager();

        parserContent = new ParserHTMLContent(fileManager.loaGameResultFile());
        connectionWrapper = ConnectionFactory.getInstance().newConnection();
        dao = new GameDAO(connectionWrapper);
    }

    private List<Game> getContentFromFile() {
        final List<Game> fileContent = parserContent.recoverToupleValues();
        return fileContent;
    }

}
