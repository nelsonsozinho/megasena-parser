package br.com.nmsalone.parser.database.dao;

import br.com.nmsalone.parser.database.ConnectionWrapper;
import br.com.nmsalone.parser.model.Game;

import java.util.List;

/**
 * Created by nelsonsozinho on 24/05/16.
 */
public class GameDAO {

    private ConnectionWrapper connectionWrapper;

    public GameDAO(final ConnectionWrapper connection) {
        this.connectionWrapper = connectionWrapper;
    }

    public void addGame(final Game game) {

    }

    public void addGames(List<Game> games) {

    }

}
