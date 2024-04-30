package ada.tech.tenthirty.tvpackages.queue;

import ada.tech.tenthirty.tvpackages.payloads.ErroPayload;
import ada.tech.tenthirty.tvpackages.rest.RemoverItemService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {
    
    final RemoverItemService estoqueService;
    private final ObjectMapper objectMapper;
    
    public EstoqueConsumer(RemoverItemService estoqueService) {
        this.estoqueService = estoqueService;
    }
    
    
    @RabbitListener(queues = "${negocio.estoque.fila-envio}")
    public void consumeMessage(@Payload ErroPayload erroPayload) {
        String errorMessage = new String(erroPayload.getBody());
        ErroPayload erro = objectMapper.readValue(errorMessage, ErroPayload[].class);
        System.out.println(erro);
        
        //colocar o método addEstoque;
        
    }
    
}
