package org.example.atributos;

import lombok.Getter;
import org.example.classesSuportes.Dados;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Habilidade {
    // definicoes de habilidades !//
    // Definição de atributos ! //

    private Integer id;
    private final String nome;
    private final Integer dano;
    private final String efeito;
    private final Integer custo;
    private final String alvo;

    private static final String URL = "jdbc:mysql://localhost:3306/sistemarpg";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";


    // Construtor ! //
    Habilidade(Integer id,String nome, Integer dano, String efeito,Integer custo,String alvo){
        this.id = id;
        this.nome = nome;
        this.dano = rollDamage(dano);
        this.efeito = efeito;
        this.custo = custo;
        this.alvo = alvo;

    }

    static Dados dado = new Dados();
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

    public static void inserirTodos() {
        String url = "jdbc:mysql://localhost:3306/sistemarpg";
        String usuario = "root";
        String senha = "1234";

        String sql = "INSERT INTO habilidade (nome_habilidade, dano_habilidade, efeito_habilidade, custo_habilidade, alvo_habilidade) VALUES (?,?,?,?,?)";

//        try (Connection cnn = DriverManager.getConnection(url, usuario, senha);
//             PreparedStatement stmt = cnn.prepareStatement(sql)) {
//
//            for (Habilidade habilidade : Habilidade.values()) {
//                stmt.setString(1, habilidade.getNome());
//                stmt.setInt(2, habilidade.getDano());
//                stmt.setString(3, habilidade.getEfeito());
//                stmt.setInt(4, habilidade.getCusto());
//                stmt.setString(5, habilidade.getAlvo());
//                stmt.executeUpdate();
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
    }

    public static List<Habilidade> listarTodos(){

        String sql = "SELECT * FROM habilidade";
        List<Habilidade> habilidades = new ArrayList<>();

        try (Connection cnn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = cnn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){


                if(rs.next()) {
                    int id = rs.getInt("idhabilidade");
                    String nome = rs.getString("nome_habilidade");
                    int dano = rs.getInt("dano_habilidade");
                    String efeito = rs.getString("efeito_habilidade");
                    int custo = rs.getInt("custo_habilidade");
                    String alvo = rs.getString("alvo_habilidade");

                    habilidades.add(new Habilidade(id,nome,dano,efeito,custo,alvo));
                }

            } catch (SQLException e) {
                System.out.println(e);
            }
        return habilidades;
    }

    public static Habilidade buscarPorNome(String nomeHabilidade) {
        String sql = "SELECT * FROM habilidade WHERE nome_habilidade = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeHabilidade); // Define o parâmetro antes de executar
            ResultSet rs = stmt.executeQuery(); // Executa a consulta

            if (rs.next()) {
                Integer id = rs.getInt("idhabilidade");
                String nome = rs.getString("nome_habilidade");
                int dano  =  rs.getInt("dano_habilidade");
                String efeito = rs.getString("efeito_habilidade");
                int custo = rs.getInt("custo_habilidade");
                String alvo = rs.getString("alvo_habilidade");
                return new Habilidade(id, nome, dano, efeito, custo, alvo ); // Retorna item encontrado
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar item: " + e.getMessage());
        }

        return null; // Não encontrado
    }

    public static Habilidade buscarPorId(Integer idHabilidade) {
        String sql = "SELECT * FROM habilidade WHERE idhabilidade = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHabilidade); // Define o parâmetro antes de executar
            ResultSet rs = stmt.executeQuery(); // Executa a consulta

            if (rs.next()) {
                Integer id = rs.getInt("idhabilidade");
                String nome = rs.getString("nome_habilidade");
                int dano  =  rs.getInt("dano_habilidade");
                String efeito = rs.getString("efeito_habilidade");
                int custo = rs.getInt("custo_habilidade");
                String alvo = rs.getString("alvo_habilidade");
                return new Habilidade(id, nome, dano, efeito, custo, alvo ); // Retorna item encontrado
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar item: " + e.getMessage());
        }

        return null; // Não encontrado
    }
    @Override
    public String toString() {
        return "[" + id + "] " + nome + " | Dano: " + dano + " | Efeito: " + efeito + " | Custo: " + custo + " | Alvo: " + alvo;
    }

    public Integer getValorEfeito(){
        if (getEfeito().equalsIgnoreCase("+ 1d6 de dano")){
            return dado.getD6();
        }
        return null;
    }

    }



