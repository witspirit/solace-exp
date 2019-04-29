package be.witspirit.solace.exp.subscriber;

import com.solacesystems.jcsmp.BytesXMLMessage;
import com.solacesystems.jcsmp.JCSMPException;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jcsmp.XMLMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingXmlMessageListener implements XMLMessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingXmlMessageListener.class);


    @Override
    public void onReceive(BytesXMLMessage msg) {
        if (msg instanceof TextMessage) {
            LOG.info("TextMessage received: {}", ((TextMessage)msg).getText());
        } else {
            LOG.info("Message received. Type {}", msg.getClass());
        }
        LOG.info("Message Dump:\n{}",msg.dump());
    }

    @Override
    public void onException(JCSMPException e) {
        LOG.error("Consumer received exception: "+e.getMessage(), e);
    }
}
