package ada.tech.tenthirty.tvpackages.rest;


import ada.tech.tenthirty.tvpackages.cliente.EnvioClient;
import ada.tech.tenthirty.tvpackages.payloads.EnvioRequest;
import ada.tech.tenthirty.tvpackages.payloads.ErroPayload;
import ada.tech.tenthirty.tvpackages.payloads.EstoqueRequest;
import ada.tech.tenthirty.tvpackages.payloads.ItemRequest;
import ada.tech.tenthirty.tvpackages.queue.EstoqueProducer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EstoqueController {
    
    private final ItemEstoqueServiceImpl serviceItemEstoque;
    private final AdicionarItemService adicionarItemService;
    private final EstoqueProducer estoqueProducer;
    private final EnvioClient envioClient;
    
    public EstoqueController(AdicionarItemService adicionarItemService, ItemEstoqueServiceImpl serviceItemEstoque, EstoqueProducer estoqueProducer, EnvioClient envioClient) {
        this.adicionarItemService = adicionarItemService;
        this.serviceItemEstoque = serviceItemEstoque;
        this.estoqueProducer = estoqueProducer;
        this.envioClient = envioClient;
    }
    
    @PostMapping("/processarcompra")
    public ResponseEntity removerItensEstoque(@RequestBody @Valid EstoqueRequest request) {
        
        boolean disponivel = serviceItemEstoque.disponivel(request.getItens());
        
        if (disponivel) {
            serviceItemEstoque.remove(request.getItens());
            envioClient.enviarDadosEnvio(new EnvioRequest(request.getId_compra(),
                    request.getId_cliente(),
                    request.getEnvio().getCep(),
                    request.getEnvio().getRua(),
                    request.getEnvio().getBairro(),
                    request.getEnvio().getCidade(),
                    request.getEnvio().getEstado(),
                    request.getEnvio().getNumero(),
                    request.getEnvio().getDestinatario()
            ));
        } else {
            var erroPayload = new ErroPayload();
            
            erroPayload.setIdCompra(request.getId_compra());
            erroPayload.setError("Estoque insuficiente");
            estoqueProducer.enviarMensagemErro(erroPayload);
        }
        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ArrayList<>());
    }
    
    @PostMapping("/adicionar-itens")
    public ResponseEntity<String> adicionarItensAoEstoque(@RequestBody List<ItemRequest> itens) {
        try {
            adicionarItemService.adicionarItens(itens);
            return ResponseEntity.ok("Itens adicionados com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar itens ao estoque: " + e.getMessage());
        }
    }
    
}