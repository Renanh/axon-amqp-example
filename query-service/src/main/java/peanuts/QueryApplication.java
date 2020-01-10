package peanuts;

import com.rabbitmq.client.Channel;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class QueryApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QueryApplication.class);
    }

    /**
     *
     * "The SpringAMQPMessageSource allows event processors to read messages from a queue, instead of the event store or event bus."
     *
     * https://docs.axoniq.io/reference-guide/extensions/spring-amqp
     */
    @Bean
    public SpringAMQPMessageSource myQueueMessageSource(AMQPMessageConverter messageConverter) {
        return new SpringAMQPMessageSource(messageConverter) {

            @RabbitListener(queues = "events")
            @Override
            public void onMessage(Message message, Channel channel) {
                LOGGER.info("amqp event message received");
                super.onMessage(message, channel);
            }
        };
    }

}
