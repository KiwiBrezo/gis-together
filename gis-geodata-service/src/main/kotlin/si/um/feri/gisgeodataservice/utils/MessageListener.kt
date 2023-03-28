package si.um.feri.gisgeodataservice.utils

import jakarta.jms.JMSException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component


@Component
class MessageListener {

    @JmsListener(destination = "insertedGeojson")
    @Throws(JMSException::class)
    fun sampleJmsListenerMethod(message: String) {
        logger.info("JMS listener received text message: {}", message)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(MessageListener::class.java)
    }
}