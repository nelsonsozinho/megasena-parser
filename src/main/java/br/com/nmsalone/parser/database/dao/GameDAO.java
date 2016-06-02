package br.com.nmsalone.parser.database.dao;

import br.com.nmsalone.parser.constants.QueryIds;
import br.com.nmsalone.parser.constants.SqlTags;
import br.com.nmsalone.parser.content.sql.ParserDatabaseSQL;
import br.com.nmsalone.parser.database.ConnectionFactory;
import br.com.nmsalone.parser.database.ConnectionWrapper;
import br.com.nmsalone.parser.model.Game;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO to game model
 */
public class GameDAO {

    private ParserDatabaseSQL parserSQLContent;

    public GameDAO() {
        parserSQLContent = ParserDatabaseSQL.getInstance();
    }

    /**
     * Delete all records from table
     */
    public void deleteAll() {
        final ConnectionWrapper connectionWrapper = ConnectionFactory.getInstance().newConnection();
        final String query = loadSQL(SqlTags.KEY_DELETE, QueryIds.DELETE_ALL_GAMES);
        final PreparedStatement statement = (PreparedStatement) connectionWrapper.prepareStatement(query);
        try {
            statement.executeUpdate();
            connectionWrapper.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionWrapper.closeConnection();
        }

    }

    /**
     * Add a new game
     *
     * @param game
     */
    public void addGame(final Game game) {
        final ConnectionWrapper connectionWrapper = ConnectionFactory.getInstance().newConnection();
        final String query = loadSQL(SqlTags.KEY_INSERT, QueryIds.ADD_NEW_GAME);
        final PreparedStatement statement = (PreparedStatement) connectionWrapper.prepareStatement(query);
        try {
            statement.setLong(1, game.getId());
            statement.setDate(2, convertDate(game.getDateDraw()));
            statement.setInt(3, game.getWinnersCount());
            statement.setInt(4, game.getN1());
            statement.setInt(5, game.getN2());
            statement.setInt(6, game.getN3());
            statement.setInt(7, game.getN4());
            statement.setInt(8, game.getN5());
            statement.setInt(9, game.getN6());

            statement.executeUpdate();
            connectionWrapper.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connectionWrapper.rollback();
        } finally {
            connectionWrapper.closeConnection();
        }
    }

    /**
     * Add new games in batch
     *
     * @param games
     */
    public void addGames(List<Game> games) {
        final ConnectionWrapper connectionWrapper = ConnectionFactory.getInstance().newConnection();
        final String query = loadSQL(SqlTags.KEY_INSERT, QueryIds.ADD_NEW_GAME);
        final PreparedStatement statement = (PreparedStatement) connectionWrapper.prepareStatement(query);
        try {
            for(Game game : games) {
                statement.setLong(1, game.getId());
                statement.setDate(2, convertDate(game.getDateDraw()));
                statement.setInt(3, game.getWinnersCount());
                statement.setInt(4, game.getN1());
                statement.setInt(5, game.getN2());
                statement.setInt(6, game.getN3());
                statement.setInt(7, game.getN4());
                statement.setInt(8, game.getN5());
                statement.setInt(9, game.getN6());

                statement.addBatch();
            }

            statement.executeBatch();
            connectionWrapper.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connectionWrapper.rollback();
        } finally {
            connectionWrapper.closeConnection();
        }
    }

    private String loadSQL(final String type, final String id) {
        String query = null;
        try {
            query = parserSQLContent.findQuery(type, id);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return query;
    }




    private java.sql.Date convertDate(java.util.Date date) {
        java.sql.Date dateConverted = new java.sql.Date(date.getTime());
        return dateConverted;
    }

}
