package vercaoComBanco.enty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import vercaoComBanco.enty.service.ItemDeUsoService;

@SpringBootApplication
public class vercaoComBanco {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(vercaoComBanco.class, args);

        ItemDeUsoService itemDeUsoService = context.getBean(ItemDeUsoService.class);

        itemDeUsoService.inserirItemTeste();
        itemDeUsoService.mostrarNomeDoItem(1);
    }
}
