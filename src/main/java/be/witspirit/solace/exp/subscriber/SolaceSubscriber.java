package be.witspirit.solace.exp.subscriber;

import be.witspirit.solace.exp.common.SolaceConfig;
import com.solacesystems.jcsmp.JCSMPSession;
import com.solacesystems.jcsmp.Topic;
import com.solacesystems.jcsmp.XMLMessageConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.Console;
import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackageClasses = {SolaceConfig.class})
public class SolaceSubscriber implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(SolaceSubscriber.class);

    public static void main(String[] args) {
        SpringApplication.run(SolaceSubscriber.class, args);
    }

    @Autowired
    private JCSMPSession session;

    @Autowired
    @Qualifier("partsTopic")
    private Topic partsTopic;

    @Override
    public void run(String... args) throws Exception {
        XMLMessageConsumer consumer = session.getMessageConsumer(new LoggingXmlMessageListener());

        session.addSubscription(partsTopic);

        consumer.start();

        LOG.info("Consumer started... Awaiting messages");

        waitForInput();
    }

    private void waitForInput() {
        Console c = System.console();
        if (c != null) {
            // printf-like arguments
            c.format("\nPress ENTER to proceed.\n");
            c.readLine();
        } else {
            System.out.println("No console...");
            System.out.println("Press any input to proceed");
            try {
                System.in.read();
            } catch (IOException e) {
                throw new RuntimeException("<Sigh> I/O exception on singly byte read... Seriously ? ", e);
            }
        }
    }
}
