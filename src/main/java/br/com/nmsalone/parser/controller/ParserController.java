package br.com.nmsalone.parser.controller;

import br.com.nmsalone.parser.database.ConnectionFactory;
import br.com.nmsalone.parser.database.ConnectionWrapper;
import br.com.nmsalone.parser.htmlcontent.ParserContent;
import br.com.nmsalone.parser.iomanager.FileManager;
import br.com.nmsalone.parser.iomanager.InvalidFileException;
import br.com.nmsalone.parser.model.Game;

import java.util.List;
import java.util.Map;

/**
 * Created by nelsonsozinho on 22/05/16.
 */
public class ParserController {

    private ConnectionWrapper connectionWrapper;
    private ParserContent parserContent;

    public ParserController() {
        try {
            prepareResources();
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }

    }

    private void prepareResources() throws InvalidFileException {
        final FileManager fileManager = new FileManager();
        parserContent = new ParserContent(fileManager.loaGameReusltFile());
        connectionWrapper = ConnectionFactory.getInstance().newConnection();
    }

    private List<Game> getContentFromFile() {
        final List<Game> fileContent = parserContent.recoverToupleValues();
        return fileContent;
    }



}
