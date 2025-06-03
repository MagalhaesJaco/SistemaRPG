package org.example.atributos;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.classesSuportes.Dados;
import org.springframework.data.repository.query.parser.Part;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Getter
public class ItemDeUso {

    // Definiçoes de itens !
    // Definição de atributos !! //
    private  Integer id;
    private final String nome;
    private Integer dano;
    private String tipo;

    private static final String URL = "jdbc:mysql://localhost:3306/sistemarpg";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";

    // Construtor ! //

    ItemDeUso(int id, String nome, Integer dano, String tipo){
        this.id = id;
        this.nome = nome;
        this.dano = rollDamage(dano);
        this.tipo = tipo;
    }
    // Gets e sets !! //
    public Integer rollDamage(Integer face){
        Dados roll = new Dados();
        Integer dado = 0;
        switch (face){
            case 4 -> dado = roll.getD4();
            case 6 -> dado = roll.getD6();
            case 8 -> dado = roll.getD8();
            case 10 -> dado = roll.getD10();
            case 12 -> dado = roll.getD12();
        }
        return dado;
    }

    public static ItemDeUso buscarItemPorNome(String nomeItem) {
        String sql = "SELECT * FROM item_de_uso WHERE nome_item = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeItem); // Define o parâmetro antes de executar
            ResultSet rs = stmt.executeQuery(); // Executa a consulta

            if (rs.next()) {
                Integer id = rs.getInt("id_item");
                String nome = rs.getString("nome_item");
                int dano = rs.getInt("dano_item");
                String tipo = rs.getString("tipo_item");

                return new ItemDeUso(id, nome, dano, tipo); // Retorna item encontrado
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar item: " + e.getMessage());
        }

        return null; // Não encontrado
    }

    public static List<ItemDeUso> listarTodosItens() {
        List<ItemDeUso> itens = new ArrayList<>();
        String sql = "SELECT * FROM item_de_uso";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("Id_item");
                String nome = rs.getString("nome_item");
                int dano = rs.getInt("dano_item");
                String tipo = rs.getString("tipo_item");

                itens.add(new ItemDeUso(id, nome, dano, tipo));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar itens: " + e.getMessage());
        }

        return itens;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome + " | Dano: " + dano + " | Tipo: " + tipo;
    }

}
