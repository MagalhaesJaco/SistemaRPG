package vercaoComBanco.enty.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vercaoComBanco.enty.model.ItemDeUsoEnty;
import vercaoComBanco.enty.repository.ItemDeUsoRepository;
import vercaoComBanco.enty.supEnty.TipoItem;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemDeUsoService {

    private final ItemDeUsoRepository itemDeUsoRepository;

    public ItemDeUsoEnty save(ItemDeUsoEnty item){
        return itemDeUsoRepository.save(item);
    }
    public List<ItemDeUsoEnty> ListarTodos(){
        return itemDeUsoRepository.findAll();
    }
    public Optional<ItemDeUsoEnty> buscarItem(Integer id){
        return itemDeUsoRepository.findById(id);
    }
    public void mostrarNomeDoItem(Integer id) {
        Optional<ItemDeUsoEnty> itemOpt = buscarItem(id);
        if (itemOpt.isPresent()) {
            System.out.println("Nome do item: " + itemOpt.get().getNome());
        } else {
            System.out.println("Item com ID " + id + " não encontrado.");
        }
    }
    public void inserirItemTeste() {
        ItemDeUsoEnty item = new ItemDeUsoEnty();
        item.setId(1);
        item.setNome("Espada de Teste");
        item.setDano(10);
        item.setTipo(TipoItem.pesado);
        save(item);
    }
}
