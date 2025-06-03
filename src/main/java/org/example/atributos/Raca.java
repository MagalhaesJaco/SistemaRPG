package org.example.atributos;


import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Raca {

    private final  Integer id;
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

        Raca(Integer id ,String nome, String descricao, Integer forcaBonus,Integer agilidadeBonus,Integer vigorBonus,Integer presencaBonus,Integer intelectoBonus) {this.nome = nome;this.descricao = descricao;
        this.id = id;
        this.forcaBonus = forcaBonus;
        this.agilidadeBonus = agilidadeBonus;
        this.vigorBonus = vigorBonus;
        this.presencaBonus = presencaBonus;
        this.intelectoBonus = intelectoBonus;
    }

    public static List<Raca> listarTodasAsRacas() {
        String sql = "SELECT * FROM raca";
        List<Raca> racas = new ArrayList<>();

        try (Connection cnn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = cnn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idRaca");
                String nome = rs.getString("nome_raca");
                String descricao = rs.getString("descricao_raca");
                int forca = rs.getInt("forcaBonus_raca");
                int agilidade = rs.getInt("agilidadeBonus_raca");
                int vigor = rs.getInt("vigorBonus_raca");
                int presenca = rs.getInt("presencaBonus_raca");
                int intelecto = rs.getInt("intelectoBonus_raca");

                racas.add(new Raca(id, nome, descricao, forca, agilidade, vigor, presenca, intelecto));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar raças: " + e.getMessage());
        }

        return racas;
    }
    public static Raca buscarRacaPorId(int idRaca) {
        String sql = "SELECT * FROM raca WHERE idRaca = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRaca);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idRaca");
                String nome = rs.getString("nome_raca");
                String descricao = rs.getString("descricao_raca");
                int forca = rs.getInt("forcaBonus_raca");
                int agilidade = rs.getInt("agilidadeBonus_raca");
                int vigor = rs.getInt("vigorBonus_raca");
                int presenca = rs.getInt("presencaBonus_raca");
                int intelecto = rs.getInt("intelectoBonus_raca");

                return new Raca(id, nome, descricao, forca, agilidade, vigor, presenca, intelecto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar raça por ID: " + e.getMessage());
        }

        return null;
    }

    public static Raca buscarRacaPorNome(String nomeRaca) {
        String sql = "SELECT * FROM raca WHERE nome_raca = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeRaca);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idRaca");
                String nome = rs.getString("nome_raca");
                String descricao = rs.getString("descricao_raca");
                int forca = rs.getInt("forcaBonus_raca");
                int agilidade = rs.getInt("agilidadeBonus_raca");
                int vigor = rs.getInt("vigorBonus_raca");
                int presenca = rs.getInt("presencaBonus_raca");
                int intelecto = rs.getInt("intelectoBonus_raca");

                return new Raca(id, nome, descricao, forca, agilidade, vigor, presenca, intelecto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar raça por nome: " + e.getMessage());
        }

        return null;
    }
    @Override
        public String toString() {
            return "[" + id + "] " + nome + ": " + descricao +
                    " | FOR: " + forcaBonus + " | AGI: " + agilidadeBonus +
                    " | VIG: " + vigorBonus + " | PRE: " + presencaBonus +
                    " | INT: " + intelectoBonus;
        }
}

