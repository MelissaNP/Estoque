package ada.tech.tenthirty.tvpackages.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class EnvioRequest {
    
    private String id_compra;
    private String id_cliente;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String destinatario;
    
}



