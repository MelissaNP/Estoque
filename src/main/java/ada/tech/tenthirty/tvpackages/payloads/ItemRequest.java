package ada.tech.tenthirty.tvpackages.payloads;

import lombok.Data;

@Data
public class ItemRequest {
    
    private String sku;
    private Integer quantidade;
}
