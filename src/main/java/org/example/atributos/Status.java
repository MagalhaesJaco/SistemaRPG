package org.example.atributos;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.example.classesSuportes.Combate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Status extends Combate {
    private  int id;
    private Integer nivel;
    private int forca;
    private int agilidade;
    private int intelecto;
    private int vigor;
    private int presenca;
    private int statusTotal;

    private static final String URL = "jdbc:mysql://localhost:3306/sistemarpg";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";

    public Status(int id,Integer nivel, Integer forca, Integer agilidade, Integer vigor, Integer intelecto, Integer presenca) throws SQLException {
        this.id =  id;
        setNivel(nivel);
        setForca(forca);
        setAgilidade(agilidade);
        setVigor(vigor);
        setIntelecto(intelecto);
        setPresenca(presenca);
        this.statusTotal = getStatusTotal();
    }

    public Status() throws SQLException {
    }

    public boolean podeAtribuir(Integer valor) {
        return valor <= getStatusRestantes();
    }

    public Integer getForca() {
        return forca;
    }

    public Integer getIntelecto() {
        return intelecto;
    }

    public Integer getPresenca() {
        return presenca;
    }

    public Integer getVigor() {
        return vigor;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setAgilidade(Integer agilidade) {
        if (podeAtribuir(agilidade)) {
            this.agilidade = agilidade;
        }
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
        if (nivel != 0) {
            atualizarStatusPorNivel(nivel);
        }
    }

    public void setForca(int forca) {
        if (podeAtribuir(forca)) {
            this.forca = forca;
        }
    }

    public void setIntelecto(Integer intelecto) {
        if (podeAtribuir(intelecto)) {
            this.intelecto = intelecto;
        }
    }

    public void setPresenca(Integer presenca) {
        if (podeAtribuir(presenca)) {
            this.presenca = presenca;
        }
    }

    public void setVigor(Integer vigor) {
        if (podeAtribuir(vigor)) {
            this.vigor = vigor;
        }
    }

    public void setStatusTotal(int statusTotal) {
        this.statusTotal = statusTotal;
    }

    public Integer atualizarStatusPorNivel(int nivel) {
        this.statusTotal = 9 + ((nivel - 1) * 2);
        return 9 + ((nivel - 1) * 2);
    }

    public Integer getStatusTotal() {
        return statusTotal;
    }

    public int getStatusRestantes() {
        int usados = forca + agilidade + intelecto + intelecto;
        return statusTotal - usados;
    }

    public static List<Status> listarTodos() {
        List<Status> lista = new ArrayList<>();
        String sql = "SELECT * FROM status";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("idstatus");
                Integer nivel = rs.getInt("nivel");
                Integer forca = rs.getInt("forca");
                Integer agilidade = rs.getInt("agilidade");
                Integer intelecto = rs.getInt("intelecto");
                Integer vigor = rs.getInt("vigor");
                Integer presenca = rs.getInt("presenca");
                Integer statusTotal = rs.getInt("statusTotal");

                Status status = new Status(id, nivel, forca, agilidade, intelecto, vigor, presenca);
                lista.add(status);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar status: " + e.getMessage());
        }

        return lista;
    }

    public static void criarStatus(Integer forca, Integer agilidade, Integer intelecto, Integer vigor, Integer presenca) {
        String sql = "INSERT INTO status (nivel, forca,agilidade,intelecto,vigor,presenca) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1,1);
            statement.setInt(2,forca);
            statement.setInt(3,agilidade);
            statement.setInt(4, intelecto);
            statement.setInt(5, vigor);
            statement.setInt(6, presenca);
            statement.executeUpdate();
            System.out.println("deu");


        } catch (SQLException e){
            System.out.println("Deu ruim" + e);
        }

    }

    public  Status buscarPorId(int id) {
        String sql = "SELECT * FROM status WHERE idstatus = ?";

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int nivel = rs.getInt("nivel");
                int forca = rs.getInt("forca");
                int agilidade = rs.getInt("agilidade");
                int vigor = rs.getInt("vigor");
                int intelecto = rs.getInt("intelecto");
                int presenca = rs.getInt("presenca");

                return new Status(id,nivel,forca,agilidade,vigor,intelecto,presenca);

            }


        } catch (SQLException e) {
            System.err.println("Erro ao buscar por ID: " + e.getMessage());


            // 2. Buscar classe por nome
        }
        return null;
    }
    @Override
    public String toString() {
        return "[" + id + "] " +
                " | nivel " + nivel +
                " | forca: " + forca +
                " | agilidade: " + agilidade +
                " | vigor: " + vigor +
                " | intelecto: " + intelecto +
                " | presenca: " + presenca ;

    }
}

