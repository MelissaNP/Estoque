package ada.tech.tenthirty.tvpackages.repository;

import ada.tech.tenthirty.tvpackages.model.ItemEstoque;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface EstoqueRepository extends JpaRepository<ItemEstoque, Long> {
    
    @Query("SELECT e FROM ItemEstoque e WHERE e.sku = ?1")
    ItemEstoque findEstoqueBySku(String sku);
    
    @Transactional
    @Modifying
    @Query("UPDATE ItemEstoque e set e.quantidade = e.quantidade - ?1 where e.sku = ?2")
    Integer removeItemBySku(Integer quantidade, String sku);
}