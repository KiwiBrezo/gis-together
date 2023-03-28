package si.um.feri.gisgeodataservice.configs

import jakarta.jms.ConnectionFactory
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.config.JmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate


@Configuration
@EnableJms
class JmsConfig {
    @Bean
    fun jmsListenerContainerFactory(): JmsListenerContainerFactory<*> {
        val factory = DefaultJmsListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        return factory
    }

    @Bean
    fun connectionFactory(): ConnectionFactory {
        //return ActiveMQConnectionFactory("tcp://localhost:61613?protocols=STOMP")
        return ActiveMQConnectionFactory("tcp://localhost:61613")
    }

    @Bean
    fun jmsTemplate(): JmsTemplate {
        return JmsTemplate(connectionFactory())
    }
}