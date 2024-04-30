package ada.tech.tenthirty.tvpackages.rest;

import ada.tech.tenthirty.tvpackages.model.ItemEstoque;
import ada.tech.tenthirty.tvpackages.payloads.ItemRequest;
import ada.tech.tenthirty.tvpackages.repository.EstoqueRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdicionarItemService {
    

    private EstoqueRepository estoqueRepository;
    
    public AdicionarItemService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }
    
    public void adicionarItens(List<ItemRequest> itens) {
     
        
        for (ItemRequest itemRequest : itens) {
            String sku = itemRequest.getSku();
            int quantidade = itemRequest.getQuantidade();
            
            ItemEstoque estoqueItem = estoqueRepository.findEstoqueBySku(sku);
            if (estoqueItem != null) {
                int quantidadeAtual = estoqueItem.getQuantidade();
                quantidadeAtual += quantidade;
                estoqueItem.setQuantidade(quantidadeAtual);
            } else {
                estoqueItem = new ItemEstoque();
                estoqueItem.setSku(sku);
                estoqueItem.setQuantidade(quantidade);
            }
            
            estoqueRepository.save(estoqueItem);
        }
    }
}