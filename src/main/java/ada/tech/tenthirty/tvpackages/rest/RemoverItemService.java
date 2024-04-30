package ada.tech.tenthirty.tvpackages.rest;

import ada.tech.tenthirty.tvpackages.model.ItemEstoque;
import ada.tech.tenthirty.tvpackages.payloads.EstoqueRequest;
import ada.tech.tenthirty.tvpackages.payloads.ItemRequest;
import ada.tech.tenthirty.tvpackages.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@RequiredArgsConstructor
public class RemoverItemService {
    
//
//    EstoqueRepository estoqueRepository;
//
//
//
//    @Transactional
//    public void removerItens(EstoqueRequest estoqueRequest) {
//        for (ItemRequest itemRequest : estoqueRequest.getItens()) {
//            String sku = itemRequest.getSku();
//            int quantidadePedido = Integer.parseInt(itemRequest.getQuantidade());
//
//            ItemEstoque estoqueItem = estoqueRepository.findEstoqueBySku(sku);
//            if (estoqueItem != null) {
//                int quantidadeAtual = estoqueItem.getQuantidade();
//                if (quantidadeAtual >= quantidadePedido) {
//                    quantidadeAtual -= quantidadePedido;
//                    estoqueItem.setQuantidade(quantidadeAtual);
//                    estoqueRepository.save(estoqueItem);
//                } else {
//                    throw new RuntimeException("Erro: O produto com SKU '" + sku + "' está em falta.");
//                }
//            } else {
//                throw new RuntimeException("Erro: O produto com SKU '" + sku + "' não foi encontrado no estoque.");
//            }
//        }
//    }
}