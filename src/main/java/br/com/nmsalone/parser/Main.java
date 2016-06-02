package br.com.nmsalone.parser;

import br.com.nmsalone.parser.controller.ParserController;
import br.com.nmsalone.parser.scheduler.TaskScheduler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Main class
 */
public class Main {

    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(final String args[]) throws InterruptedException {
        //final TaskScheduler task = new TaskScheduler();
        //task.startAsync();
        //Thread.sleep(50000);
        //task.stopAsync();


        log.info("Executing task");
        ParserController controller = new ParserController();
        controller.executeDataFill();
    }

}
