package br.com.nmsalone.parser.iomanager;

/**
 * Exception to handle problems with file
 */
public class InvalidFileException extends Exception {

    public InvalidFileException(String message) {
        super(message);
    }
}
