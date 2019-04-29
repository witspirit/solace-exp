package be.witspirit.solace.exp.publisher;

import be.witspirit.solace.exp.common.SolaceConfig;
import com.solacesystems.jcsmp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.Instant;

@SpringBootApplication
@ComponentScan(basePackageClasses = {SolaceConfig.class})
public class SolacePublisher implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SolacePublisher.class, args);
    }

    @Autowired
    private JCSMPSession session;

    @Override
    public void run(String... args) throws Exception {
        XMLMessageProducer producer = session.getMessageProducer(new LoggingPublishEventHandler());

        Topic partA = JCSMPFactory.onlyInstance().createTopic("parts/partA");
        TextMessage messageA = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
        messageA.setText("Part Update A@"+ Instant.now());

        producer.send(messageA, partA);

        Topic partB = JCSMPFactory.onlyInstance().createTopic("parts/partB");
        TextMessage messageB = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
        messageB.setText("Part Update B@"+ Instant.now());

        producer.send(messageB, partB);


    }
}
