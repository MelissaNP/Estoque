package ada.tech.tenthirty.tvpackages.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    
    @Value("${negocio.estoque.fila-estoque}")
    private String filaEstoque;
    
    @Value("${negocio.estoque.fila-envio}")
    private String filaEnvio;
    
    @Value("${negocio.estoque.routing-key}")
    private String routingKey;
    
    @Bean
    public Queue queueEstoque(){
        return new Queue(filaEstoque, true);
    }
    
    @Bean
    public Queue queueEnvio(){
        return new Queue(filaEnvio, true);
    }
    
//    @Bean
//    public TopicExchange exchange() {
//        return new TopicExchange(exchangeName);
//    }
//
//    @Bean
//    public Binding bindingEstoque(Queue queueEstoque, TopicExchange exchange) {
//        return BindingBuilder.bind(queueEstoque).to(exchange).with(routingKey);
//    }
//
//    @Bean
//    public Binding bindingEnvio(Queue queueEnvio, TopicExchange exchange) {
//        return BindingBuilder.bind(queueEnvio).to(exchange).with(routingKey);
//    }
    
    
}
