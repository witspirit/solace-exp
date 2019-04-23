package be.witspirit.solace.exp.subscriber;

import be.witspirit.solace.exp.common.SolaceConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.Console;
import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackageClasses = {SolaceConfig.class})
public class SolaceSubscriber implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SolaceSubscriber.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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
