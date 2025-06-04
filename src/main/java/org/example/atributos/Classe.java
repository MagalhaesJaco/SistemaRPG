package org.example.atributos;

import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Classe {

    // Definição de atributos das classes ! //
    private final Integer id;
    private final String nome;
    private final String descricao;
    private final Integer forcaBonus;
    private final Integer agilidadeBonus;
    private final Integer vigorBonus;
    private final Integer presencaBonus;
    private final Integer intelectoBonus;

    private static final String URL = "jdbc:mysql://localhost:3306/sistemarpg";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";

    // Construtor ! //
    Classe(Integer id, String nome, String descricao, Integer forcaBonus, Integer agilidadeBonus, Integer vigorBonus, Integer presencaBonus, Integer intelectoBonus) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.forcaBonus = forcaBonus;
        this.agilidadeBonus = agilidadeBonus;
        this.vigorBonus = vigorBonus;
        this.presencaBonus = presencaBonus;
        this.intelectoBonus = intelectoBonus;
    }

    // Gets ! //

    public static Classe buscarPorNome(String nome1) {
        String sql = "SELECT * FROM classe WHERE nome_classe = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome1);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idclasse");
                String nome = rs.getString("nome_classe");
                String descricao = rs.getString("descricao_classe");
                int forcaBonus = rs.getInt("forcaBonus_classe");
                int agilidadeBonus = rs.getInt("agilidadeBonus_classe");
                int vigorBonus = rs.getInt("vigorBonus_classe");
                int presencaBonus = rs.getInt("presencaBonus_classe");
                int intelectoBonus = rs.getInt("intelectoBonus_classe");
                return new Classe(id,nome,descricao,forcaBonus,agilidadeBonus,vigorBonus,presencaBonus,intelectoBonus);

            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar por nome: " + e.getMessage());
        }

        return null;
    }

    public static List<Classe> listarTodas() {
        List<Classe> lista = new ArrayList<>();
        String sql = "SELECT * FROM classe";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idclasse");
                String nome = rs.getString("nome_classe");
                String descricao = rs.getString("descricao_classe");
                int forcaBonus = rs.getInt("forcaBonus_classe");
                int agilidadeBonus = rs.getInt("agilidadeBonus_classe");
                int vigorBonus = rs.getInt("vigorBonus_classe");
                int presencaBonus = rs.getInt("presencaBonus_classe");
                int intelectoBonus = rs.getInt("intelectoBonus_classe");

                Classe classe = new Classe(id, nome, descricao, forcaBonus, agilidadeBonus, vigorBonus, presencaBonus, intelectoBonus);
                lista.add(classe);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar classes: " + e.getMessage());
        }

        return lista;
    }

    public static Classe buscarPorId(int id) {
        String sql = "SELECT * FROM classe WHERE idclasse = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                rs.getInt("idclasse");
                String nome = rs.getString("nome_classe");
                String descricao = rs.getString("descricao_classe");
                int forcaBonus = rs.getInt("forcaBonus_classe");
                int agilidadeBonus = rs.getInt("agilidadeBonus_classe");
                int vigorBonus = rs.getInt("vigorBonus_classe");
                int presencaBonus = rs.getInt("presencaBonus_classe");
                int intelectoBonus = rs.getInt("intelectoBonus_classe");
                return new Classe(id,nome,descricao,forcaBonus,agilidadeBonus,vigorBonus,presencaBonus,intelectoBonus);

            }


        } catch (SQLException e) {
            System.err.println("Erro ao buscar por ID: " + e.getMessage());


            // 2. Buscar classe por nome
        }
        return null;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nome +
                " | Descrição: " + descricao +
                " | Força: " + forcaBonus +
                " | Agilidade: " + agilidadeBonus +
                " | Vigor: " + vigorBonus +
                " | Presença: " + presencaBonus +
                " | Intelecto: " + intelectoBonus;
    }
}
