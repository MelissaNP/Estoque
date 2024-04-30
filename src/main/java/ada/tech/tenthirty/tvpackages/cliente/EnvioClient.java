package ada.tech.tenthirty.tvpackages.cliente;


import ada.tech.tenthirty.tvpackages.payloads.EnvioRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface EnvioClient {
    
    @PostExchange(value= "/")
    EnvioRequest mandarEndereco( @RequestBody EnvioRequest envio);
    
}
