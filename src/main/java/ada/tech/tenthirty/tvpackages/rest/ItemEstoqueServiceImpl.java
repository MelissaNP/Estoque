package ada.tech.tenthirty.tvpackages.rest;



import ada.tech.tenthirty.tvpackages.model.ItemEstoque;
import ada.tech.tenthirty.tvpackages.payloads.ItemRequest;
import ada.tech.tenthirty.tvpackages.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemEstoqueServiceImpl implements ItemEstoqueService {
    
    private final EstoqueRepository estoqueRepository;
    private final ModelMapper modelMapper;

    @Override
    public Boolean disponivel(List<ItemRequest> items) {
        System.out.println("tentando verificar");
        boolean disponivel = true;

        for (ItemRequest item : items) {
            ItemEstoque e = estoqueRepository.findEstoqueBySku(item.getSku());
            System.out.println("verificando item "+ item.getSku());
            if (e == null || e.getQuantidade() < item.getQuantidade()) {
                disponivel = false;
                break;
            }
        }
        return disponivel;
    }

    @Override
    public Void remove(List<ItemRequest> items){
        System.out.println("removeu");
        for (ItemRequest item : items) {
            estoqueRepository.removeItemBySku(item.getQuantidade(), item.getSku());
        }

        return null;
    }

    @Override
    public ItemEstoque saveOne(ItemRequest item) {
        ItemEstoque estoque = modelMapper.map(item, ItemEstoque.class);
//        estoqueRepository.findEstoquesBySku("ABCDEF");
        return estoqueRepository.save(estoque);
    }
}
