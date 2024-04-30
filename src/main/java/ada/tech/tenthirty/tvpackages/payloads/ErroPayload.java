package ada.tech.tenthirty.tvpackages.payloads;


import lombok.Data;

@Data
public class ErroPayload {
    
    private String idCompra;
    private String error;
}
