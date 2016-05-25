package br.com.nmsalone.parser.database.dao;

import br.com.nmsalone.parser.content.sql.ParserDatabaseSQL;
import br.com.nmsalone.parser.database.ConnectionFactory;
import br.com.nmsalone.parser.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestGameDAO {

    private ParserDatabaseSQL parserSQL;
    private ConnectionFactory factory = ConnectionFactory.getInstance();
    private GameDAO dao;
    private String sql;

    @Before
    public void prepare() {
        parserSQL = new ParserDatabaseSQL();
        dao = new GameDAO(factory.newConnection());

        try {
            sql = parserSQL.loadInsert();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void testAddGame() {
        final Game game = prepareGame();
        dao.addGame(game);
    }

    //@Test
    public void testAddGames() {
        final List<Game> games = prepareGames();
        dao.addGames(games);
    }

    private Game prepareGame() {
        Game game = new Game();
        game.setId(10L);
        game.setDateDraw(new Date());
        game.setCity("test");
        game.setWinnersCount(10);
        game.setN1(10);
        game.setN2(10);
        game.setN3(10);
        game.setN4(10);
        game.setN5(10);
        game.setN6(10);

        return game;
    }

    private List<Game> prepareGames() {
        List<Game> games = new ArrayList<>();
        Game game1 = prepareGame();
        Game game2 = prepareGame();

        game1.setId(1L);
        game2.setId(2L);

        games.add(game1);
        games.add(game2);

        return games;
    }

}
