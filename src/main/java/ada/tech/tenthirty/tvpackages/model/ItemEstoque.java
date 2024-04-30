package ada.tech.tenthirty.tvpackages.model;
import lombok.*;

import jakarta.persistence.*;
import jakarta.persistence.Id;


@Table(name = "TB-ESTOQUE")
@Data
@Entity
public class ItemEstoque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String sku;
    private Integer quantidade;

    
}