package br.com.nmsalone.parser;

import br.com.nmsalone.parser.controller.ParserController;

/**
 * Main class
 */
public class Main {

    public static void main(final String args[]) {
        ParserController controller = new ParserController();
        controller.executeDataFill();
    }

}
