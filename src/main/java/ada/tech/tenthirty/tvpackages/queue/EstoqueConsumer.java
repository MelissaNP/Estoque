package ada.tech.tenthirty.tvpackages.queue;

import ada.tech.tenthirty.tvpackages.payloads.ErroPayload;
import ada.tech.tenthirty.tvpackages.rest.RemoverItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EstoqueConsumer {
    private final ObjectMapper objectMapper;
    
    public EstoqueConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    @RabbitListener(queues = {"${negocio.estoque.fila-envio}"})
    public void consumer(Message message , Channel channel)  {
        try {
            String mensagemString = new String(message.getBody());
            ErroPayload informarErro = objectMapper.readValue(mensagemString, ErroPayload.class);
            System.out.println(informarErro);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
