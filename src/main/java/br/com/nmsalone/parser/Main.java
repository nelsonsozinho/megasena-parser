package br.com.nmsalone.parser;

import br.com.nmsalone.parser.controller.ParserController;

/**
 * Created by nelsonsozinho on 22/05/16.
 */
public class Main {

    public static void main(final String args[]) {
        ParserController controller = new ParserController();
        controller.executeDataFill();
    }

}
