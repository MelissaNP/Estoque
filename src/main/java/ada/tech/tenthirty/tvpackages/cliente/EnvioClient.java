package ada.tech.tenthirty.tvpackages.cliente;


import ada.tech.tenthirty.tvpackages.payloads.EnvioRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "envioClient", url = "${negocio.envio.url}")
public interface EnvioClient {
    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = "application/json")
    void enviarDadosEnvio(@RequestBody EnvioRequest dadosEnvio);
}