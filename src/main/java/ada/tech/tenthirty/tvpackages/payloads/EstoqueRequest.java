package ada.tech.tenthirty.tvpackages.payloads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class EstoqueRequest {
    
    private String id_compra;
    private String id_cliente;
    private List<ItemRequest> itens = new ArrayList<>();
    private EnvioRequest envio;
    
    @JsonCreator
    public EstoqueRequest(@JsonProperty("id_compra") String id_compra,
                     @JsonProperty("id_cliente") String id_cliente,
                     @JsonProperty("itens") List<ItemRequest> itens,
                     @JsonProperty("envio") EnvioRequest envio
    ) {
        this.id_compra = Objects.requireNonNull(id_compra);
        this.id_cliente = Objects.requireNonNull(id_cliente);
        this.itens = Objects.requireNonNull(itens);
        this.envio = Objects.requireNonNull(envio);
    }
    
//    {
//        "id_compra":"string",
//            "id_cliente":"string",
//            "itens":[
//        {
//            "sku":"string",
//                "quantidade":"string"
//        }
//   ],
//        "envio":{
//        "cep":"string",
//        "rua":"string",
//        "bairro":"string",
//         "cidade":"string",
//         "estado":"string",
//         "numero":"string",
//         "destinatario":"string"
//    }
//    }

}
