package vercaoComBanco.enty.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vercaoComBanco.enty.model.ItemDeUsoEnty;

public interface ItemDeUsoRepository extends JpaRepository <ItemDeUsoEnty, Integer> {
}
