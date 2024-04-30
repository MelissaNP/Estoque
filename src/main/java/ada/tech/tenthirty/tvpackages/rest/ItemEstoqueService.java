package ada.tech.tenthirty.tvpackages.rest;


import ada.tech.tenthirty.tvpackages.model.ItemEstoque;
import ada.tech.tenthirty.tvpackages.payloads.ItemRequest;

import java.util.List;

public interface ItemEstoqueService {
    Boolean disponivel(List<ItemRequest> items);
    Void remove(List<ItemRequest> items);
    ItemEstoque saveOne(ItemRequest item);
}
