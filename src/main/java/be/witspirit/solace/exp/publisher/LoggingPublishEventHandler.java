package be.witspirit.solace.exp.publisher;

import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.JCSMPStreamingPublishEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingPublishEventHandler implements JCSMPStreamingPublishEventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingPublishEventHandler.class);

    @Override
    public void handleError(String messageId, JCSMPException exception, long timestamp) {
        LOG.error("Producer received error for " + messageId + " at " + timestamp, exception);
    }

    @Override
    public void responseReceived(String messageId) {
        LOG.info("Producer received response for message {}", messageId);
    }
}
