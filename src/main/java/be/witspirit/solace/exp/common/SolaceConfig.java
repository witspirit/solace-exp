package be.witspirit.solace.exp.common;

import com.solacesystems.jcsmp.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolaceConfig {

    @Value("${solace.host}")
    private String host;

    @Value("${solace.username}")
    private String username;

    @Value("${solace.password}")
    private String password;

    @Value("${solace.vpn}")
    private String vpn;


    @Bean
    public JCSMPSession solaceSession() throws JCSMPException {
        JCSMPProperties properties = new JCSMPProperties();
        properties.setProperty(JCSMPProperties.HOST, host);
        properties.setProperty(JCSMPProperties.USERNAME, username);
        properties.setProperty(JCSMPProperties.VPN_NAME,  vpn);
        properties.setProperty(JCSMPProperties.PASSWORD, password);

        JCSMPSession session = JCSMPFactory.onlyInstance().createSession(properties);

        session.connect(); // Not sure whether this should be in the config...

        return session;
    }
}
