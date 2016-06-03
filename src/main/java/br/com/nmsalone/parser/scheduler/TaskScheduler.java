package br.com.nmsalone.parser.scheduler;

import br.com.nmsalone.parser.controller.ParserController;
import com.google.common.util.concurrent.AbstractScheduledService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class TaskScheduler extends AbstractScheduledService {

    private static final Logger log = LogManager.getLogger(TaskScheduler.class);

    @Override
    protected void runOneIteration() throws Exception {
        log.info("Executing task");
        ParserController controller = new ParserController();
        controller.executeDataFill();
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(5, 6, TimeUnit.HOURS);
    }

    @Override
    protected void startUp() throws Exception {
        log.info("Start service");
        super.startUp();
    }

    @Override
    protected void shutDown() throws Exception {
        log.info("Shutdown service");
        super.shutDown();
    }

}
