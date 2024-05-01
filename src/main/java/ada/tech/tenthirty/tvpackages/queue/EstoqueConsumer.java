package ada.tech.tenthirty.tvpackages.queue;

import ada.tech.tenthirty.tvpackages.payloads.ErroPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Service;

import java.io.IOException;
@RequiredArgsConstructor
@Service
@Slf4j
public class EstoqueConsumer {
    private final ObjectMapper objectMapper;
    
    @RabbitListener(queues = {"${negocio.estoque.fila-envio}"})
    public void consumer(Message message , Channel channel)  {
        try {
            String mensagemString = new String(message.getBody());
            ErroPayload informarErro = objectMapper.readValue(mensagemString, ErroPayload.class);
            log.info("Mensagem falha envio {}", informarErro);
             channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  
    
}
