package vercaoComBanco.enty.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vercaoComBanco.enty.supEnty.TipoItem;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itens_de_uso")
public class ItemDeUsoEnty {

    @Id
    private Integer id;

    private String nome;

    private Integer dano;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;
}

