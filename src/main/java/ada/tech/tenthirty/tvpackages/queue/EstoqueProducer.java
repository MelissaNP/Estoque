package ada.tech.tenthirty.tvpackages.queue;

import ada.tech.tenthirty.tvpackages.payloads.ErroPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EstoqueProducer {
    
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEstoque;
    @Value("${negocio.estoque.fila-estoque}")
    private String routingKey;
    
    public EstoqueProducer(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate, @Qualifier("queueEstoque") Queue queueEstoque) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.queueEstoque = queueEstoque;
    }
    
    
    public void enviarMensagemErro(@Payload ErroPayload erroPayload) {
        
        try {
            System.out.println(queueEstoque.getName());
            String objeto = objectMapper.writeValueAsString(erroPayload);
            System.out.println("objeto");
            rabbitTemplate.convertSendAndReceive("", routingKey, objeto);
            
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
        
        //rabbitTemplate.convertAndSend(queueEstoque.getName(),routingKey, erroPayload);
        
    }
}


