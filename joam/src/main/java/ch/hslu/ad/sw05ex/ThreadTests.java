package ch.hslu.ad.sw05ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ThreadTests {

    public static Logger LOG = LoggerFactory.getLogger(ThreadTests.class);

    public static void main(String[] args) {
        final int numProc = Runtime.getRuntime().availableProcessors(); //logical processors
        LOG.info(String.format("Number of Processors: %s", numProc));

        final Thread self = Thread.currentThread();
        LOG.info("Name : " + self.getName());
        LOG.info("Priority : " + self.getPriority());
        LOG.info("ID : " + self.threadId());
        LOG.info("Deamon? : " + self.isDaemon());
    }
}
