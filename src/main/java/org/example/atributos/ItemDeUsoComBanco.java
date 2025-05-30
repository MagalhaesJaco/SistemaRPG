package org.example.atributos;
import lombok.Getter;
import org.example.classesSuportes.Dados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Getter

public class ItemDeUsoComBanco {

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

    ItemDeUsoComBanco(int id, String nome, Integer dano, String tipo){
        this.id = id;
        this.nome = nome;
        this.dano = dano;
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

    public static void inserirTodosItensDeUsoNoBanco() {
        String sql = "INSERT INTO item_de_uso ( nome_item, dano_item, tipo_item) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (ItemDeUso item : ItemDeUso.values()) {
                stmt.setString(1, item.getNome());
                stmt.setInt(2, item.getDano());  // Usa o valor rolado com base no dado (D4, D6, etc.)
                stmt.setString(3, item.getTipo());
                stmt.executeUpdate();
            }

            System.out.println("Todos os itens de uso foram inseridos no banco com sucesso.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir itens de uso: " + e.getMessage());
        }
    }

    public static ItemDeUsoComBanco buscarItemPorNome(String nomeItem) {
        String sql = "SELECT * FROM item_de_uso WHERE nome = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeItem);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Integer id = rs.getInt("id_item");
                String nome = rs.getString("nome_item");
                int dano = rs.getInt("dano_item");
                String tipo = rs.getString("tipo_item");

                return new ItemDeUsoComBanco(id, nome, dano, tipo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar item: " + e.getMessage());
        }

        return null; // Não encontrado
    }

    public static List<ItemDeUsoComBanco> listarTodos() {
        List<ItemDeUsoComBanco> itens = new ArrayList<>();
        String sql = "SELECT * FROM item_de_uso";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("Id_item");
                String nome = rs.getString("nome_item");
                int dano = rs.getInt("dano_item");
                String tipo = rs.getString("tipo_item");

                itens.add(new ItemDeUsoComBanco(id, nome, dano, tipo));
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
